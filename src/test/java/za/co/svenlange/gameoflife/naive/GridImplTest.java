package za.co.svenlange.gameoflife.naive;

import org.junit.Before;
import org.junit.Test;
import za.co.svenlange.gameoflife.Grid;
import za.co.svenlange.gameoflife.PredefinedGrids;
import za.co.svenlange.gameoflife.naive.GridImpl;

import static org.junit.Assert.*;

public class GridImplTest {

    private GridImpl grid;

    @Before
    public void setUp() throws Exception {
        grid = new GridImpl(8, 8);
    }

    @Test
    public void neighbourCountIsZero() throws Exception {
        grid.addAliveCell(1, 1);
        assertEquals(0, grid.numberOfNeighbours(1, 1));
    }

    @Test
    public void neighbourCountIsOne() throws Exception {
        grid.addAliveCell(0, 0);
        grid.addAliveCell(1, 1);
        assertEquals(1, grid.numberOfNeighbours(1, 1));
    }

    @Test
    public void neighbourCountIsTwo() throws Exception {
        grid.addAliveCell(0, 0);
        grid.addAliveCell(1, 1);
        grid.addAliveCell(2, 2);
        assertEquals(2, grid.numberOfNeighbours(1, 1));
    }

    @Test
    public void neighbourCountIsThree() throws Exception {
        grid.addAliveCell(0, 0);
        grid.addAliveCell(1, 1);
        grid.addAliveCell(0, 1);
        grid.addAliveCell(2, 2);
        assertEquals(3, grid.numberOfNeighbours(1, 1));
    }

    @Test
    public void liveCellWithZeroLiveNeighboursDies() {
        grid.addAliveCell(1, 1);
        grid = grid.getNextGeneration();
        assertFalse(grid.isCellAlive(1, 1));
    }

    @Test
    public void liveCellWithOneLiveNeighboursDies() {
        grid.addAliveCell(0, 1);
        grid.addAliveCell(1, 1);
        grid = grid.getNextGeneration();
        assertFalse(grid.isCellAlive(1, 1));
    }

    @Test
    public void liveCellWithTwoLiveNeighboursLivesOn() {
        grid.addAliveCell(0, 0);
        grid.addAliveCell(0, 1);
        grid.addAliveCell(1, 1);
        grid = grid.getNextGeneration();
        assertEquals(true, grid.isCellAlive(1, 1));
    }

    @Test
    public void liveCellWithThreeLiveNeighboursLivesOn() {
        grid.addAliveCell(0, 0);
        grid.addAliveCell(0, 1);
        grid.addAliveCell(0, 2);
        grid.addAliveCell(1, 1);
        grid = grid.getNextGeneration();
        assertTrue(grid.isCellAlive(1, 1));
    }

    @Test
    public void liveCellWithFourLiveNeighboursDies() throws Exception {
        grid.addAliveCell(0, 0);
        grid.addAliveCell(0, 1);
        grid.addAliveCell(0, 2);
        grid.addAliveCell(1, 2);
        grid.addAliveCell(1, 1);
        grid = grid.getNextGeneration();
        assertFalse(grid.isCellAlive(1, 1));
    }

    @Test
    public void deadCellWithExactlyThreeLiveNeighboursBecomesLiveCell() {
        grid.addAliveCell(0, 5);
        grid.addAliveCell(0, 6);
        grid.addAliveCell(0, 7);
        grid = grid.getNextGeneration();
        assertEquals(true, grid.isCellAlive(1, 6));
    }
}
