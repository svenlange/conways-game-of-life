package za.co.svenlange.gameoflife;

/**
 * PredefinedGrids
 *
 * @author Sven Lange
 */
public class PredefinedGrids {
    public static Grid getBlinkerGrid() {
        GridImpl grid = new GridImpl(6, 6);
        grid.addCell(2, 2);
        grid.addCell(2, 3);
        grid.addCell(2, 4);
        return grid;
    }

    public static Grid getBeaconGrid() {
        GridImpl grid = new GridImpl(8, 8);
        grid.addCell(1, 1);
        grid.addCell(1, 2);
        grid.addCell(2, 1);
        grid.addCell(2, 2);
        grid.addCell(3, 3);
        grid.addCell(3, 4);
        grid.addCell(4, 3);
        grid.addCell(4, 4);
        return grid;
    }

    public static Grid getOctagon2Grid() {
        GridImpl grid = new GridImpl(10, 10);
        grid.addCell(4, 1);
        grid.addCell(5, 1);
        grid.addCell(3, 2);
        grid.addCell(6, 2);
        grid.addCell(2, 3);
        grid.addCell(7, 3);
        grid.addCell(1, 4);
        grid.addCell(8, 4);
        grid.addCell(1, 5);
        grid.addCell(8, 5);
        grid.addCell(2, 6);
        grid.addCell(7, 6);
        grid.addCell(3, 7);
        grid.addCell(6, 7);
        grid.addCell(4, 8);
        grid.addCell(5, 8);
        return grid;
    }

    public static Grid getActionGrid() {
        int size = 200; // thats a grid with 10000 cells
        GridImpl grid = new GridImpl(size, size);
        for (int i = 0; i < size; i++) {
            grid.addCell(i, size / 2);
        }
        return grid;
    }

    public static Grid getFlowerOfEden() {
        GridImpl grid = new GridImpl(13, 13);

        grid.addCell(2, 1);
        grid.addCell(3, 1);
        grid.addCell(4, 1);
        grid.addCell(7, 1);
        grid.addCell(8, 1);

        grid.addCell(2, 2);
        grid.addCell(3, 2);
        grid.addCell(5, 2);
        grid.addCell(7, 2);
        grid.addCell(9, 2);
        grid.addCell(10, 2);
        grid.addCell(11, 2);
        
        grid.addCell(2, 3);
        grid.addCell(3, 3);
        grid.addCell(4, 3);
        grid.addCell(7, 3);
        grid.addCell(8, 3);
        grid.addCell(9, 3);
        grid.addCell(10, 3);
        grid.addCell(11, 3);

        grid.addCell(1, 4);
        grid.addCell(3, 4);
        grid.addCell(5, 4);
        grid.addCell(7, 4);
        grid.addCell(9, 4);
        grid.addCell(11, 4);
        
        grid.addCell(1, 5);
        grid.addCell(2, 5);
        grid.addCell(3, 5);
        grid.addCell(4, 5);
        grid.addCell(6, 5);
        grid.addCell(8, 5);
        grid.addCell(10, 5);
        
        grid.addCell(5, 6);
        grid.addCell(6, 6);
        grid.addCell(7, 6);
        
        grid.addCell(2, 7);
        grid.addCell(4, 7);
        grid.addCell(6, 7);
        grid.addCell(8, 7);
        grid.addCell(9, 7);
        grid.addCell(10, 7);
        grid.addCell(11, 7);
        
        grid.addCell(1, 8);
        grid.addCell(3, 8);
        grid.addCell(5, 8);
        grid.addCell(7, 8);
        grid.addCell(9, 8);
        grid.addCell(11, 8);
        
        grid.addCell(1, 9);
        grid.addCell(2, 9);
        grid.addCell(3, 9);
        grid.addCell(4, 9);
        grid.addCell(5, 9);
        grid.addCell(8, 9);
        grid.addCell(9, 9);
        grid.addCell(10, 9);
        
        grid.addCell(1, 10);
        grid.addCell(2, 10);
        grid.addCell(3, 10);
        grid.addCell(5, 10);
        grid.addCell(7, 10);
        grid.addCell(9, 10);
        grid.addCell(10, 10);
        
        grid.addCell(4, 11);
        grid.addCell(5, 11);
        grid.addCell(8, 11);
        grid.addCell(9, 11);
        grid.addCell(10, 11);
        
        return grid;
    }


}
