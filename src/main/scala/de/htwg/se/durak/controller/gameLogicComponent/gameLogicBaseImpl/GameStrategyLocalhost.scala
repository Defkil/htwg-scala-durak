package de.htwg.se.durak.controller.gameLogicComponent.gameLogicBaseImpl

import de.htwg.se.durak.controller.gameLogicComponent.GameStrategyInterface
import de.htwg.se.durak.model.gameElementsComponent.{FieldInterface, GameElementsInterface}
import de.htwg.se.durak.model.roundComponent.{GameDataInterface, RoundInterface, roundBaseImpl}

//noinspection ScalaStyle
case class GameStrategyLocalhost (elm: GameElementsInterface, round: RoundInterface) extends GameStrategyInterface {
  var gameTable: GameTable = GameTable(elm)
  var roundDataFactory: RoundDataFactory = new RoundDataFactory(round)
  /**
   * unterste Karte ist (0) und oberste (.size)
   * @param gameData
   * @param input
   * @return
   */
  def playerSelect(gameData: GameDataInterface, input: String): GameDataInterface = {
    val players = gameTable.createPlayers(input.split(" "))
    val (mainDeck, playerDecks) = gameTable.handOutCardsStart(
      elm.createCardDeck(gameTable generateDeck 36),
      gameTable.createPlayerDecks(players.length)
    )
    val trump: Int = mainDeck.deck.head.symbol
    val field: FieldInterface = elm.createField()
    val currentPlayer: Int = gameTable.getFirstPlayer(playerDecks, trump)
    val defendPlayer: Int = gameTable.getNextPlayer(currentPlayer, players.length)
    roundBaseImpl.GameData(
      roundDataFactory.getInstance(10, Some(List(players(currentPlayer).toString))),
      Some(round.createTurnData (players, playerDecks, currentPlayer, defendPlayer, field, mainDeck, trump))
    )
  }

  /**
   *
   * @param gameData
   * @param input
   * @return
   */
  def nextTurn(gameData: GameDataInterface, input: String): GameDataInterface = {
    val turnData = gameData.turnData.get
    var res = gameData

    // falls das Spielfeld leer ist
    if(turnData.field.size == 0) {
      val maxPossible = turnData.playerDecks(turnData.defendPlayer).deck.length - 1
      res = round.createGameData(
        round.createRoundData(11, gameTable.countTo(maxPossible)),
        gameData.turnData
      )
    }
    res
    //GameData(roundFactory.getInstance(11, Some(List("looasd"))), None)
  }

  def attackTurn(gameData: GameDataInterface, input: String): GameDataInterface = {
    //todo: get name from next user
    roundBaseImpl.GameData(roundDataFactory.getInstance(10, Some(List("lol"))), gameData.turnData)
  }

  def parseAttackTurn(gameData: GameDataInterface, input: String): GameDataInterface = {
    roundBaseImpl.GameData(roundDataFactory.getInstance(13, None), None)
  }
}