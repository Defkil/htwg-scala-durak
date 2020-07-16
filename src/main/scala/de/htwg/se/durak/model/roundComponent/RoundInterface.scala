package de.htwg.se.durak.model.roundComponent

import de.htwg.se.durak.model.gameElementsComponent.{CardDeckInterface, FieldInterface}
import de.htwg.se.durak.model.playerComponent.Player

trait RoundInterface {
  def createRoundData(siteID: Int,
                      validateInputList: List[String],
                      validateInput: Option[String => Boolean],
                      param: Option[List[String]]): RoundDataInterface

  def createRoundData(siteID: Int,
                      validateInputList: List[String]): RoundDataInterface

  def createTurnData(players: List[Player], playerDecks: List[CardDeckInterface]
                     , currentPlayer: Int, defendPlayer: Int, field: FieldInterface
                     , mainDeck: CardDeckInterface, outDeck: CardDeckInterface, trump: Int): TurnDataInterface

  def createGameData(roundData: RoundDataInterface, turnData: Option[TurnDataInterface]): GameDataInterface
}
