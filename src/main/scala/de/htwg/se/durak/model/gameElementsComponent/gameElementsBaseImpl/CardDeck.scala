package de.htwg.se.durak.model.gameElementsComponent.gameElementsBaseImpl

import de.htwg.se.durak.model.gameElementsComponent.{CardDeckInterface, CardInterface}

/**
 * CardDeck for handling multiple Cards in a List
 *
 * @constructor CardDeck with existing List
 * @param deck List of Cards for the new CardDeck
 */
case class CardDeck(var deck: List[CardInterface]) extends CardDeckInterface {
  /**
   * Empty CardDeck
   *
   * @return return empty CardDeck
   */
  def this() {
    this(List())
  }

  /**
   * Deck size
   *
   * @return Size of cards in the deck
   */
  def size: Int = deck.size

  /**
   * Add one card to the deck
   *
   * @param card Card that should be added to the deck
   * @return New CardDeck instance
   */
  def addCard(card: CardInterface): CardDeckInterface = CardDeck((deck :+ card).distinct)

  /**
   * Add multiple cards to the deck
   *
   * @param cards List of Cards that should be added to the deck
   * @return New CardDeck instance
   */
  def addCards(cards: List[CardInterface]): CardDeckInterface = CardDeck((deck ++ cards).distinct)

  /**
   * Remove a card based on the position
   *
   * @param position Position of the card that should be removed
   * @return New CardDeck instance
   */
  def removeCard(position: Int): CardDeck = {
    if(position >= deck.size) throw new IndexOutOfBoundsException
    CardDeck(deck.take(position) ++ deck.drop(position + 1))
  }

  /**
   * Remove the same card as in the parameter
   *
   * @param card Card wich should be removed
   * @return New CardDeck instance
   */
  def removeCard(card: CardInterface): CardDeckInterface = CardDeck(deck.filterNot(cardFromDeck => cardFromDeck.equals(card)))

  /**
   * Remove last card and give it back
   *
   * @return New CardDeck and the last card
   */
  def pop(): (CardDeckInterface, CardInterface) =  (CardDeck(deck.init), deck.last)
}