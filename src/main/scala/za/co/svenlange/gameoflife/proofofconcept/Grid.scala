package za.co.svenlange.gameoflife.proofofconcept

import java.util

import za.co.svenlange.gameoflife
import za.co.svenlange.gameoflife.Cell
import za.co.svenlange.gameoflife.CellExtensions._

import scala.collection.JavaConverters._
import scala.collection.mutable

class Grid extends za.co.svenlange.gameoflife.Grid {

  val aliveCells = new mutable.HashSet[Cell]()

  override def getNextGeneration: gameoflife.Grid = {
    val survivors = aliveCells.filter((getNumberOfAliveNeighbors(_) == 2) || (getNumberOfAliveNeighbors(_) == 3))

    // TODO dead cells with living neighbors == 3
    val cellsThatAreReborn = aliveCells.filter(???)

    new Grid(survivors ::: cellsThatAreReborn)
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
