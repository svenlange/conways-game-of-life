package za.co.svenlange.gameoflife.array;

import za.co.svenlange.gameoflife.Cell;
import za.co.svenlange.gameoflife.Grid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author Sven Lange
 */
public class GridWithArray implements Grid {

    private final State[][] grid;

    public GridWithArray() {
        this(100, 100);
    }

    public GridWithArray(int width, int height) {
        grid = new State[width][height];
    }

    @Override
    public long getNumberOfAliveNeighbors(int x, int y) {
        long numberOfAliveNeighbors = 0;

        for (int i = calculatePosition(x); i <= x + 1 && i < getWidth(); i++) {
            for (int j = calculatePosition(y); j <= y + 1 && j < getHeight(); j++) {
                if (isCellAlive(i, j) && !(x == i && y == j)) {
                    numberOfAliveNeighbors++;
                }
            }
        }

        return numberOfAliveNeighbors;
    }

    private int calculatePosition(int position) {
        if (position > 0) {
            return position - 1;
        } else {
            return position;
        }
    }

    @Override
    public void addAliveCell(int x, int y) {
        grid[x][y] = State.ALIVE;
    }

    @Override
    public Grid getNextGeneration() {
        Grid nextGrid = new GridWithArray(getWidth(), getHeight());

        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                if (isAliveInNextGeneration(x, y)) {
                    nextGrid.addAliveCell(x, y);
                }
            }
        }

        return nextGrid;
    }

    private boolean isAliveInNextGeneration(int x, int y) {
        return isCellAlive(x, y) && getNumberOfAliveNeighbors(x, y) == 2 || getNumberOfAliveNeighbors(x, y) == 3;
    }

    @Override
    public boolean isCellAlive(int x, int y) {
        return grid[x][y] != null && grid[x][y] == State.ALIVE;
    }

    @Override
    public Collection<Cell> getAliveCells() {
        List<Cell> aliveCells = new ArrayList<>();
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                if (isCellAlive(x, y)) {
                    aliveCells.add(new Cell(x, y));
                }
            }
        }
        return aliveCells;
    }

    private int getWidth() {
        return grid.length;
    }

    private int getHeight() {
        return grid[0].length;
    }

    private static enum State {
        ALIVE
    }
}
