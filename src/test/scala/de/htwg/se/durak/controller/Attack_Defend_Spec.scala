package de.htwg.se.durak.controller

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

import de.htwg.se.durak.controller.GameLogic
import de.htwg.se.durak.model.{CardStack, Card, Field}

@RunWith(classOf[JUnitRunner])
class Attack_Defend_Spec extends WordSpec with Matchers {
  val logic = new GameLogic
  val field = Field(new de.htwg.se.durak.model.CardStack())
  val trumpValue = 3

  val c1 = new Card(11,3)
  val c2 = new Card(4,2)
  val c3 = new Card(11,3)
  val c4 = new Card(11,3)

  field.addAttack(c1)
  logic.canAttack(field, c2)
}

