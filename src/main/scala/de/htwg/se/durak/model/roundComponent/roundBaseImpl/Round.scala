package de.htwg.se.durak.model.roundComponent.roundBaseImpl

import de.htwg.se.durak.model.gameElementsComponent.{CardDeckInterface, FieldInterface}
import de.htwg.se.durak.model.playerComponent.Player
import de.htwg.se.durak.model.roundComponent.{GameDataInterface, RoundDataInterface, RoundInterface, TurnDataInterface}

/**
 * Handler for creating round elements
 */
class Round extends RoundInterface {
  /**
   * Create RoundData
   *
   * @param siteID ID of the selected site/game state
   * @param validateInputList function which returns a boolean dependent on the parameter
   * @param validateInput Possible inputs if [[RoundDataInterface.validateInput]] is None
   * @param param Optional list of string parameter
   * @return RoundData based on parameter
   */
  def createRoundData(siteID: Int,
                      validateInputList: List[String],
                      validateInput: Option[String => Boolean],
                      param: Option[List[String]]): RoundDataInterface = {
    RoundData(siteID, validateInputList, validateInput, param)
  }

  /**
   * Create RoundData with minimal parameter
   *
   * @param siteID ID of the selected site/game state
   * @param validateInputList function which returns a boolean dependent on the parameter
   * @return RoundData based on parameter
   */
  def createRoundData(siteID: Int,
                      validateInputList: List[String]): RoundDataInterface = {
    new RoundData(siteID, validateInputList)
  }

  /**
   * Create TurnData
   *
   * @param players List of active player
   * @param playerDecks List of the player decks
   * @param currentPlayer Current player ID
   * @param defendPlayer Player that defends
   * @param field Game field
   * @param mainDeck Main deck with remaining cards
   * @param outDeck Deck with cards that are outside
   * @param trump current game trump
   * @param turnType Current turn type
   * @return TurnData based on parameter
   */
  //noinspection ScalaStyle
  def createTurnData(players: List[Player],
                     playerDecks: List[CardDeckInterface]
                     , currentPlayer: Int,
                     defendPlayer: Int,
                     field: FieldInterface,
                     mainDeck: CardDeckInterface,
                     outDeck: CardDeckInterface,
                     trump: Int,
                     turnType: Int): TurnDataInterface = {
    TurnData(players, playerDecks,currentPlayer, defendPlayer, field, mainDeck, outDeck, trump, turnType)
  }

  /**
   * Create GameData
   *
   * @param roundData Data with actual site, possible inputs and custom parameter
   * @param turnData Data with all game information
   * @return GameData instance based on parameter
   */
  def createGameData(roundData: RoundDataInterface, turnData: Option[TurnDataInterface]): GameDataInterface = {
    GameData(roundData, turnData)
  }
}
