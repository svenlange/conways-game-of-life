package za.co.svenlange.gameoflife;

import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    private static final int CELL_SIZE = 1;
    private static final Color BACKGROUND_COLOR = Color.WHITE;
    private static final Color CELL_COLOR = Color.ORANGERED;

    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("Conway's Game of Life by Sven Lange");

        Grid grid = PredefinedGrids.getActionGrid();

        final Group root = new Group();
        root.getChildren().add(createCanvas(grid));
        stage.setScene(new Scene(root, getSceneWidth(grid), getSceneHeight(grid), BACKGROUND_COLOR));
        stage.show();

        final GridService service = new GridService(grid);
        service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                Grid grid = (Grid) workerStateEvent.getSource().getValue();
                root.getChildren().clear();
                root.getChildren().add(createCanvas(grid));
                service.restart();
            }
        });
        service.start();
    }

    private int getSceneWidth(Grid grid) {
        return grid.getWidth() * CELL_SIZE;
    }

    private int getSceneHeight(Grid grid) {
        return grid.getHeight() * CELL_SIZE;
    }

    private Canvas createCanvas(Grid grid) {
        Canvas canvas = new Canvas(getSceneWidth(grid), getSceneHeight(grid));
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // draw alive cells
        for (Cell cell : grid.getAliveCells()) {
            gc.setFill(CELL_COLOR);
            gc.fillRect(cell.getX() * CELL_SIZE, cell.getY() * CELL_SIZE, CELL_SIZE, CELL_SIZE);
        }

        return canvas;
    }

    public static void main(String[] args) {
        launch(args);
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
                    Thread.sleep(100);
                    grid = grid.getNextGeneration();
                    return grid;
                }
            };
        }
    }
}
