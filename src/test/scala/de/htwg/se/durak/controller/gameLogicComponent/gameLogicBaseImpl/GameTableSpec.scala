package de.htwg.se.durak.controller.gameLogicComponent.gameLogicBaseImpl

import de.htwg.se.durak.model.gameElementsComponent.gameElementsBaseImpl.{CardDeck, GameElements}
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

    "handout cards" in {
      var playerDecks = List(new CardDeck(), new CardDeck())
      var mainDeck: CardDeck = CardDeck(gameTable.generateDeck(36))
      var (resPlayerDecks, resMainDeck) = gameTable.refuelPlayerDecks(playerDecks, mainDeck, 0)
      resPlayerDecks(0).deck.length should be(6)
    }
  }
}
