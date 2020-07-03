package de.htwg.se.durak.model.gameElementsComponent.gameElementsBaseImpl

import de.htwg.se.durak.model.gameElementsComponent.{CardDeckInterface, CardInterface, FieldInterface}

case class Field(var cardDeck: CardDeckInterface) extends FieldInterface {
  def this() {
    this(new CardDeck());
  }
  val size: Int = cardDeck.size
  val deck: List[CardInterface] = cardDeck.deck

  def addCard(card: CardInterface): Field =  Field(cardDeck.addCard(card))
}
