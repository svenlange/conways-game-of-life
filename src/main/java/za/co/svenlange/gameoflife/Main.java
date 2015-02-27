package za.co.svenlange.gameoflife;

import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.Collection;

public class Main extends Application {

    private static final int CELL_SIZE = 1;
    private static final Color BACKGROUND_COLOR = Color.WHITE;
    private static final Color CELL_COLOR = Color.ORANGERED;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Conway's Game of Life by Sven Lange");
        stage.setMaximized(true);

        Grid grid = PredefinedGrids.getActionGrid(getWidth(), getHeight());

        final Group root = new Group();
        final Canvas canvas = new Canvas(getWidth(), getHeight());
        root.getChildren().add(canvas);
        stage.setScene(new Scene(root, getWidth(), getHeight(), BACKGROUND_COLOR));
        stage.show();

        final GridService service = new GridService(grid);
        service.setOnSucceeded(workerStateEvent -> {
            Grid g = (Grid) workerStateEvent.getSource().getValue();
            updateCanvas(g.getAliveCells(), canvas);
            service.restart();
        });
        service.start();
    }

    private static int getWidth() {
        Double width = Screen.getPrimary().getVisualBounds().getWidth();
        return width.intValue();
    }

    private static int getHeight() {
        Double height = Screen.getPrimary().getVisualBounds().getHeight();
        return height.intValue();
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
