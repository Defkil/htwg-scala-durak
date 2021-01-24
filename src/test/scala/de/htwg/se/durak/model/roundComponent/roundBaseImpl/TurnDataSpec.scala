package de.htwg.se.durak.model.roundComponent.roundBaseImpl

import de.htwg.se.durak.model.gameElementsComponent.gameElementsBaseImpl.{Card, GameElements}
import de.htwg.se.durak.model.playerComponent.Player
import org.scalatest._

class TurnDataSpec extends WordSpec with Matchers {
  "TurnData" should {
    val gameElements = new GameElements()
    "create and add a card" in {
      var turnData = new TurnData(
        List(Player("PlayerA"), Player("PlayerB")),
        List(gameElements.createCardDeck(List(new Card(5,1), new Card(6,1)))),
        0,
        1,
        gameElements.createField(),
        gameElements.createCardDeck(List(new Card(4,1))),
        gameElements.createCardDeck(),
        1,
        0
      )

      turnData = turnData.addCard(Card(4, 1))
      turnData.field.cardDeck.deck.head.toString should equal("Rang: 4, Typ: Pik")
    }
    "test unapply" in {
      val turnData = new TurnData(
        List(Player("PlayerA"), Player("PlayerB")),
        List(gameElements.createCardDeck(List(new Card(5,1), new Card(6,1)))),
        0,
        1,
        gameElements.createField(),
        gameElements.createCardDeck(List(new Card(4,1))),
        gameElements.createCardDeck(),
        1,
        0
      )
      TurnData.unapply(turnData)
    }
  }
}
