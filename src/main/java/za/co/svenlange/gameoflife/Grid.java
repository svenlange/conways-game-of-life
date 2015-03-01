package za.co.svenlange.gameoflife;

import java.util.Collection;

/**
 * Grid
 *
 * @author Sven Lange
 */
public interface Grid {
    Grid getNextGeneration();

    boolean isCellAlive(int x, int y);

    Collection<Cell> getAliveCells();

    int getNumberOfNeighbours(int i, int j);

    void addAliveCell(Cell cell);

    int getWidth();

    int getHeight();
}
