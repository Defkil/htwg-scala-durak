package de.htwg.se.durak.controller.gameLogicComponent.gameLogicBaseImpl

import de.htwg.se.durak.controller.gameLogicComponent.GameStrategyInterface
import de.htwg.se.durak.model.gameElementsComponent.{CardDeckInterface, FieldInterface, GameElementsInterface}
import de.htwg.se.durak.model.roundComponent.{GameDataInterface, RoundInterface, roundBaseImpl}

//noinspection ScalaStyle
case class GameStrategyLocalhost (elm: GameElementsInterface, round: RoundInterface) extends GameStrategyInterface {
  var gameTable: GameTable = GameTable(elm, round)
  var roundDataFactory: RoundDataFactory = new RoundDataFactory(round)
  /**
   * unterste Karte ist (0) und oberste (.size)
   * @param gameData
   * @param input
   * @return
   */
  def playerSelect(gameData: GameDataInterface, input: String): GameDataInterface = {
    println("playerSelect")
    val players = gameTable.createPlayers(input.split(" "))
    val (mainDeck, playerDecks) = gameTable.handOutCardsStart(
      elm.createCardDeck(gameTable generateDeck 36),
      gameTable.createPlayerDecks(players.length)
    )
    val trump: Int = mainDeck.deck.head.symbol
    val field: FieldInterface = elm.createField()
    val outDeck: CardDeckInterface = elm.createCardDeck()
    val currentPlayer: Int = gameTable.getFirstPlayer(playerDecks, trump)
    val defendPlayer: Int = gameTable.getNextPlayer(currentPlayer, players.length)
    round.createGameData(
      roundDataFactory.getInstance(10),
      Some(round.createTurnData(players, playerDecks, currentPlayer, defendPlayer, field, mainDeck, outDeck, trump))
    )
  }

  /**
   *
   * @param gameData
   * @param input
   * @return
   */
  def nextTurn(gameData: GameDataInterface, input: String): GameDataInterface = {
    println("nextTurn")
    val turnData = gameData.turnData.get
    val playerDecks = turnData.playerDecks(turnData.currentPlayer)
    var res = gameData

    if(turnData.currentPlayer == turnData.defendPlayer) { // Verteidiger Runde setzen
      res = round.createGameData(
        round.createRoundData(12,  gameTable.getPossibleDefendTurns(turnData.field.cardDeck.deck.last, playerDecks, turnData.trump)),
        gameData.turnData
      )
    } else if(turnData.field.size == 0) { // falls das Spielfeld leer ist, ist auch kein skip möglich
      res = round.createGameData(
        round.createRoundData(11, gameTable.countTo(playerDecks.deck.length)),
        gameData.turnData
      )
    } else {
      //todo handle skip?
      res = round.createGameData(
        round.createRoundData(11, gameTable.getPossibleAttackTurns(turnData.field, playerDecks)),
        gameData.turnData
      )
      // Alle möglichkeiten für weitere Angriffe berechnen
    }



    res
    //GameData(roundFactory.getInstance(11, Some(List("looasd"))), None)
  }

  def attackTurn(gameData: GameDataInterface, input: String): GameDataInterface = {
    println("attackTurn")
    var turnData = gameData.turnData.get
    if(input == "s") {
      //todo handle skips
    } else {
      // add card to field and set defendPlayer as current
      turnData = gameTable.addCardToField(turnData, input.toInt, turnData.defendPlayer)
    }
    roundBaseImpl.GameData(roundDataFactory.getInstance(10, None), Some(turnData))
  }

  def defendTurn(gameData: GameDataInterface, input: String): GameDataInterface = {
    println("defendTurn")
    var turnData = gameData.turnData.get
    if(input == "s") {
      //todo handle skips
    } else {
      // add card to field and set defendPlayer as current
      if(turnData.outDeck.deck.isEmpty && turnData.field.deck.length == 9 || turnData.field.deck.length == 11) {
        // maximal Karten für die erste Runde erreicht mit 9 oder allgemein mit 11 im field
        //todo field zu outDeck, neue Rollen verteilen
      } else {
        turnData = gameTable.addCardToField(turnData, input.toInt, gameTable.getLeftPlayer(turnData.currentPlayer, turnData.playerDecks.length))
      }
    }
    roundBaseImpl.GameData(roundDataFactory.getInstance(10, None), Some(turnData))
  }

  def parseAttackTurn(gameData: GameDataInterface, input: String): GameDataInterface = {
    roundBaseImpl.GameData(roundDataFactory.getInstance(13, None), None)
  }
}