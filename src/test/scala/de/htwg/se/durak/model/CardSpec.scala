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
}
