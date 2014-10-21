package za.co.svenlange.gameoflife;

import javafx.application.Application;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
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
		final Canvas canvas = new Canvas(getSceneWidth(grid), getSceneHeight(grid));
		root.getChildren().add(canvas);
		stage.setScene(new Scene(root, getSceneWidth(grid), getSceneHeight(grid), BACKGROUND_COLOR));
		stage.show();

		final GridService service = new GridService(grid);
		service.setOnSucceeded(workerStateEvent -> {
			Grid g = (Grid) workerStateEvent.getSource().getValue();
			updateCanvas(g, canvas);
			service.restart();
		});
		service.start();
	}

	private int getSceneWidth(Grid grid) {
		return grid.getWidth() * CELL_SIZE;
	}

	private int getSceneHeight(Grid grid) {
		return grid.getHeight() * CELL_SIZE;
	}

	private void updateCanvas(Grid grid, Canvas canvas) {
		GraphicsContext gc = canvas.getGraphicsContext2D();

		gc.setFill(BACKGROUND_COLOR);
		gc.fillRect(0, 0, grid.getWidth(), grid.getHeight());

		// draw alive cells
		gc.setFill(CELL_COLOR);
		for (Cell cell : grid.getAliveCells()) {
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
