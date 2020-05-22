package de.htwg.se.durak.controller

import de.htwg.se.durak.model.{CardStack, Player}
import scala.collection.mutable.ListBuffer

case class GameTable() {
  val CARDS_PER_PLAYER = 6
  def createPlayerCardStack(players: List[Player]): List[CardStack] = {
    val stack = new ListBuffer[CardStack]()
    for(player <- players)
      stack += new CardStack(0)
    stack.toList
  }
  def handOutCards(players: List[Player], cardStackList: List[CardStack], cardStack: CardStack): Unit = {
    if(players.size != cardStackList.size) throw new Exception("Amout of players not like cardstacks per player")
    for(i <- 0 until players.size) {
      val stack = cardStackList(i)
      for(j <- 0 until CARDS_PER_PLAYER) {
        stack.addCard(cardStack.popCard())
      }
    }
  }
}