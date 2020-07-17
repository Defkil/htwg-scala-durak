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
    val currentPlayer: Int = gameTable.getFirstPlayer(playerDecks, trump)

    round.createGameData(
      roundDataFactory.getInstance(10),
      Some(round.createTurnData(
        players, playerDecks, currentPlayer, gameTable.getRightPlayer(currentPlayer, players.length)
        , elm.createField() , mainDeck, elm.createCardDeck(), trump, 0)
      )
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
    var turnData = gameData.turnData.get
    val playerDeck = turnData.playerDecks(turnData.currentPlayer)
    var res = gameData

    turnData.turnType match {
      case 0 =>
        if(turnData.currentPlayer == turnData.defendPlayer) { // Verteidiger Runde setzen
          res = round.createGameData(
            round.createRoundData(12,  gameTable.getPossibleDefendTurns(turnData.field.cardDeck.deck.last, playerDeck, turnData.trump))
            , gameData.turnData
          )
        } else if(turnData.field.size == 0) { // falls das Spielfeld leer ist, ist auch kein skip möglich
          res = round.createGameData(
            round.createRoundData(11, gameTable.countTo(playerDeck.deck.length))
            , gameData.turnData
          )
        } else {
          res = round.createGameData(
            round.createRoundData(11, gameTable.getPossibleAttackTurns(turnData.field, playerDeck))
            , gameData.turnData
          )
          // Alle möglichkeiten für weitere Angriffe berechnen
        }
      case 1 => { // nur bei mehr als 2 Spielern, der erste Spieler hat geskipped
        res = round.createGameData(
          round.createRoundData(11, gameTable.getPossibleAttackTurns(turnData.field, playerDeck))
          , gameData.turnData
        )
      }
      case 2 => {// Wenn Verteidiger Karten aufnimmt, in den nächsten Angriff. attackTurn wird den Angriff beenden
        res = round.createGameData(
          round.createRoundData(11, gameTable.getPossibleAttackTurns(turnData.field, playerDeck))
          , gameData.turnData
        )
      }
    }

    if (turnData.mainDeck.deck.isEmpty) {
      turnData = gameTable.removeEmptyPlayer(turnData)
      if (turnData.players.length == 1) {
        res = round.createGameData(
          roundDataFactory.getInstance(-1, Some(List("Der Durak dieser Runde ist:"))),
          None
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
            turnData = gameTable.attackFinish(turnData)
          } else {
            turnData = gameTable.setRightAttacker(turnData)
          }
        } else {
          turnData = gameTable.addCardToField(turnData, input.toInt, turnData.defendPlayer)
        }
      case 1 => {
        if(input == "s") {
          if(turnData.playerDecks.length > 2) {
            turnData = gameTable.setRightAttacker(turnData)
          } else {
            turnData = gameTable.attackFinish(turnData)
          }
        } else {
          turnData = gameTable.addCardToField(turnData, input.toInt, turnData.defendPlayer)
        }
      }
      case 2 => { // der fall, wenn der Verteidiger aufnimmt
        if (input == "s") {
          if (turnData.players.length == 2) {
            turnData = gameTable.defenderTakeCards(turnData)
          } else {
            if (turnData.currentPlayer == gameTable.getRightPlayer(turnData.defendPlayer, turnData.players.length)) {
              turnData = gameTable.defenderTakeCards(turnData) // third and last attacker
            } else {
              turnData = gameTable.setRightAttacker(turnData, 2)
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
      turnData = round.createTurnData(
        turnData.players, turnData.playerDecks, gameTable.getLeftPlayer(turnData.currentPlayer, turnData.players.length), turnData.defendPlayer
        , turnData.field, turnData.mainDeck, turnData.outDeck
        , turnData.trump, 2
      )
    } else {
      // add card to field and set defendPlayer as current
      if(turnData.outDeck.deck.isEmpty && turnData.field.deck.length == 9 || turnData.field.deck.length == 11) {
        turnData = gameTable.attackFinish(turnData)
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