package de.htwg.se.durak.model

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CardSpec extends WordSpec with Matchers {
  val cards = Array.ofDim[Card](52)
  var index = 0
  for(i <- 2 to 14) {
    for (j <- 1 to 4) {
      cards(index) = new Card(i,j)
      index += 1
    }
  }

  val ranks = List("2", "3", "4", "5", "6", "7", "8", "9", "10", "Bube", "Dame", "König", "Ass")
  val cardTypes = List("Pik", "Karo", "Kreuz", "Herz")

  var index2 = 0
  for(i <- 0 to 12) {
    for (j <- 0 to 3) {
      cards(index2).toString should be("Rang: " + ranks(i) + ", Typ: " + cardTypes(j))
      index2 += 1
    }
  }
}