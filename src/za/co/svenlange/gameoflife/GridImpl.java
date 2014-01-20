package za.co.svenlange.gameoflife;

/**
 * GridImpl
 *
 * @author Sven Lange
 */
public class GridImpl implements Grid {

    private final State[][] grid;

    public GridImpl(int width, int height) {
        grid = new State[width][height];
    }

    int numberOfNeighbours(int i, int j) {
        int neighbours = 0;

        for (int x = calculatePosition(i); x <= i + 1 && x < grid.length; x++) {
            for (int y = calculatePosition(j); y <= j + 1 && y < grid.length; y++) {
                if (isCellAlive(x, y) && !(i == x && j == y)) {
                    neighbours++;
                }
            }
        }

        return neighbours;
    }

    private int calculatePosition(int position) {
        if (position > 0) {
            return position - 1;
        } else {
            return position;
        }
    }

    public void addCell(int x, int y) {
        grid[x][y] = State.ALIVE;
    }

    @Override
    public GridImpl tick() {
        GridImpl nextGrid = new GridImpl(getWidth(), getHeight());

        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid.length; y++) {
                if (isAliveInNextGeneration(x, y)) {
                    nextGrid.addCell(x, y);
                }
            }
        }

        return nextGrid;
    }

    private boolean isAliveInNextGeneration(int x, int y) {
        if (isCellAlive(x, y)) {
            return !(numberOfNeighbours(x, y) < 2) && !(numberOfNeighbours(x, y) > 3);
        } else {
            return numberOfNeighbours(x, y) == 3;
        }
    }

    @Override
    public boolean isCellAlive(int x, int y) {
        return grid[x][y] != null && grid[x][y] == State.ALIVE;
    }

    @Override
    public int getWidth() {
        return grid.length;
    }

    @Override
    public int getHeight() {
        return grid[0].length;
    }

    @Override
    public String toString() {
        StringBuilder buffer = new StringBuilder();

        for (int x = 0; x < grid.length; x++) {
            for (int y = 0; y < grid.length; y++) {

                buffer.append(grid[x][y]);
                if (y != grid.length - 1) {
                    buffer.append(" | ");
                } else {
                    buffer.append("\n");
                }

            }
        }
        return buffer.toString();
    }

    private static enum State {
        ALIVE
    }
}
