package de.htwg.se.durak.controller

import de.htwg.se.durak.model.{Card, CardDeck, Player}
import org.scalatest._

import scala.collection.mutable.ListBuffer

class GameTableSpec extends WordSpec with Matchers {
  "A GameTable" when { "new" should {
    /*val table = new GameTable()
    val player1 = new Player("Alex")
    val player2 = new Player("Ben")
    "have two empty player CardStacks" in {
      val players = table.createPlayerCardStack(List(player1, player2))
      players.length should be(2)
      players(0).getSize should be(0)
      players(1).getSize should be(0)
    }

    val cards = ListBuffer[Card]()
    cards.append(
      new Card(2,1),new Card(3,1),new Card(4,1),
      new Card(5,1),new Card(6,1),new Card(7,1),
      new Card(2,2),new Card(3,2),new Card(4,2),
      new Card(5,2),new Card(6,2),new Card(7,2)
    )
    val cardStack = new CardStack(12, cards)
    val playerList = List(player1, player2)
    val playerCards = table.createPlayerCardStack(playerList)
    "handout 12 cards to 2 player" in {
      table.handOutCards(playerCards, cardStack)
      playerCards(0).popCard().toString should be("Rang: 2, Typ: Karo")
      playerCards(0).popCard().toString should be("Rang: 3, Typ: Karo")
      playerCards(0).popCard().toString should be("Rang: 4, Typ: Karo")
      playerCards(0).popCard().toString should be("Rang: 5, Typ: Karo")
      playerCards(0).popCard().toString should be("Rang: 6, Typ: Karo")
      playerCards(0).popCard().toString should be("Rang: 7, Typ: Karo")

      playerCards(1).popCard().toString should be("Rang: 2, Typ: Pik")
      playerCards(1).popCard().toString should be("Rang: 3, Typ: Pik")
      playerCards(1).popCard().toString should be("Rang: 4, Typ: Pik")
      playerCards(1).popCard().toString should be("Rang: 5, Typ: Pik")
      playerCards(1).popCard().toString should be("Rang: 6, Typ: Pik")
      playerCards(1).popCard().toString should be("Rang: 7, Typ: Pik")
    }
  */}}
}

