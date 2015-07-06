package za.co.svenlange.gameoflife.set;

import za.co.svenlange.gameoflife.Cell;
import za.co.svenlange.gameoflife.Grid;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class GridWithSet implements Grid {

    public GridWithSet() {
        this.aliveCells = new HashSet<>();
    }

    private GridWithSet(Set<Cell> aliveCells) {
        this.aliveCells = aliveCells;
    }

    private final Set<Cell> aliveCells;

    @Override
    public Collection<Cell> getAliveCells() {
        return new HashSet<>(aliveCells);
    }

    @Override
    public void addAliveCell(int x, int y) {
        aliveCells.add(new Cell(x, y));
    }

    @Override
    public Grid getNextGeneration() {
        Set<Cell> survivingCells = getSurvivingCells();
        Set<Cell> newBornCells = getNewBornCells();

        Set<Cell> nextGeneration = new HashSet<>();
        nextGeneration.addAll(survivingCells);
        nextGeneration.addAll(newBornCells);

        return new GridWithSet(nextGeneration);
    }

    private Set<Cell> getSurvivingCells() {
        return aliveCells.stream()
                    .filter(aliveCell -> getNumberOfAliveNeighbors(aliveCell.getX(), aliveCell.getY()) == 2 || getNumberOfAliveNeighbors(aliveCell.getX(), aliveCell.getY()) == 3)
                    .collect(toSet());
    }

    private Set<Cell> getNewBornCells() {
        return aliveCells.stream().flatMap(aliveCell -> getNeighbors(aliveCell).stream())
                .filter(neighbor -> !aliveCells.contains(neighbor))
                .filter(neighbor -> getNumberOfAliveNeighbors(neighbor.getX(), neighbor.getY()) == 3)
                .collect(toSet());
    }

    private Set<Cell> getNeighbors(Cell cell) {
        final int x = cell.getX();
        final int y = cell.getY();
        final int center = 0;
        final int north = -1;
        final int south = 1;
        final int west = -1;
        final int east = 1;
        Set<Cell> neighbors = new HashSet<>();
        neighbors.add(new Cell(x + west, y + north));
        neighbors.add(new Cell(x + center, y + north));
        neighbors.add(new Cell(x + east, y + north));
        neighbors.add(new Cell(x + west, y + center));
        neighbors.add(new Cell(x + east, y + center));
        neighbors.add(new Cell(x + east, y + south));
        neighbors.add(new Cell(x + center, y + south));
        neighbors.add(new Cell(x + west, y + south));
        return neighbors;
    }

    @Override
    public boolean isCellAlive(int x, int y) {
        return aliveCells.contains(new Cell(x, y));
    }

    @Override
    public long getNumberOfAliveNeighbors(int x, int y) {
        return getNeighbors(new Cell(x, y)).stream().filter(aliveCells::contains).count();
    }
}
