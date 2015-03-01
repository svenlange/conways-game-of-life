package za.co.svenlange.gameoflife.naive;

import za.co.svenlange.gameoflife.Cell;
import za.co.svenlange.gameoflife.Grid;

import java.util.ArrayList;
import java.util.Collection;

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

    @Override
    public int getNumberOfNeighbours(int i, int j) {
        int neighbours = 0;

        for (int x = calculatePosition(i); x <= i + 1 && x < getWidth(); x++) {
            for (int y = calculatePosition(j); y <= j + 1 && y < getHeight(); y++) {
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

    @Override
    public void addAliveCell(Cell cell) {
        grid[cell.getX()][cell.getY()] = State.ALIVE;
    }

    @Override
    public Grid getNextGeneration() {
        Grid nextGrid = new GridImpl(getWidth(), getHeight());

        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                if (isAliveInNextGeneration(x, y)) {
                    nextGrid.addAliveCell(new CellImpl(x, y));
                }
            }
        }

        return nextGrid;
    }

    private boolean isAliveInNextGeneration(int x, int y) {
        return isCellAlive(x, y) && getNumberOfNeighbours(x, y) == 2 || getNumberOfNeighbours(x, y) == 3;
    }

    @Override
    public boolean isCellAlive(int x, int y) {
        return grid[x][y] != null && grid[x][y] == State.ALIVE;
    }

    @Override
    public Collection<Cell> getAliveCells() {
        ArrayList<Cell> aliveCells = new ArrayList<>();
        for (int x = 0; x < getWidth(); x++) {
            for (int y = 0; y < getHeight(); y++) {
                if (isCellAlive(x, y)) {
                    aliveCells.add(new CellImpl(x, y));
                }
            }
        }
        return aliveCells;
    }

    @Override
    public int getWidth() {
        return grid.length;
    }

    @Override
    public int getHeight() {
        return grid[0].length;
    }

    private static enum State {
        ALIVE
    }
}
