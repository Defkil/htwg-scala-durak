package de.htwg.se.durak.model.gameElementsComponent.gameElementsBaseImpl

import de.htwg.se.durak.model.gameElementsComponent.{CardDeckInterface, CardInterface, FieldInterface}
import org.scalatest._

class FieldSpec extends WordSpec with Matchers {
  "A field" when {
    "add card" in{
      var field: FieldInterface = new Field()
      field.cardDeck.size should be(0)
      field.size should be(0)
      field = field.addCard(new Card(4,4))
      field.size should be(1)
      field.cardDeck.deck.head.rank should be(4)
      field.cardDeck.deck.head.symbol should be(4)
    }

    "card in constructor" in{
      var testList: List[CardInterface] = List(new Card(4,4))
      var field: FieldInterface = new Field(new CardDeck(testList))
      field.size should be(1)
      field.cardDeck.deck.head.rank should be(4)
      field.cardDeck.deck.head.symbol should be(4)
    }

    "test unapply" in {
      var testList: List[CardInterface] = List(new Card(4,4))
      var cardDeck: CardDeckInterface = new CardDeck(testList)
      Field.unapply(Field(cardDeck)).get should be(cardDeck)
    }
  }
}