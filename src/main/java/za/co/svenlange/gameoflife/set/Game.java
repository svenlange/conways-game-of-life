package za.co.svenlange.gameoflife.set;

import za.co.svenlange.gameoflife.Cell;
import za.co.svenlange.gameoflife.Grid;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

public class Game implements Grid {

    public Game() {
        this.aliveCells = new HashSet<>();
    }

    private Game(Set<Cell> aliveCells) {
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
        Set<Cell> survivors = aliveCells.stream()
                .filter(aliveCell -> getNumberOfAliveNeighbours(aliveCell.getX(), aliveCell.getY()) == 2 || getNumberOfAliveNeighbours(aliveCell.getX(), aliveCell.getY()) == 3)
                .collect(toSet());

        Set<Cell> cellsThatAreReborn = aliveCells.stream().flatMap(aliveCell -> getNeighbors(aliveCell).stream())
                .filter(neighbor -> !aliveCells.contains(neighbor))
                .filter(neighbor -> getNumberOfAliveNeighbours(neighbor.getX(), neighbor.getY()) == 3)
                .collect(toSet());

        Set<Cell> nextGeneration = new HashSet<>();
        nextGeneration.addAll(survivors);
        nextGeneration.addAll(cellsThatAreReborn);

        return new Game(nextGeneration);
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
    public long getNumberOfAliveNeighbours(int x, int y) {
        return getNeighbors(new Cell(x, y)).stream().filter(aliveCells::contains).count();
    }
}
