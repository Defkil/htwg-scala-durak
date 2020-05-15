package de.htwg.se.durak.model

import scala.collection.mutable.ListBuffer

class CardStack {
  private var cards = new ListBuffer[Card]()
  private var size: Int = 48
  def this(getSize: Int, getCards: ListBuffer[Card]){
    this()
    cards = getCards
    size = getSize
  }
  def this(getSize: Int = 0){
    this()
    size = getSize
  }

  def generateStack(): ListBuffer[Card] = {
    val y = 0
    val x = 0
    if(size == 48){
      for( x <- 2 to 14)
        for( y <- 1 to 4)
          cards += (new Card(x, y))
    }else if(size == 36){
      for( x <- 6 to 14)
        for( y <- 1 to 4)
          cards += (new Card(x, y))
    }
    cards = util.Random.shuffle(cards)
    cards
  }

  def removeCard(position: Int): Unit = {
    if(position >= cards.size) throw new IndexOutOfBoundsException
    cards.remove(position)
  }

  def addCard(c:Card): Unit = {
    cards += c
  }

  def changeCardPosition(from: Int, to: Int): Unit = {
    if(from >= cards.size || to >= cards.size || from < 0 || to < 0) throw new IndexOutOfBoundsException
  }

  def getAllCards(): ListBuffer[Card] = {
    cards
  }

  def popCard(): Card = {
    cards.remove(cards.size - 1)
  }

  def popCards(numberOfCards: Int): Unit = {
    val a = Array.ofDim[Card](numberOfCards)
    for(i <- 0 until numberOfCards) a(i) = popCard()
    a.toList
  }

  def debugLog() : Unit = {
    for (card <- cards) {
      println(card.get())
    }
  }

  // delete all cards in cards
  def clear() : Unit = {
    cards.clear()
  }
}
