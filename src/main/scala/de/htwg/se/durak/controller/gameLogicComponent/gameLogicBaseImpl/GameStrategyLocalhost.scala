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
    val defendPlayer: Int = gameTable.getRightPlayer(currentPlayer, players.length)

    round.createGameData(
      roundDataFactory.getInstance(10),
      Some(round.createTurnData(players, playerDecks, currentPlayer, defendPlayer, field, mainDeck, outDeck, trump, 0))
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

    turnData.turnType match {
      case 0 =>
        if(turnData.currentPlayer == turnData.defendPlayer) { // Verteidiger Runde setzen
          res = round.createGameData(
            round.createRoundData(12,  gameTable.getPossibleDefendTurns(turnData.field.cardDeck.deck.last, playerDecks, turnData.trump))
            , gameData.turnData
          )
        } else if(turnData.field.size == 0) { // falls das Spielfeld leer ist, ist auch kein skip möglich
          res = round.createGameData(
            round.createRoundData(11, gameTable.countTo(playerDecks.deck.length))
            , gameData.turnData
          )
        } else {
          res = round.createGameData(
            round.createRoundData(11, gameTable.getPossibleAttackTurns(turnData.field, playerDecks))
            , gameData.turnData
          )
          // Alle möglichkeiten für weitere Angriffe berechnen
        }
      case 1 => { // nur bei mehr als 2 Spielern, der erste Spieler hat geskipped
        res = round.createGameData(
          round.createRoundData(11, gameTable.getPossibleAttackTurns(turnData.field, playerDecks))
          , gameData.turnData
        )
      }
      case 2 => {// Wenn Verteidiger Karten aufnimmt, in den nächsten Angriff. attackTurn wird den Angriff beenden
        res = round.createGameData(
          round.createRoundData(11, gameTable.getPossibleAttackTurns(turnData.field, playerDecks))
          , gameData.turnData
        )
      }
    }
    res
  }

  def attackTurn(gameData: GameDataInterface, input: String): GameDataInterface = {
    println("attackTurn")
    var turnData = gameData.turnData.get
    var roundData = roundDataFactory.getInstance(10, None)

    turnData.turnType match {
      case 0 =>
        if(input == "s") {
          if(turnData.players.length == 2) {
            //todo finish round
          } else {
            turnData = round.createTurnData (
              turnData.players, turnData.playerDecks, turnData.defendPlayer, gameTable.getRightPlayer(turnData.defendPlayer, turnData.players.length)
              , turnData.field, turnData.mainDeck, turnData.outDeck
              , turnData.trump, 1
            )
          }
        } else {
          turnData = gameTable.addCardToField(turnData, input.toInt, turnData.defendPlayer)
        }
      case 1 => {
        if(input == "s") {
          //todo finish round
        } else {
          //todo add card to field
          //todo set currentPlayer to left Player
        }
      }
      case 2 => { // der fall, wenn der Verteidiger aufnimmt
        if (input == "s") {
          if (turnData.players.length == 2) {
            turnData = gameTable.defenderTakeCards(turnData, 2)
            //todo neue Karten verteilen
          } else {
            if (turnData.currentPlayer == gameTable.getRightPlayer(turnData.defendPlayer, turnData.players.length)) {
              turnData = gameTable.defenderTakeCards(turnData, 2) // third and last attacker
              //todo neue Karten verteilen
            } else {
              //todo set third player as attacker
            }
          }
        } else { // legt Karte zu den aufnehmenden Karten
          turnData = gameTable.addCardToField(gameTable.addSpacer(turnData), input.toInt, turnData.currentPlayer)
        }
      }
    }

    roundBaseImpl.GameData(roundData, Some(turnData))
  }

  def defendTurn(gameData: GameDataInterface, input: String): GameDataInterface = {
    println("defendTurn")
    var turnData = gameData.turnData.get
    if(input == "s") { // Karten werden aufgenommen
      println("1")
      turnData = round.createTurnData(
        turnData.players, turnData.playerDecks, gameTable.getLeftPlayer(turnData.currentPlayer, turnData.players.length), turnData.defendPlayer
        , turnData.field, turnData.mainDeck, turnData.outDeck
        , turnData.trump, 2
      )
    } else {
      // add card to field and set defendPlayer as current
      if(turnData.outDeck.deck.isEmpty && turnData.field.deck.length == 9 || turnData.field.deck.length == 11) {
        println("2")
        turnData = round.createTurnData (
          turnData.players, turnData.playerDecks, turnData.defendPlayer, gameTable.getRightPlayer(turnData.defendPlayer, turnData.players.length)
          , elm.createField(), turnData.mainDeck, turnData.outDeck.addCards(turnData.field.deck)
          , turnData.trump, 0
        )
        //todo neue Karten verteilen
      } else {
        println("3")
        turnData = gameTable.addCardToField(turnData, input.toInt, gameTable.getLeftPlayer(turnData.currentPlayer, turnData.playerDecks.length))
      }
    }
    roundBaseImpl.GameData(roundDataFactory.getInstance(10, None), Some(turnData))
  }

  def parseAttackTurn(gameData: GameDataInterface, input: String): GameDataInterface = {
    roundBaseImpl.GameData(roundDataFactory.getInstance(13, None), None)
  }
}