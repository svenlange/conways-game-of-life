package za.co.svenlange.gameoflife;

import java.util.Collection;

/**
 * Grid
 *
 * @author Sven Lange
 */
public interface Grid {
    Grid getNextGeneration();

    Collection<Cell> getAliveCells();

    void addAliveCell(int x, int y);

    boolean isCellAlive(int x, int y);

    long getNumberOfAliveNeighbors(int x, int y);
}
