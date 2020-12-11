package de.htwg.se.durak.model.roundComponent.roundBaseImpl

import de.htwg.se.durak.model.gameElementsComponent.gameElementsBaseImpl.Card
import de.htwg.se.durak.model.gameElementsComponent.{CardDeckInterface, FieldInterface}
import de.htwg.se.durak.model.playerComponent.Player
import de.htwg.se.durak.model.roundComponent.TurnDataInterface

/**
 * TurnData with all game information
 *
 * turnType = 0  Default attack/defense
 * turnType = 1  Attacker skipped
 * turnType = 2  Defender takes
 *
 * @param players List of active player
 * @param playerDecks List of the player decks
 * @param currentPlayer Current player ID
 * @param defendPlayer Player that defends
 * @param field Game field
 * @param mainDeck Main deck with remaining cards
 * @param outDeck Deck with cards that are outside
 * @param trump Current game trump
 * @param turnType Current turn type
 */
case class TurnData (
  players: List[Player],
  playerDecks: List[CardDeckInterface],
  currentPlayer: Int,
  defendPlayer: Int,
  field: FieldInterface,
  mainDeck: CardDeckInterface,
  outDeck: CardDeckInterface,
  trump: Int,
  turnType: Int
) extends TurnDataInterface{
  /**
   * Add card to the game field
   *
   * @param card card that should be added
   * @return TurnData with new card in field
   */
  def addCard(card: Card): TurnData = TurnData(
    players,
    playerDecks,
    currentPlayer,
    defendPlayer,
    field.addCard(card),
    mainDeck,
    outDeck,
    trump,
    turnType
  )
}