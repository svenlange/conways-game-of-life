package za.co.svenlange.cgol.set

import java.util

import za.co.svenlange.gameoflife.{Cell, Grid}

import scala.collection.JavaConverters._
import scala.collection.mutable

class GridWithSet(aliveCells: mutable.HashSet[Cell]) extends Grid {

  def this() {
    this(new mutable.HashSet[Cell]())
  }

  override def getNextGeneration: GridWithSet = {
    val survivors = aliveCells.filter(cell => getNumberOfAliveNeighbors(cell) == 2 || getNumberOfAliveNeighbors(cell) == 3)
    val cellsThatAreReborn = aliveCells.flatMap(neighbors).diff(aliveCells).filter(getNumberOfAliveNeighbors(_) == 3)
    new GridWithSet(survivors ++ cellsThatAreReborn)
  }

  override def getNumberOfAliveNeighbors(x: Int, y: Int): Long = {
    getNumberOfAliveNeighbors(new Cell(x, y))
  }

  def getNumberOfAliveNeighbors(cell: Cell): Long = {
    neighbors(cell).intersect(aliveCells).size.toLong
  }

  override def isCellAlive(x: Int, y: Int): Boolean = aliveCells.contains(new Cell(x, y))

  override def addAliveCell(x: Int, y: Int): Unit = {
    aliveCells.add(new Cell(x, y))
  }

  override def getAliveCells: util.Set[Cell] = aliveCells.asJava

  def neighbors(cell: Cell): Set[Cell] = {
    val x = cell.getX
    val y = cell.getY
    val center: Int = 0
    val north: Int = -1
    val south: Int = 1
    val west: Int = -1
    val east: Int = 1

    Set(
      new Cell(x + west, y + north),
      new Cell(x + center, y + north),
      new Cell(x + east, y + north),
      new Cell(x + west, y + center),
      new Cell(x + east, y + center),
      new Cell(x + east, y + south),
      new Cell(x + center, y + south),
      new Cell(x + west, y + south)
    )
  }

}