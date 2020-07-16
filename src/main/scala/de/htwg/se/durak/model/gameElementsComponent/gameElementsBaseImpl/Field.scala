package de.htwg.se.durak.model.gameElementsComponent.gameElementsBaseImpl

import de.htwg.se.durak.model.gameElementsComponent.{CardDeckInterface, CardInterface, FieldInterface}

/**
 * containing all cards being used in this round to attack and defend
 * @param cardDeck for storing all cards added to the Field
 */
case class Field(var cardDeck: CardDeckInterface) extends FieldInterface {
  def this() {
    this(new CardDeck());
  }
  val size: Int = cardDeck.size

  def addCard(card: CardInterface): FieldInterface =  Field(cardDeck.addCard(card))
}
