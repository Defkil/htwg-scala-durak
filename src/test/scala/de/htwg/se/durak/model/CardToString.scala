package de.htwg.se.durak.model

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CardToString extends WordSpec with Matchers {
  val c = new Card(9,2)
  c.toString should be("Rang: Bube, Typ: Kreuz")
}