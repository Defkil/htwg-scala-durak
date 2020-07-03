package de.htwg.se.durak.controller.gameLogicComponent.gameLogicBaseImpl

import de.htwg.se.durak.controller.gameLogicComponent.GameStrategyInterface
import de.htwg.se.durak.model.gameElementsComponent.gameElementsBaseImpl.{CardDeck, Field}
import de.htwg.se.durak.model.{GameData, RoundData, TurnData}

//noinspection ScalaStyle
case class GameStrategyLocalhost(gameLogic: GameLogic) extends GameStrategyInterface {
  /**
   * unterste Karte ist (0) und oberste (.size)
   * @param gameData
   * @param input
   * @return
   */
  def playerSelect(gameData: GameData, input: String): GameData = {
    val players = gameLogic.gameTable.createPlayers(input.split(" "))
    val (mainDeck, playerDecks) = gameLogic.gameTable.handOutCardsStart(
      CardDeck(gameLogic.gameTable generateDeck 36),
      gameLogic.gameTable.createPlayerDecks(players.length)
    )
    val trump: Int = mainDeck.deck.head.symbol
    val field = new Field
    val currentPlayer: Int = gameLogic.gameTable.getFirstPlayer(playerDecks, trump)
    val defendPlayer: Int = gameLogic.gameTable.getNextPlayer(currentPlayer, players.length)
    GameData(
      RoundFactory.getInstance(10, Some(List(players(currentPlayer).toString))),
      Some(TurnData (players, playerDecks, currentPlayer, defendPlayer, field, mainDeck, trump))
    )
  }

  /**
   *
   * @param gameData
   * @param input
   * @return
   */
  def nextTurn(gameData: GameData, input: String): GameData = {
    val turnData = gameData.turnData.get
    var res = gameData

    // falls das Spielfeld leer ist
    if(turnData.field.size == 0) {
      val maxPossible = turnData.playerDecks(turnData.defendPlayer).deck.length - 1
      res = GameData(
        new RoundData(11, gameLogic.gameTable.countTo(maxPossible)),
        gameData.turnData
      )
    }
    res
    //GameData(roundFactory.getInstance(11, Some(List("looasd"))), None)
  }

  def attackTurn(gameData: GameData, input: String): GameData = {
    //todo: get name from next user
    GameData(RoundFactory.getInstance(10, Some(List("lol"))), gameData.turnData)
  }

  def parseAttackTurn(gameData: GameData, input: String): GameData = {
    GameData(RoundFactory.getInstance(13, None), None)
  }
}