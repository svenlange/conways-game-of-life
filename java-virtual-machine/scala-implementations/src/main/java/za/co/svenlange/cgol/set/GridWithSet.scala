package za.co.svenlange.cgol.set

import java.util

import za.co.svenlange.cgol.CellExtensions._
import za.co.svenlange.gameoflife.{Cell, Grid}

import scala.collection.JavaConverters._
import scala.collection.mutable

class GridWithSet(aliveCells: mutable.HashSet[Cell]) extends Grid {

  def this() {
    this(new mutable.HashSet[Cell]())
  }

  override def getNextGeneration: Grid = {
    val survivors = aliveCells.filter(cell => getNumberOfAliveNeighbors(cell) == 2 || getNumberOfAliveNeighbors(cell) == 3)
    val cellsThatAreReborn = aliveCells.flatMap(_.neighbors).diff(aliveCells).filter(getNumberOfAliveNeighbors(_) == 3)
    val value = survivors ++ cellsThatAreReborn
    new GridWithSet(value)
  }

  override def getNumberOfAliveNeighbors(x: Int, y: Int): Long = {
    new Cell(x, y).neighbors.intersect(aliveCells).size.toLong
  }

  def getNumberOfAliveNeighbors(cell: Cell): Long = {
    cell.neighbors.intersect(aliveCells).size.toLong
  }

  override def isCellAlive(x: Int, y: Int): Boolean = aliveCells.contains(new Cell(x, y))

  override def addAliveCell(x: Int, y: Int): Unit = {
    aliveCells.add(new Cell(x, y))
  }

  override def getAliveCells: util.Collection[Cell] = aliveCells.asJava

}