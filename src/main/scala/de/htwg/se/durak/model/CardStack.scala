package de.htwg.se.durak.model

import scala.collection.mutable.ListBuffer

class CardStack {
  var cards = new ListBuffer[Card]()
  var size: Int = 0
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
  }

  def addCard(): Unit = {
  }

  def changeCardPosition(from: Int, to: Int): Unit = {
  }

  def getAllCards(): ListBuffer[Card] = {
    cards
  }

  def popCard(): Unit = {
  }

  def popCards(numberOfCards: Int): Unit = {
  }

  def debugLog() : Unit = {
    for (card <- cards) {
      println(card.get())
    }
  }

  // delete all cards in cards
  def clear() : Unit = {

  }
}
