package de.htwg.se.durak.model

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CardSpec extends WordSpec with Matchers {
  "A Card" when { "new" should {
    val card = new Card(2,1)
    "have get(): (rank, cardType)"  in {
      card.get() should be(2,1)
    }
    "have toString with words"  in {
      card.toString should be("Rang: 2, Typ: Pik")
    }
    "have weight 1" in {
      card.getWeigth() should be(1)
    }
  }}
  "Compare Cards to be equal" should {
    val c1 = new Card(11,3)
    val c2 = new Card(11,3)
    val c3 = new Card(9,3)
    "compare equal cards" in {
      c1.equals(c2) should be(true)
    }
    "compare unequal cards" in {
      c2.equals(c3) should be(false)
    }
  }
}
