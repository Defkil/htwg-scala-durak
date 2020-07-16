package de.htwg.se.durak.model.roundComponent.roundBaseImpl

import de.htwg.se.durak.model.gameElementsComponent.{CardDeckInterface, FieldInterface}
import de.htwg.se.durak.model.playerComponent.Player
import de.htwg.se.durak.model.roundComponent.{GameDataInterface, RoundDataInterface, RoundInterface, TurnDataInterface}

class Round extends RoundInterface {
  def createGameData(roundData: RoundDataInterface, turnData: Option[TurnDataInterface]): GameDataInterface = {
    GameData(roundData, turnData)
  }

  def createRoundData(siteID: Int,
                      validateInputList: List[String],
                      validateInput: Option[String => Boolean],
                      param: Option[List[String]]): RoundDataInterface = {
    RoundData(siteID, validateInputList, validateInput, param)
  }

  def createTurnData(players: List[Player], playerDecks: List[CardDeckInterface]
                     , currentPlayer: Int, defendPlayer: Int, field: FieldInterface
                     , mainDeck: CardDeckInterface, outDeck: CardDeckInterface, trump: Int): TurnDataInterface = {
    TurnData(players, playerDecks,currentPlayer, defendPlayer, field, mainDeck, outDeck, trump)
  }

  def createRoundData(siteID: Int,
                      validateInputList: List[String]): RoundDataInterface = {
    new RoundData(siteID, validateInputList)
  }
}
