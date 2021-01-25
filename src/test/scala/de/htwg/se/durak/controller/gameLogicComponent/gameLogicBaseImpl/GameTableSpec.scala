package de.htwg.se.durak.controller.gameLogicComponent.gameLogicBaseImpl

import de.htwg.se.durak.model.gameElementsComponent.gameElementsBaseImpl.{Card, GameElements}
import de.htwg.se.durak.model.playerComponent.Player
import de.htwg.se.durak.model.roundComponent.roundBaseImpl.{Round, TurnData}
import org.scalatest._

class GameTableSpec extends WordSpec with Matchers {
  "GameTable" should {
    "add Spacer" in {
      val gameTable = new GameTable(new GameElements, new Round)
      val gameElements = new GameElements
      val turnData = gameTable.addSpacer(TurnData(
        List(Player("PlayerA"), Player("PlayerB")),
        List(gameElements.createCardDeck(List(new Card(5,1), new Card(6,1)))),
        0,
        1,
        gameElements.createField(),
        gameElements.createCardDeck(List(new Card(4,1))),
        gameElements.createCardDeck(),
        1,
        0
      ))
      turnData.field.cardDeck.deck.last.rank should be(-1)
      turnData.field.cardDeck.deck.last.symbol should be(-1)
    }
    "canCardDefend" in {
      val gameTable = new GameTable(new GameElements, new Round)
      gameTable.canCardDefend(Card(3, 2), Card(2, 1), 1) should be(true)
      gameTable.canCardDefend(Card(2, 2), Card(3, 2), 1) should be(true)
    }

    "test unapply" in {
      val gameTable = new GameTable(new GameElements, new Round)
      GameTable.unapply(gameTable)
    }
  }

}
