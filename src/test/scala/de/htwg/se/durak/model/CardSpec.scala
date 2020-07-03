package de.htwg.se.durak.model

import de.htwg.se.durak.model.gameElementsComponent.gameElementsBaseImpl.Card
import org.scalatest._

class CardSpec extends WordSpec with Matchers {
  "A Card" should {
    "check all possibilities by rank" in {
      var card = Card(2, 1)
      card.rank should be(2)
      card.symbol should be(1)
      card.toString should be("Rang: 2, Typ: Pik")
      card = Card(3, 2)
      card.rank should be(3)
      card.symbol should be(2)
      card.toString should be("Rang: 3, Typ: Karo")
      card = Card(4, 3)
      card.rank should be(4)
      card.symbol should be(3)
      card.toString should be("Rang: 4, Typ: Kreuz")
      card = Card(5, 4)
      card.rank should be(5)
      card.symbol should be(4)
      card.toString should be("Rang: 5, Typ: Herz")
      card = Card(6, 1)
      card.rank should be(6)
      card.symbol should be(1)
      card.toString should be("Rang: 6, Typ: Pik")
      card = Card(7, 1)
      card.rank should be(7)
      card.symbol should be(1)
      card.toString should be("Rang: 7, Typ: Pik")
      card = Card(8, 1)
      card.rank should be(8)
      card.symbol should be(1)
      card.toString should be("Rang: 8, Typ: Pik")
      card = Card(9, 1)
      card.rank should be(9)
      card.symbol should be(1)
      card.toString should be("Rang: 9, Typ: Pik")
      card = Card(10, 1)
      card.rank should be(10)
      card.symbol should be(1)
      card.toString should be("Rang: 10, Typ: Pik")
      card = Card(11, 1)
      card.rank should be(11)
      card.symbol should be(1)
      card.toString should be("Rang: Bube, Typ: Pik")
      card = Card(12, 1)
      card.rank should be(12)
      card.symbol should be(1)
      card.toString should be("Rang: Dame, Typ: Pik")
      card = Card(13, 1)
      card.rank should be(13)
      card.symbol should be(1)
      card.toString should be("Rang: König, Typ: Pik")
      card = Card(14, 1)
      card.rank should be(14)
      card.symbol should be(1)
      card.toString should be("Rang: Ass, Typ: Pik")
      card = Card(10,2)
      card.rank should be(10)
      card.symbol should be(2)
      card.toString should be("Rang: 10, Typ: Karo")
      card = Card(12,3)
      card.rank should be(12)
      card.symbol should be(3)
      card.toString should be("Rang: Dame, Typ: Kreuz")
      card = Card(14,4)
      card.rank should be(14)
      card.symbol should be(4)
      card.toString should be("Rang: Ass, Typ: Herz")
    }

    "test unapply" in {
      val card = new Card(12,3)
      Card.unapply(card).get should be((12, 3))
    }
  }
}
