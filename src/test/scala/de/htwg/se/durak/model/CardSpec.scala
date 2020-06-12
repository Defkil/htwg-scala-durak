package de.htwg.se.durak.model

import org.scalatest._
import org.junit.runner.{Runner, RunWith}
import org.scalatest.matchers.should.Matchers

@RunWith(classOf[Runner])
class CardSpec extends WordSpec with Matchers {
  "A Card" when { "new" should {
    val card = new Card(2,1)
    "gave getRank(): Int" in {
      card.getRank should be(2)
    }
    "have get(): (rank, cardType)"  in {
      card.get should be(2,1)
      val (resRank, resType) = card.get()
      resRank should be(2)
      resType should be(1)
    }
    "have toString with words"  in {
      card.toString should be("Rang: 2, Typ: Pik")
    }
    "have weight and to string" in {
      var card = new Card(2, 1)
      card.getWeight should be(2)
      card.toString should be("Rang: 2, Typ: Pik")
      card = new Card(3, 1)
      card.getWeight should be(3)
      card.toString should be("Rang: 3, Typ: Pik")
      card = new Card(4, 1)
      card.getWeight should be(4)
      card.toString should be("Rang: 4, Typ: Pik")
      card = new Card(5, 1)
      card.getWeight should be(5)
      card.toString should be("Rang: 5, Typ: Pik")
      card = new Card(6, 1)
      card.getWeight should be(6)
      card.toString should be("Rang: 6, Typ: Pik")
      card = new Card(7, 1)
      card.getWeight should be(7)
      card.toString should be("Rang: 7, Typ: Pik")
      card = new Card(8, 1)
      card.getWeight should be(8)
      card.toString should be("Rang: 8, Typ: Pik")
      card = new Card(9, 1)
      card.getWeight should be(9)
      card.toString should be("Rang: 9, Typ: Pik")
      card = new Card(10, 1)
      card.getWeight should be(10)
      card.toString should be("Rang: 10, Typ: Pik")
      card = new Card(11, 1)
      card.getWeight should be(11)
      card.toString should be("Rang: Bube, Typ: Pik")
      card = new Card(12, 1)
      card.getWeight should be(12)
      card.toString should be("Rang: Dame, Typ: Pik")
      card = new Card(13, 1)
      card.getWeight should be(13)
      card.toString should be("Rang: König, Typ: Pik")
      card = new Card(14, 1)
      card.getWeight should be(14)
      card.toString should be("Rang: Ass, Typ: Pik")
      card = new Card(10,2)
      card.getWeight should be(24)
      card.toString should be("Rang: 10, Typ: Karo")
      card = new Card(12,3)
      card.getWeight should be(40)
      card.toString should be("Rang: Dame, Typ: Kreuz")
      card = new Card(14,4)
      card.getWeight should be(56)
      card.toString should be("Rang: Ass, Typ: Herz")
    }
  }}
}
