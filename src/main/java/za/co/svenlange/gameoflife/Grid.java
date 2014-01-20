package za.co.svenlange.gameoflife;

/**
 * Grid
 *
 * @author Sven Lange
 */
public interface Grid {
    Grid tick();

    boolean isCellAlive(int x, int y);

    int getWidth();

    int getHeight();
}
