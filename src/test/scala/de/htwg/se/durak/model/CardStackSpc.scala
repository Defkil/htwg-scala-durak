package de.htwg.se.durak.model

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CardStackSpc extends WordSpec with Matchers {
  val stack = new CardStack(6)
  val cards = List(new Card(9,3),new Card(2,4),new Card(11,1))
  for(c <- cards) stack.addCard(c)

  stack.getSize should be(3)
  val c1 = stack.popCard()
  c1.toString() should be("Rang: Bube, Typ: Pik")
  val c2 = stack.popCard()
  c2.toString() should be("Rang: 2, Typ: Herz")
  val c3 = stack.popCard()
  c3.toString() should be("Rang: 9, Typ: Kreuz")
}
