package de.htwg.se.durak.controller

import de.htwg.se.durak.model.gameElementsComponent.gameElementsBaseImpl.{Card, CardDeck, Field}
import de.htwg.se.durak.utilities.old.GameLogic
import org.scalatest._

class AttackDefendSpec extends WordSpec with Matchers {
  val logic = new GameLogic
  val field = Field(new CardDeck())
  val trumpValue = 3

  val c1 = new Card(11,3)
  val c2 = new Card(4,2)
  val c3 = new Card(11,3)
  val c4 = new Card(11,3)

  //field.addAttack(c1)
  //logic.canAttack(field, c2)
}

