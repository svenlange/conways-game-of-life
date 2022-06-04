package za.co.svenlange.gameoflife.gui;

import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import za.co.svenlange.gameoflife.core.Cell;
import za.co.svenlange.gameoflife.core.Grid;
import za.co.svenlange.gameoflife.set.PredefinedGrids;

import java.util.Collection;

public class Main extends Application {

    private static final int CELL_SIZE = 1;
    private static final Color BACKGROUND_COLOR = Color.WHITE;
    private static final Color CELL_COLOR = Color.ORANGERED;
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Conway's Game of Life by Sven Lange");
        stage.setMaxWidth(WIDTH);
        stage.setMaxHeight(HEIGHT);

//        Grid grid = za.co.svenlange.gameoflife.naive.PredefinedGrids.getActionGrid(getWidth(), getHeight());
        Grid grid = PredefinedGrids.getActionGrid(WIDTH, HEIGHT);

        final Group root = new Group();
        final Canvas canvas = new Canvas(WIDTH, HEIGHT);
        root.getChildren().add(canvas);
        stage.setScene(new Scene(root, WIDTH, HEIGHT, BACKGROUND_COLOR));
        stage.show();

        final GridService service = new GridService(grid);
        service.setOnSucceeded(workerStateEvent -> {
            Grid g = (Grid) workerStateEvent.getSource().getValue();
            updateCanvas(g.getAliveCells(), canvas);
            service.restart();
        });
        service.start();
    }

    private void updateCanvas(Collection<Cell> aliveCells, Canvas canvas) {
        GraphicsContext gc = canvas.getGraphicsContext2D();

        gc.setFill(BACKGROUND_COLOR);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

        // draw alive cells
        gc.setFill(CELL_COLOR);
        for (Cell cell : aliveCells) {
            gc.fillRect(cell.getX() * CELL_SIZE, cell.getY() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }
    }

    private class GridService extends Service<Grid> {
        private Grid grid;

        public GridService(Grid grid) {
            this.grid = grid;
        }

        @Override
        protected Task<Grid> createTask() {
            return new Task<Grid>() {

                @Override
                protected Grid call() throws Exception {
                    grid = grid.getNextGeneration();
                    return grid;
                }
            };
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
