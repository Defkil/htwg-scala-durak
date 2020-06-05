package de.htwg.se.durak.controller

import de.htwg.se.durak.model.{Field, Card}

import de.htwg.se.durak.model.{CardStack, Player}

import scala.util.Random

case class GameLogic() {
  def canAttack(card: Card, field: Field): Boolean = {
    if (field.size == 0) true
    else {
      for (c <- field.stack.cards) {
        if (c.rank == card.rank) true
      }
      false
    }
  }
    def canDefend(defeat: Card, field: Field, trumpValue: Int): Boolean = {
      if (field.size % 2 != 1) throw new RuntimeException("No attacking card")
      val attack = field.stack.peek()

      def isTrump(c: Card): Boolean = c.cardType == trumpValue

      attack.rank > defeat.rank || !isTrump(attack) && isTrump(defeat)
    }
}