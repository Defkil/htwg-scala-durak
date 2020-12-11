package de.htwg.se.durak.model.roundComponent

import de.htwg.se.durak.model.gameElementsComponent.{CardDeckInterface, FieldInterface}
import de.htwg.se.durak.model.playerComponent.Player

/**
 * TurnData with all game information
 */
trait TurnDataInterface {
  /**
   * List of all active player
   *
   * @return List of active player
   */
  val players: List[Player]

  /**
   * Decks of all player in a list
   *
   * @return List of the player decks
   */
  val playerDecks: List[CardDeckInterface]

  /**
   * Current player ID
   *
   * @return Current player ID
   */
  val currentPlayer: Int

  /**
   * Player that defends
   *
   * @return Player that defends
   */
  val defendPlayer: Int

  /**
   * Game field
   *
   * @return Game field
   */
  val field: FieldInterface

  /**
   * Main deck for new cards
   *
   * @return Main deck with remaining cards
   */
  val mainDeck: CardDeckInterface

  /**
   * Deck with cards that are outside
   *
   * @return Deck with cards that are outside
   */
  val outDeck: CardDeckInterface

  /**
   * Current game trump
   *
   * @return Current game trump
   */
  val trump: Int

  /**
   * Current turn type
   *
   * @return Current turn type
   */
  val turnType: Int
}
