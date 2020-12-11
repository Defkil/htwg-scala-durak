package de.htwg.se.durak.model.gameElementsComponent

/**
 * Handler for creating game elements
 */
trait GameElementsInterface {

  /**
   * Create a new card
   *
   * @param rank Rank of the card
   * @param symbol Symbol of the card
   * @return Card with given parameter
   */
  def createCard(rank: Int, symbol: Int): CardInterface

  /**
   * Create a empty card deck
   *
   * @return empty CardDeck
   */
  def createCardDeck(): CardDeckInterface

  /**
   * Create a CardDeck with given parameters
   *
   * @param deck List of cards which should be added
   * @return CardDeck instance with given cards
   */
  def createCardDeck(deck: List[CardInterface]): CardDeckInterface

  /**
   * create a empty field
   *
   * @return empty field
   */
  def createField(): FieldInterface

  /**
   * Create a Field with given parameters
   *
   * @param cardDeck CardDeck which should be added to the field
   * @return Field instance with the CardDeck from the parameters
   */
  def createField(cardDeck: CardDeckInterface): FieldInterface
}
