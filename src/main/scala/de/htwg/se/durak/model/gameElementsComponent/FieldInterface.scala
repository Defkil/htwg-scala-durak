package de.htwg.se.durak.model.gameElementsComponent

/**
 * Custom CardDeck for the game field
 */
trait FieldInterface {
  /**
   * Fields CardDeck
   *
   * @return CardDeck of the field
   */
  val cardDeck: CardDeckInterface

  /**
   * Fields main deck
   *
   * @return List of cards in the field
   */
  val deck: List[CardInterface] = cardDeck.deck

  /**
   * Fields deck size
   *
   * @return Number of cards in the field
   */
  val size: Int = deck.size

  /**
   * Add a card to the field
   *
   * @param card Card that should be added
   * @return new Field instance
   */
  def addCard(card: CardInterface): FieldInterface
}
