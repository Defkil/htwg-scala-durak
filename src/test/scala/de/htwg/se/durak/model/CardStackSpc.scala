package de.htwg.se.durak.model

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CardStackSpc extends WordSpec with Matchers {
  "A CardStack" when {
    "check adding and removing 3 cards to a stack" should {
      val stack = new CardStack(6)
      val cards = List(new Card(9, 3), new Card(2, 4), new Card(11, 1))
      for (c <- cards) stack.addCard(c)

      "size is amount of pushed Cards" in {
        stack.getSize should be(3)
      }
      "first pop ist last push" in {
        val c1 = stack.popCard()
        c1.toString() should be("Rang: Bube, Typ: Pik")
      }
      "second pop is second push" in {
        val c2 = stack.popCard()
        c2.toString() should be("Rang: 2, Typ: Herz")
      }
      "last pop ist first push" in {
        val c3 = stack.popCard()
        c3.toString() should be("Rang: 9, Typ: Kreuz")
      }
    }
    "Adding two Cards and removing both bei call popCards() mit argument 2" should {
      val stack = new CardStack(48)
      "Adding two cars" in {
        stack.addCard(new Card(2, 3))
        stack.addCard(new Card(11, 1))
      }
      /*"popping two cards in one method call" in {
        val cards = stack.popCards(2)
      }*/
      "the stack should be empty" in {
        stack.getSize should be(0)
      }
    }
    "check get all cards and clear" should {
      val stack = new CardStack()
      stack.addCard(new Card(11,2))
      stack.addCard(new Card(12,1))
      "return al cards as list" in {
        val cards = stack.getAllCards()
        cards(0).toString should be("Rang: Bube, Typ: Karo")
        cards(1).toString should be("Rang: Dame, Typ: Pik")
      }
      "method clear() makes CardStack empty" in {
        stack.clear()
        stack.getSize should be(0)
      }
    }
    "add and remove card" in {
      val stack = new CardStack(6)
      stack.addCard(new Card(1,1))
      stack.getSize should be(1)
      val card = stack.removeCard(0)
      stack.getSize should be(0)
      card.get should be(1,1)
    }
    "run generateStack 36" in {
      val stack = new CardStack(36)
      stack.generateStack()
      stack.getAllCards().length should be(36)
    }
    "run generateStack 48" in {
      val stack = new CardStack(48)
      stack.generateStack()
      stack.getAllCards().length should be(48)
    }
  }
}
