package de.htwg.se.durak.model

import scala.collection.mutable.ListBuffer

case class CardStack(size: Int = 0, var cards: ListBuffer[Card]) {
  def this(getSize: Int) {
    this(getSize, ListBuffer())
  }

  def this() {
    this(0)
  }

  def generateStack(): ListBuffer[Card] = {
    val y = 0
    val x = 0
    if(size == 48){
      for( x <- 2 until 14)
        for( y <- 1 to 4)
          cards += (new Card(x, y))
    }else if(size == 36){
      for( x <- 5 until 14)
        for( y <- 1 to 4)
          cards += (new Card(x, y))
    }
    cards = util.Random.shuffle(cards)
    cards
  }

  def removeCard(position: Int): Card = {
    if(position >= cards.size) throw new IndexOutOfBoundsException
    cards.remove(position)
  }

  def addCard(c:Card): ListBuffer[Card] = {
    cards += c
    cards
  }

  def changeCardPosition(from: Int, to: Int): Unit = {
    if(from >= cards.size || to >= cards.size || from < 0 || to < 0) throw new IndexOutOfBoundsException
    //todo: implement function and test
  }

  def getAllCards(): ListBuffer[Card] = {
    cards
  }

  def popCard(): Card = {
    cards.remove(cards.size - 1)
  }

  def popCards(numberOfCards: Int): List[Card] = {
    val a = Array.ofDim[Card](numberOfCards)
    for(i <- 0 until numberOfCards) a(i) = popCard()
    a.toList
  }

  // delete all cards in cards
  def clear() : Unit = {
    cards.clear()
  }

  def getSize:Int = cards.size
}