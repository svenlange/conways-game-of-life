package za.co.svenlange.gameoflife.array;

import za.co.svenlange.gameoflife.core.Grid;

/**
 * PredefinedGrids
 *
 * @author Sven Lange
 */
public abstract class PredefinedGrids {

    public static Grid getBlinkerGrid() {
        Grid grid = new GridWithArray(6, 6);
        grid.addAliveCell(2, 2);
        grid.addAliveCell(2, 3);
        grid.addAliveCell(2, 4);
        return grid;
    }

    public static Grid getBeaconGrid() {
        Grid grid = new GridWithArray(8, 8);
        grid.addAliveCell(1, 1);
        grid.addAliveCell(1, 2);
        grid.addAliveCell(2, 1);
        grid.addAliveCell(2, 2);
        grid.addAliveCell(3, 3);
        grid.addAliveCell(3, 4);
        grid.addAliveCell(4, 3);
        grid.addAliveCell(4, 4);
        return grid;
    }

    public static Grid getOctagon2Grid() {
        Grid grid = new GridWithArray(10, 10);
        grid.addAliveCell(4, 1);
        grid.addAliveCell(5, 1);
        grid.addAliveCell(3, 2);
        grid.addAliveCell(6, 2);
        grid.addAliveCell(2, 3);
        grid.addAliveCell(7, 3);
        grid.addAliveCell(1, 4);
        grid.addAliveCell(8, 4);
        grid.addAliveCell(1, 5);
        grid.addAliveCell(8, 5);
        grid.addAliveCell(2, 6);
        grid.addAliveCell(7, 6);
        grid.addAliveCell(3, 7);
        grid.addAliveCell(6, 7);
        grid.addAliveCell(4, 8);
        grid.addAliveCell(5, 8);
        return grid;
    }

    public static Grid getFumarole() {
        Grid grid = new GridWithArray(10,10);
        grid.addAliveCell(1, 7);
        grid.addAliveCell(2, 7);
        grid.addAliveCell(7, 7);
        grid.addAliveCell(8, 7);

        grid.addAliveCell(1, 6);
        grid.addAliveCell(3, 6);
        grid.addAliveCell(6, 6);
        grid.addAliveCell(8, 6);

        grid.addAliveCell(3, 5);
        grid.addAliveCell(6, 5);

        grid.addAliveCell(2, 4);
        grid.addAliveCell(7, 4);

        grid.addAliveCell(2, 3);
        grid.addAliveCell(7, 3);

        grid.addAliveCell(2, 2);
        grid.addAliveCell(7, 2);

        grid.addAliveCell(4, 1);
        grid.addAliveCell(5, 1);
        return grid;
    }


    public static Grid getActionGrid(int width, int height) {
        Grid grid = new GridWithArray(width, height);

        for (int i = 0; i < width; i++) {
            grid.addAliveCell(i, height / 2);
        }

        return grid;
    }

    public static Grid getFlowerOfEden() {
        Grid grid = new GridWithArray(13, 13);

        grid.addAliveCell(2, 1);
        grid.addAliveCell(3, 1);
        grid.addAliveCell(4, 1);
        grid.addAliveCell(7, 1);
        grid.addAliveCell(8, 1);

        grid.addAliveCell(2, 2);
        grid.addAliveCell(3, 2);
        grid.addAliveCell(5, 2);
        grid.addAliveCell(7, 2);
        grid.addAliveCell(9, 2);
        grid.addAliveCell(10, 2);
        grid.addAliveCell(11, 2);

        grid.addAliveCell(2, 3);
        grid.addAliveCell(3, 3);
        grid.addAliveCell(4, 3);
        grid.addAliveCell(7, 3);
        grid.addAliveCell(8, 3);
        grid.addAliveCell(9, 3);
        grid.addAliveCell(10, 3);
        grid.addAliveCell(11, 3);

        grid.addAliveCell(1, 4);
        grid.addAliveCell(3, 4);
        grid.addAliveCell(5, 4);
        grid.addAliveCell(7, 4);
        grid.addAliveCell(9, 4);
        grid.addAliveCell(11, 4);

        grid.addAliveCell(1, 5);
        grid.addAliveCell(2, 5);
        grid.addAliveCell(3, 5);
        grid.addAliveCell(4, 5);
        grid.addAliveCell(6, 5);
        grid.addAliveCell(8, 5);
        grid.addAliveCell(10, 5);

        grid.addAliveCell(5, 6);
        grid.addAliveCell(6, 6);
        grid.addAliveCell(7, 6);

        grid.addAliveCell(2, 7);
        grid.addAliveCell(4, 7);
        grid.addAliveCell(6, 7);
        grid.addAliveCell(8, 7);
        grid.addAliveCell(9, 7);
        grid.addAliveCell(10, 7);
        grid.addAliveCell(11, 7);

        grid.addAliveCell(1, 8);
        grid.addAliveCell(3, 8);
        grid.addAliveCell(5, 8);
        grid.addAliveCell(7, 8);
        grid.addAliveCell(9, 8);
        grid.addAliveCell(11, 8);

        grid.addAliveCell(1, 9);
        grid.addAliveCell(2, 9);
        grid.addAliveCell(3, 9);
        grid.addAliveCell(4, 9);
        grid.addAliveCell(5, 9);
        grid.addAliveCell(8, 9);
        grid.addAliveCell(9, 9);
        grid.addAliveCell(10, 9);

        grid.addAliveCell(1, 10);
        grid.addAliveCell(2, 10);
        grid.addAliveCell(3, 10);
        grid.addAliveCell(5, 10);
        grid.addAliveCell(7, 10);
        grid.addAliveCell(9, 10);
        grid.addAliveCell(10, 10);

        grid.addAliveCell(4, 11);
        grid.addAliveCell(5, 11);
        grid.addAliveCell(8, 11);
        grid.addAliveCell(9, 11);
        grid.addAliveCell(10, 11);

        return grid;
    }

}
