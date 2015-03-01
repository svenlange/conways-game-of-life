package za.co.svenlange.gameoflife.naive;

import org.junit.Before;
import org.junit.Test;
import za.co.svenlange.gameoflife.Grid;

import static org.junit.Assert.*;

public class GridImplTest {

    private Grid grid;

    @Before
    public void setUp() throws Exception {
        grid = new GridImpl(8, 8);
    }

    @Test
    public void neighbourCountIsZero() throws Exception {
        grid.addAliveCell(new CellImpl(1, 1));
        assertEquals(0, grid.getNumberOfNeighbours(1, 1));
    }

    @Test
    public void neighbourCountIsOne() throws Exception {
        grid.addAliveCell(new CellImpl(0, 0));
        grid.addAliveCell(new CellImpl(1, 1));
        assertEquals(1, grid.getNumberOfNeighbours(1, 1));
    }

    @Test
    public void neighbourCountIsTwo() throws Exception {
        grid.addAliveCell(new CellImpl(0, 0));
        grid.addAliveCell(new CellImpl(1, 1));
        grid.addAliveCell(new CellImpl(2, 2));
        assertEquals(2, grid.getNumberOfNeighbours(1, 1));
    }

    @Test
    public void neighbourCountIsThree() throws Exception {
        grid.addAliveCell(new CellImpl(0, 0));
        grid.addAliveCell(new CellImpl(1, 1));
        grid.addAliveCell(new CellImpl(0, 1));
        grid.addAliveCell(new CellImpl(2, 2));
        assertEquals(3, grid.getNumberOfNeighbours(1, 1));
    }

    @Test
    public void liveCellWithZeroLiveNeighboursDies() {
        grid.addAliveCell(new CellImpl(1, 1));
        grid = grid.getNextGeneration();
        assertFalse(grid.isCellAlive(1, 1));
    }

    @Test
    public void liveCellWithOneLiveNeighboursDies() {
        grid.addAliveCell(new CellImpl(0, 1));
        grid.addAliveCell(new CellImpl(1, 1));
        grid = grid.getNextGeneration();
        assertFalse(grid.isCellAlive(1, 1));
    }

    @Test
    public void liveCellWithTwoLiveNeighboursLivesOn() {
        grid.addAliveCell(new CellImpl(0, 0));
        grid.addAliveCell(new CellImpl(0, 1));
        grid.addAliveCell(new CellImpl(1, 1));
        grid = grid.getNextGeneration();
        assertEquals(true, grid.isCellAlive(1, 1));
    }

    @Test
    public void liveCellWithThreeLiveNeighboursLivesOn() {
        grid.addAliveCell(new CellImpl(0, 0));
        grid.addAliveCell(new CellImpl(0, 1));
        grid.addAliveCell(new CellImpl(0, 2));
        grid.addAliveCell(new CellImpl(1, 1));
        grid = grid.getNextGeneration();
        assertTrue(grid.isCellAlive(1, 1));
    }

    @Test
    public void liveCellWithFourLiveNeighboursDies() throws Exception {
        grid.addAliveCell(new CellImpl(0, 0));
        grid.addAliveCell(new CellImpl(0, 1));
        grid.addAliveCell(new CellImpl(0, 2));
        grid.addAliveCell(new CellImpl(1, 2));
        grid.addAliveCell(new CellImpl(1, 1));
        grid = grid.getNextGeneration();
        assertFalse(grid.isCellAlive(1, 1));
    }

    @Test
    public void deadCellWithExactlyThreeLiveNeighboursBecomesLiveCell() {
        grid.addAliveCell(new CellImpl(0, 5));
        grid.addAliveCell(new CellImpl(0, 6));
        grid.addAliveCell(new CellImpl(0, 7));
        grid = grid.getNextGeneration();
        assertEquals(true, grid.isCellAlive(1, 6));
    }
}
