package de.htwg.se.durak.model.gameElementsComponent.gameElementsBaseImpl

import org.scalatest._

class GameElementsSpec extends WordSpec with Matchers {
  "Game Elements" when {
    "create a card" in {
      val gameElements = new GameElements()
      val card = gameElements.createCard(2, 1)
      card.rank should be(2)
      card.symbol should be(1)
    }

    "create an empty card deck" in {
      val gameElements = new GameElements()
      val cardDeck = gameElements.createCardDeck()
      cardDeck.deck.isEmpty should be(true)
    }

    "create a card deck with one card" in {
      val gameElements = new GameElements()
      val cardDeck = gameElements.createCardDeck(List(gameElements.createCard(2, 1)))
      cardDeck.deck.isEmpty should be(false)
    }

    "create an empty field" in {
      val gameElements = new GameElements()
      val field = gameElements.createField()
      field.deck.isEmpty should be(true)
    }

    "create a field with one card" in {
      val gameElements = new GameElements()
      val cardDeck = gameElements.createCardDeck(List(gameElements.createCard(2, 1)))
      val field = gameElements.createField(cardDeck)
      field.deck.isEmpty should be(false)
    }

    "test unapply" in {
      val gameElements = new GameElements()
      GameElements.unapply(gameElements)
    }
  }
}
