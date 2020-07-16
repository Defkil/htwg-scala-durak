package de.htwg.se.durak.controller.gameLogicComponent.gameLogicBaseImpl

import de.htwg.se.durak.model.gameElementsComponent.gameElementsBaseImpl.GameElements
import de.htwg.se.durak.model.roundComponent.roundBaseImpl.Round
import org.scalatest._

class GameTableSpec extends WordSpec with Matchers {
  "GameTable" should {
    val gameTable = new GameTable(new GameElements, new Round)
    "get right player" in {
      var nextPlayer = gameTable.getRightPlayer(0, 2)
      nextPlayer should be(1)
      nextPlayer = gameTable.getRightPlayer(1, 2)
      nextPlayer should be(0)
    }
  }
}
