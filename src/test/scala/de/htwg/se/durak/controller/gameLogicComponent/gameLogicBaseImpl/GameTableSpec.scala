package de.htwg.se.durak.controller.gameLogicComponent.gameLogicBaseImpl

import de.htwg.se.durak.model.gameElementsComponent.gameElementsBaseImpl.GameElements
import de.htwg.se.durak.model.roundComponent.roundBaseImpl.Round
import org.scalatest._

class GameTableSpec extends WordSpec with Matchers {
  "GameTable" should {

  }

  "test unapply" in {
    val gameTable = new GameTable(new GameElements, new Round)
    GameTable.unapply(gameTable)
  }
}
