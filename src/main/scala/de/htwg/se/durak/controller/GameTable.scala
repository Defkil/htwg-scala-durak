package de.htwg.se.durak.controller

import de.htwg.se.durak.model.{CardStack, Player}

import scala.collection.mutable.ListBuffer

class GameTable() {
  def createPlayerCardStack(players: List[Player]): List[CardStack] = {
    val stack = new ListBuffer[CardStack]()
    for(player <- players)
      stack += new CardStack(0)
    stack.toList
  }
  def handOutCards(players: List[Player], cardStack:CardStack): Unit = {

  }
}