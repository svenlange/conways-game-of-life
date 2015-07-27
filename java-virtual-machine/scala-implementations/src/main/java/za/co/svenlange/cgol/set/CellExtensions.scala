package za.co.svenlange.cgol.set

import za.co.svenlange.gameoflife.Cell

import scala.collection.mutable

object CellExtensions {

  implicit class CellExtensions(val cell: Cell) {
    def neighbors: mutable.HashSet[Cell] = {
      val x = cell.getX
      val y = cell.getY
      val center: Int = 0
      val north: Int = -1
      val south: Int = 1
      val west: Int = -1
      val east: Int = 1
      val neighbors = new mutable.HashSet[Cell]
      neighbors.add(new Cell(x + west, y + north))
      neighbors.add(new Cell(x + center, y + north))
      neighbors.add(new Cell(x + east, y + north))
      neighbors.add(new Cell(x + west, y + center))
      neighbors.add(new Cell(x + east, y + center))
      neighbors.add(new Cell(x + east, y + south))
      neighbors.add(new Cell(x + center, y + south))
      neighbors.add(new Cell(x + west, y + south))
      neighbors
    }
  }

}
