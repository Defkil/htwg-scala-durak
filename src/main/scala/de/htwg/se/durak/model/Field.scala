package de.htwg.se.durak.model

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

case class Field(var fieldList:ListBuffer[Card]) {
  def this() {
    this(new ListBuffer[Card]());
  }
  val size = 0

  def addAttack(card: Card): Field = {
    fieldList += card
    Field(fieldList)
  }

  def getData(): List[CardStack] = {
    if(fieldList.isEmpty) return null
    var res = new ListBuffer[CardStack]()

    for(i <- 0 to fieldList.length by 2) {
      if(fieldList.length % 2 == 0) {
          res += CardStack(2, ListBuffer(res(i), res(i + 1)))
      } else {
          val temp = if(fieldList.length < i) null else fieldList(i)
          res += CardStack(2, ListBuffer(res(i), temp))
      }
    }
    res.toList
  }

}
