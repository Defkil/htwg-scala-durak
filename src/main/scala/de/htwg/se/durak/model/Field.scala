package de.htwg.se.durak.model

import scala.collection.mutable.ListBuffer

case class Field(var cardDeck: CardDeck) {
  def this() {
    this(new CardDeck());
  }
  val size: Int = cardDeck.size
  val deck: List[Card] = cardDeck.deck

  //def addAttack(card: Card): ListBuffer[Card] = stack.addCard(card)

  /*def getData(): List[CardStack] = {
    if(stack.getSize == 0) return null
    var res = new ListBuffer[CardStack]()

    for(i <- 0 to stack.getSize by 2) {
      if(stack.getSize  % 2 == 0) {
          res += CardStack(2, ListBuffer(fieldList(i), fieldList(i + 1)))
      } else {
          val temp = if(stack.getSize  < i) null else fieldList(i)
          res += CardStack(2, ListBuffer(fieldList(i), temp))
      }
    }
    res.toList
  }*/

}
