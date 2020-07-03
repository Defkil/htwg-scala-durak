package de.htwg.se.durak.model.gameElementsComponent.gameElementsBaseImpl

import de.htwg.se.durak.model.gameElementsComponent.{CardDeckInterface, CardInterface, FieldInterface}

case class Field(var cardDeck: CardDeckInterface) extends FieldInterface {
  def this() {
    this(new CardDeck());
  }
  val size: Int = cardDeck.size

  def addCard(card: CardInterface): FieldInterface =  Field(cardDeck.addCard(card))
}
