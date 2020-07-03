package de.htwg.se.durak.model.gameElementsComponent.gameElementsBaseImpl

import de.htwg.se.durak.model.gameElementsComponent.{CardDeckInterface, CardInterface}

/**
 * CardDeck for handling multiple Cards in a List
 *
 * @constructor CardDeck with existing List
 * @param deck List of Cards for the new CardDeck
 */
case class CardDeck(var deck: List[CardInterface]) extends CardDeckInterface {
  def this() {
    this(List())
  }
  def size: Int = deck.size
  def addCard(card: CardInterface): CardDeckInterface = CardDeck((deck :+ card).distinct)
  def addCards(cards: List[CardInterface]): CardDeckInterface = CardDeck((deck ++ cards).distinct)

  def removeCard(position: Int): CardDeck = {
    if(position >= deck.size) throw new IndexOutOfBoundsException
    CardDeck(deck.take(position) ++ deck.drop(position + 1))
  }

  def removeCard(card: CardInterface): CardDeckInterface = CardDeck(deck.filterNot(cardFromDeck => cardFromDeck.equals(card)))

  def pop(): (CardDeckInterface, CardInterface) =  (CardDeck(deck.init), deck.last)
}