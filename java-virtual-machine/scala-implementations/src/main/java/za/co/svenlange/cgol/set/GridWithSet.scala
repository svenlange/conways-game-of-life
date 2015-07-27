package za.co.svenlange.cgol.set

import CellExtensions._
import za.co.svenlange.gameoflife.{Cell, Grid}

import scala.collection.JavaConverters._
import scala.collection.mutable

class GridWithSet(aliveCells: mutable.HashSet[Cell]) extends Grid {

  def this() {
    this(new mutable.HashSet[Cell]())
  }

  override def getNextGeneration = {
    val survivors = aliveCells.filter(cell => getNumberOfAliveNeighbors(cell) == 2 || getNumberOfAliveNeighbors(cell) == 3)
    val cellsThatAreReborn = aliveCells.flatMap(_.neighbors).diff(aliveCells).filter(getNumberOfAliveNeighbors(_) == 3)
    new GridWithSet(survivors ++ cellsThatAreReborn)
  }

  override def getNumberOfAliveNeighbors(x: Int, y: Int) = {
    getNumberOfAliveNeighbors(new Cell(x, y))
  }

  def getNumberOfAliveNeighbors(cell: Cell) = {
    cell.neighbors.intersect(aliveCells).size.toLong
  }

  override def isCellAlive(x: Int, y: Int) = aliveCells.contains(new Cell(x, y))

  override def addAliveCell(x: Int, y: Int) = {
    aliveCells.add(new Cell(x, y))
  }

  override def getAliveCells = aliveCells.asJava

}