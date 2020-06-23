package de.htwg.se.durak.controller

import de.htwg.se.durak.model.{CardDeck, Player}
import scala.collection.mutable.ListBuffer

case class GameTable() {
  val CARDS_PER_PLAYER = 6
  /*def createPlayerCardStack(players: List[Player]): List[CardStack] = {
    val stack = new ListBuffer[CardStack]()
    for(player <- players)
      stack += new CardStack(0)
    stack.toList
  }
  def handOutCards(cardStackList: List[CardStack], cardStack: CardStack): Unit = {
    for(i <- cardStackList) {
      for(j <- 0 until CARDS_PER_PLAYER) {
        i.addCard(cardStack.popCard())
      }
    }
  }*/
}