package de.htwg.se.durak.model

import org.scalatest._

class CardDeckSpec extends WordSpec with Matchers {
  "A CardDeck" when {
    "add a new card to a deck" in {
      var deck = new CardDeck()
      deck = deck.addCard(Card(9, 3))
      deck.size should be(1)
    }

    "test double card in addCard" in {
      var deck = new CardDeck()
      deck = deck.addCard(Card(9, 3))
      deck = deck.addCard(Card(9, 3))
      deck.size should be(1)
    }

    "test double card in addCards" in {
      var deck = new CardDeck()
      deck = deck.addCards(List(Card(9,3), Card(9,3)))
      deck.size should be(1)
    }

    "remove a card by position" in {
      var deck = new CardDeck()
      deck = deck.addCard(Card(9, 3))
      deck = deck.addCard(Card(10, 3))
      deck = deck.removeCard(0)
      deck.size should be(1)
    }

    "remove a card by card" in {
      var deck = new CardDeck()
      deck = deck.addCard(Card(9, 3))
      deck = deck.addCard(Card(10, 3))
      deck = deck.addCard(Card(11, 3))
      deck = deck.removeCard(Card(10, 3))
      deck.deck.head.rank should be(9)
      deck.deck.last.rank should be(11)
      deck.size should be(2)
    }

    "pop a card" in {
      var deck = new CardDeck()
      deck = deck.addCards(List(Card(9,3), Card(10,3), Card(11,3)))
      deck.size should be(3)
      deck.deck.last.rank should be(11)
      val res = deck.pop()
      res._1.size should be(2)
      res._1.deck.last.rank should be(10)
      res._2.rank should be(11)
    }

    "test unapply" in {
      CardDeck.unapply(CardDeck(List(Card(9, 3)))).get should be(List(Card(9, 3)))
    }
  }
}