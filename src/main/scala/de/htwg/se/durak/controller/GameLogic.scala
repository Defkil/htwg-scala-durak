package de.htwg.se.durak.controller

import de.htwg.se.durak.model.{CardStack, Player}

import scala.util.Random

class GameLogic(gameTable: GameTable, playerList: List[Player]) {
  var trump: Int = -1
  var stackSize: Int = 0
  def start(lastWinner: Int): Unit = {
    //todo lastWinner==-1 need to check cards
    trump = 1 + Random.nextInt(3)
    val playerCards = gameTable.createPlayerCardStack(playerList)
    val hiddenCards = new CardStack(stackSize)
    hiddenCards.generateStack()
    val showedCards = new CardStack()

  }
  def start(): Unit = {
    start(-1)
  }

  def setStackSize(size: Int): Unit = {
    stackSize = size
  }
}