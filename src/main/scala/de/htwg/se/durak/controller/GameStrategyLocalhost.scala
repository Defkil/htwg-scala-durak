package de.htwg.se.durak.controller

import de.htwg.se.durak.model.{CardDeck, Field, GameData, RoundData, TurnData}
import de.htwg.se.durak.utilities.TraitGameStrategy

//noinspection ScalaStyle
case class GameStrategyLocalhost() extends TraitGameStrategy {
  val roundFactory = new RoundFactory
  val gameTable = new GameTable

  /**
   * unterste Karte ist (0) und oberste (.size)
   * @param gameData
   * @param input
   * @return
   */
  def playerSelect(gameData: GameData, input: String): GameData = {
    val players = gameTable.createPlayers(input.split(" "))
    val (mainDeck, playerDecks) = gameTable.handOutCardsStart(
      CardDeck(gameTable generateDeck 36),
      gameTable.createPlayerDecks(players.length)
    )
    val trump: Int = mainDeck.deck.head.symbol
    val field = new Field
    val currentPlayer: Int = gameTable.getFirstPlayer(playerDecks, trump)
    val defendPlayer: Int = gameTable.getNextPlayer(currentPlayer, players.length)
    GameData(
      roundFactory.getInstance(10, Some(List(players(currentPlayer).toString))),
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
        RoundData(11, Some((param:String) => param.matches("[0-" + maxPossible + "]"))
          , Some(List(", " + gameTable.countTo(maxPossible))), None),
        gameData.turnData
      )
    }
    res
    //GameData(roundFactory.getInstance(11, Some(List("looasd"))), None)
  }

  def attackTurn(gameData: GameData, input: String): GameData = {
    //todo: get name from next user
    GameData(roundFactory.getInstance(10, Some(List("lol"))), gameData.turnData)
  }

  def parseAttackTurn(gameData: GameData, input: String): GameData = {
    GameData(roundFactory.getInstance(13, None), None)
  }
}













/*var turnData: Option[TurnData] = None
    val roundData = runtime.roundData.siteID match {
      case 0 => input match {
        case "0" => runtime.roundFactory.getInstance(3, None)
        case "1" => runtime.roundFactory.getInstance(1, None)
        case "3" => runtime.roundFactory.getInstance(-1, None)
      }
      case 1 => runtime.roundFactory.getInstance(2, None)
      case 2 =>
        runtime.screenSize = input.toInt
        runtime.roundFactory.getInstance(0, None)
      case 3 => // player name select
        //todo add player
        //todo start game
        //turnData = logic.initiateGame(regex.findAllIn(roundData.param).toList)
        runtime.roundFactory.getInstance(10, Some(List("Static Name")))
      case 10 => // next turn
        //todo implementation
        runtime.roundFactory.getInstance(11, None)
      case 11 => // attacker
        //todo implementation
        runtime.roundFactory.getInstance(10, Some(List("Static Name")))
      case 12 => // defender
        //todo implementation
        runtime.roundFactory.getInstance(13, None)
      case 13 => // finished
        //todo implementation
        runtime.roundFactory.getInstance(0, None)
    }*/