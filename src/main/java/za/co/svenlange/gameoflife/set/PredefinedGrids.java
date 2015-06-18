package za.co.svenlange.gameoflife.set;

import za.co.svenlange.gameoflife.Grid;

public class PredefinedGrids {

    public static Grid getActionGrid(int width, int height) {
        Grid grid = new GridWithSet();

        for (int i = 0; i < width; i++) {
            grid.addAliveCell(i, height / 2);
        }

        return grid;
    }

}
