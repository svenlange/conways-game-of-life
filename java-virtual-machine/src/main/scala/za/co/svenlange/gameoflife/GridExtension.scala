package za.co.svenlange.gameoflife

import scala.collection.mutable
import za.co.svenlange.gameoflife.CellExtensions._

object GridExtension {

  implicit class GridExtension(grid: Grid) {
    def survivingCells:mutable.HashSet[Cell] = {
      grid.aliveCells
    }
  }

}
