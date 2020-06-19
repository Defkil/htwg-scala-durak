package de.htwg.se.durak.controller

import de.htwg.se.durak.model.{Card, CardStack, Field, Player, TurnData}

import scala.collection.mutable.ListBuffer

case class GameLogic() {
  /*für die abgabe auskommentiert
  val CARDS_PER_PLAYER = 6

  def createPlayerCardStack(numberOfplayers:Int): List[CardStack] = {
    val stack = new ListBuffer[CardStack]()
    for(player <- 0 until numberOfplayers) stack += new CardStack(0)
    stack.toList
  }
  def handOutCards(cardStackList: List[CardStack], cardStack: CardStack): Unit = {
    for(i <- cardStackList) {
      for(j <- 0 until CARDS_PER_PLAYER) {
        i.addCard(cardStack.popCard())
      }
    }
  }

  def createplayers(strings: Array[String]): List[Player] = {
    val l = new ListBuffer[Player]
    for(s <- strings) l += new Player(s)
    l.toList
  }

  def initiateGame(paramData : Array[String]) = {
    val stack = new CardStack(48)
    stack.generateStack()
    val players = createplayers(paramData)
    val stacks = createPlayerCardStack(players.size)

    handOutCards(stacks, stack)

    val field = new Field(new CardStack())
    new TurnData(players, stacks, 0, field, "", stack)
  }

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

  def addCardToField(oldTurnData : TurnData, c : Int) : TurnData = {
    val activePlayer = oldTurnData.playerId
    val playerStacks = oldTurnData.playerStacks.toArray
    val card = playerStacks(activePlayer).removeCard(c)
    val newField = oldTurnData.field.copy()
    val mainStack = oldTurnData.mainStack.copy()
    newField.addAttack(card)
    def nextPlayer:Int = if (activePlayer == oldTurnData.players.size - 1)  0 else activePlayer + 1
    TurnData(oldTurnData.players, playerStacks.toList, nextPlayer, newField, oldTurnData.msg, mainStack)
  }*/
}