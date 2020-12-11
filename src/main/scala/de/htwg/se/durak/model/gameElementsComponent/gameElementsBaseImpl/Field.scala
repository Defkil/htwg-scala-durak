package de.htwg.se.durak.model.gameElementsComponent.gameElementsBaseImpl

import de.htwg.se.durak.model.gameElementsComponent.{CardDeckInterface, CardInterface, FieldInterface}

/**
 * containing all cards being used in this round to attack and defend
 * @param cardDeck for storing all cards added to the Field
 */
case class Field(val cardDeck: CardDeckInterface) extends FieldInterface {
  /**
   * Empty Field
   *
   * @return return empty Field
   */
  def this() {
    this(new CardDeck(): CardDeckInterface);
  }

  /**
   * Add a card to the field
   *
   * @param card Card that should be added
   * @return new Field instance
   */
  def addCard(card: CardInterface): FieldInterface = Field(cardDeck.addCard(card))
}
