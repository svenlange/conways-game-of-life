package za.co.svenlange.gameoflife;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import za.co.svenlange.gameoflife.array.GridWithArray;
import za.co.svenlange.gameoflife.set.GridWithSet;
import za.co.svenlange.gameoflife.set.GridWithSetInScala;

import static org.junit.Assert.*;

@RunWith(value = Parameterized.class)
public class GridTest {

    private final Class gridClass;

    private za.co.svenlange.gameoflife.Grid grid;

    public GridTest(Class gridClass) {
        this.gridClass = gridClass;
    }

    @Parameterized.Parameters(name = "{index}: class={0}")
    public static Class[] getImplementaionClasses() {
        return new Class[]{GridWithSet.class, GridWithArray.class, GridWithSetInScala.class};
    }

    @Before
    public void setUp() throws IllegalAccessException, InstantiationException {
        grid = (Grid) gridClass.newInstance();
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
