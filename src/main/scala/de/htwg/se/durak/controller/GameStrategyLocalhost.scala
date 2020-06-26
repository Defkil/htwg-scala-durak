package de.htwg.se.durak.controller

import de.htwg.se.durak.model.{CardDeck, Field, GameData, TurnData}
import de.htwg.se.durak.utilities.TraitGameStrategy

//noinspection ScalaStyle
case class GameStrategyLocalhost() extends TraitGameStrategy {
  val roundFactory = new RoundFactory
  val gameTable = new GameTable
  def playerSelect(gameData: GameData, input: String): GameData = {
    val players = gameTable.createPlayers(input.split(" "))
    val (mainDeck, playerDecks) = gameTable.handOutCardsStart(
      CardDeck(gameTable generateDeck 36),
      gameTable.createPlayerDecks(players.length)
    )
    val trump: Int = mainDeck.deck.head.symbol
    val field = new Field
    val playerId: Int = gameTable.getFirstPlayer(playerDecks, trump)

    GameData(
      roundFactory.getInstance(10, Some(List(players(playerId).toString))),
      Some(TurnData (players, playerDecks, playerId, field, mainDeck, trump))
    )
  }

  /**
   * unterste Karte ist (0) und oberste (.size)
   * @param gameData
   * @param input
   * @return
   */
  def startGame(gameData: GameData, input: String): GameData = {
    GameData(roundFactory.getInstance(10, None), None)
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