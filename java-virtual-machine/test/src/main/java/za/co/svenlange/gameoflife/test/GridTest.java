package za.co.svenlange.gameoflife.test;

import org.junit.jupiter.api.Test;
import za.co.svenlange.gameoflife.core.Grid;

import static org.junit.jupiter.api.Assertions.*;

abstract public class GridTest {

    private Grid grid;

    public GridTest(Grid grid) {
        this.grid = grid;
    }

    @Test
    public void aNewGameHasNoCells() {
        assertEquals(0, grid.getAliveCells().size());
    }

    @Test
    public void cellsAddedToGameAreMaintained() {
        grid.addAliveCell(2, 3);
        assertTrue(grid.isCellAlive(2, 3));
    }

    @Test
    public void aSingleCellDiesAfterFirstGeneration() {
        grid.addAliveCell(2, 3);
        assertEquals(0, grid.getNumberOfAliveNeighbors(2, 3));
        assertFalse(grid.getNextGeneration().isCellAlive(2, 3));
    }

    @Test
    public void aCellWith2NeighborsSurvivesFirstGeneration() {
        grid.addAliveCell(2, 2);
        grid.addAliveCell(2, 3);
        grid.addAliveCell(2, 4);
        assertEquals(2, grid.getNumberOfAliveNeighbors(2, 3));
        assertTrue(grid.getNextGeneration().isCellAlive(2, 3));
    }

    @Test
    public void aCellWith3NeighborsSurvivesFirstGeneration() {
        grid.addAliveCell(2, 3);
        grid.addAliveCell(2, 2);
        grid.addAliveCell(2, 4);
        grid.addAliveCell(1, 4);
        assertEquals(3, grid.getNumberOfAliveNeighbors(2, 3));
        assertTrue(grid.getNextGeneration().isCellAlive(2, 3));
    }

    @Test
    public void aDeadCellWith3LiveNeigborsIsReborn() {
        grid.addAliveCell(2, 2);
        grid.addAliveCell(1, 4);
        grid.addAliveCell(2, 4);
        assertEquals(3, grid.getNumberOfAliveNeighbors(2, 3));
        assertTrue(grid.getNextGeneration().isCellAlive(2, 3));
    }

    @Test
    public void neighbourCountIsZero() {
        grid.addAliveCell(1, 1);
        assertEquals(0, grid.getNumberOfAliveNeighbors(1, 1));
    }

    @Test
    public void neighbourCountIsOne() {
        grid.addAliveCell(0, 0);
        grid.addAliveCell(1, 1);
        assertEquals(1, grid.getNumberOfAliveNeighbors(1, 1));
    }

    @Test
    public void neighbourCountIsTwo() {
        grid.addAliveCell(0, 0);
        grid.addAliveCell(1, 1);
        grid.addAliveCell(2, 2);
        assertEquals(2, grid.getNumberOfAliveNeighbors(1, 1));
    }

    @Test
    public void neighbourCountIsThree() {
        grid.addAliveCell(0, 0);
        grid.addAliveCell(1, 1);
        grid.addAliveCell(0, 1);
        grid.addAliveCell(2, 2);
        assertEquals(3, grid.getNumberOfAliveNeighbors(1, 1));
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
    public void liveCellWithFourLiveNeighboursDies() {
        grid.addAliveCell(0, 0);
        grid.addAliveCell(0, 1);
        grid.addAliveCell(0, 2);
        grid.addAliveCell(1, 2);
        grid.addAliveCell(1, 1);
        grid = grid.getNextGeneration();
        assertFalse(grid.isCellAlive(1, 1));
    }
}
