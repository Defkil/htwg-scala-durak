package de.htwg.se.durak.model.gameElementsComponent

/**
 * Provides an deck of cards
 *
 * Cards can be added, removed and maintained
 */
trait CardDeckInterface {

  /**
   * List of Cards
   *
   * @return List of cards
   */
  var deck: List[CardInterface]

  /**
   * Deck size
   *
   * @return Size of cards in the deck
   */
  def size: Int

  /**
   * Add one card to the deck
   *
   * @param card Card that should be added to the deck
   * @return New CardDeck instance
   */
  def addCard(card: CardInterface): CardDeckInterface

  /**
   * Add multiple cards to the deck
   *
   * @param cards List of Cards that should be added to the deck
   * @return New CardDeck instance
   */
  def addCards(cards: List[CardInterface]): CardDeckInterface

  /**
   * Remove a card based on the position
   *
   * @param position Position of the card that should be removed
   * @return New CardDeck instance
   */
  def removeCard(position: Int): CardDeckInterface

  /**
   * Remove the same card as in the parameter
   *
   * @param card Card wich should be removed
   * @return New CardDeck instance
   */
  def removeCard(card: CardInterface): CardDeckInterface

  /**
   * Remove last card and give it back
   *
   * @return New CardDeck and the last card
   */
  def pop(): (CardDeckInterface, CardInterface)
}
