package de.htwg.se.durak.controller.gameLogicComponent.gameLogicBaseImpl

import de.htwg.se.durak.model.gameElementsComponent.{CardDeckInterface, CardInterface, FieldInterface, GameElementsInterface}
import de.htwg.se.durak.model.playerComponent.Player
import de.htwg.se.durak.model.roundComponent.{RoundInterface, TurnDataInterface}

import scala.collection.mutable.ListBuffer

case class GameTable(elms: GameElementsInterface, round: RoundInterface) {
  val CARD_PER_HAND = 6
  def generateDeck(size: Int): List[CardInterface] = {
    val cards = new ListBuffer[CardInterface]
    val y = 0
    val x = 0
    val fromCounter = if (size == 36) 5 else 2
    for (x <- fromCounter until 14)
      for (y <- 1 to 4)
        cards += elms.createCard(x, y)
    util.Random.shuffle(cards).toList
  }

  def createPlayers(strings: Array[String]): List[Player] = {
    val l = new ListBuffer[Player]
    for(s <- strings) l += new Player(s)
    l.toList
  }

  def createPlayerDecks(size:Int): List[CardDeckInterface] = {
    val stack = new ListBuffer[CardDeckInterface]()
    for(player <- 0 until size) stack += elms.createCardDeck()
    stack.toList
  }

  def handOutCardsStart(mainDeck: CardDeckInterface, playerDecks: List[CardDeckInterface]): (CardDeckInterface, List[CardDeckInterface]) = {
    var newPlayerDecks = new ListBuffer[CardDeckInterface]
    var newMainDeck = mainDeck
    for (i <- Range(0, playerDecks.length, 1)) {
      var newPlayerDeck: CardDeckInterface = elms.createCardDeck()
      for (j <- Range(0, CARD_PER_HAND, 1)) {
        var (tempMainDeck, cardToAdd) = newMainDeck.pop()
        newPlayerDeck = newPlayerDeck.addCard(cardToAdd)
        newMainDeck = tempMainDeck
      }
      newPlayerDecks += newPlayerDeck
    }
    (newMainDeck, newPlayerDecks.toList)
  }

  def getFirstPlayer(playerDecks: List[CardDeckInterface], trump: Int): Int = {
    var lowTrumpCard: Option[CardInterface] = None
    var playerID: Option[Int] = None
    var actualID: Int = 0
    playerDecks.foreach(deck => {
      deck.deck.foreach(card => {
        if(card.symbol == trump && (lowTrumpCard.isEmpty || card.rank < lowTrumpCard.get.rank)) {
          lowTrumpCard = Option(card)
          playerID = Option(actualID)
        }
      })
      actualID += 1
    })
    playerID.getOrElse(0)
  }

  def countTo(size: Int): List[String] = {
    val res: ListBuffer[String] = ListBuffer()
    for(i <- Range(0, size, 1)) res += i.toString
    res.toList
  }

  def addSpacer(turnData: TurnDataInterface): TurnDataInterface = {
    round.createTurnData(
      turnData.players, turnData.playerDecks, turnData.currentPlayer, turnData.defendPlayer
      , turnData.field.addCard(elms.createCard(-1, -1)), turnData.mainDeck, turnData.outDeck, turnData.trump, turnData.turnType)
  }

  def addCardToField(turnData: TurnDataInterface, input: Int, nextPlayer: Int, turnType: Int): TurnDataInterface = {
    val card = turnData.playerDecks(turnData.currentPlayer).deck(input)
    val field = turnData.field.addCard(card)
    val playerDecks = turnData.playerDecks.updated(turnData.currentPlayer, turnData.playerDecks(turnData.currentPlayer).removeCard(card))
    round.createTurnData(turnData.players, playerDecks, nextPlayer, turnData.defendPlayer, field, turnData.mainDeck, turnData.outDeck, turnData.trump, turnType)
  }
  def addCardToField(turnData: TurnDataInterface, input: Int, nextPlayer: Int): TurnDataInterface = addCardToField(turnData, input, nextPlayer, 0)

  def getPossibleAttackTurns(field: FieldInterface, playerDeck: CardDeckInterface): List[String] = {
    val res: ListBuffer[String] = ListBuffer("s") // Erster Angriff (den man lediglich skippen kann) hat freie Auswahl, daher kann man hier skippen
    var possibleRank: ListBuffer[Int] = ListBuffer()
    field.deck.foreach(card => if(!possibleRank.contains(card.rank)) possibleRank += card.rank)
    for(i <- Range(0,  playerDeck.deck.length, 1)) if(possibleRank.contains(playerDeck.deck(i).rank)) res += i.toString
    res.toList
  }

  def getPossibleDefendTurns(card: CardInterface, playerDeck: CardDeckInterface, trump: Int): List[String] = {
    val res: ListBuffer[String] = ListBuffer("s") // Verteidiger kann immer Karten aufnehmen
    for(i <- Range(0,  playerDeck.deck.length, 1)) if(canCardDefend(card, playerDeck.deck(i), trump)) res += i.toString
    res.toList
  }

  def canCardDefend(attackCard: CardInterface, defendCard: CardInterface, trump: Int): Boolean = {
    if(isTrump(attackCard, trump) && !isTrump(defendCard, trump)) { false } // attack is trump, defend not
    else if(isTrump(attackCard, trump) && isTrump(defendCard, trump)) { attackCard.rank <= defendCard.rank } // both is trump
    else if(isTrump(defendCard, trump) && !isTrump(attackCard, trump)) { true } // defend card is trump, attack not
    else attackCard.rank < defendCard.rank
    // else if(attackCard.symbol == defendCard.symbol) { attackCard.rank < defendCard.rank } // symbol are both same
    // else { false }
  }

  def isTrump(card: CardInterface, trump: Int): Boolean = card.symbol == trump

  def getLeftPlayer(currentPlayer: Int, maxPlayer: Int): Int = { // max player - 1 da Player von 0 hochgezählt wird
    if(currentPlayer == 0) { maxPlayer - 1 }
    else { currentPlayer - 1 }
  }

  def getRightPlayer(currentPlayer: Int, maxPlayer: Int): Int = {
    if(currentPlayer == maxPlayer - 1) 0 else currentPlayer + 1
  }

  def defenderTakeCards(turnData: TurnDataInterface, turnType: Int) : TurnDataInterface = {
    val playerDecks: List[CardDeckInterface] = turnData.playerDecks.updated(
      turnData.defendPlayer
      , turnData.playerDecks(turnData.defendPlayer).addCards(turnData.field.deck)
    )
    val (resPlayerDecks, resMainDeck) = refuelPlayerDecks(playerDecks, turnData.mainDeck, turnData.defendPlayer)
    val nextAttacker = getRightPlayer(turnData.defendPlayer, turnData.players.length)
    round.createTurnData(
      turnData.players, resPlayerDecks, nextAttacker, getRightPlayer(nextAttacker, turnData.players.length)
      , elms.createField(), resMainDeck, turnData.outDeck
      , turnData.trump, turnType
    )
  }

  def defenderTakeCards(turnData: TurnDataInterface) : TurnDataInterface = defenderTakeCards(turnData, 0)

  def attackFinish(turnData: TurnDataInterface) : TurnDataInterface = {
    val (resPlayerDecks, resMainDeck) = refuelPlayerDecks(turnData.playerDecks, turnData.mainDeck, turnData.defendPlayer)
    round.createTurnData (
      turnData.players, resPlayerDecks, turnData.defendPlayer, getRightPlayer(turnData.defendPlayer, turnData.players.length)
      , elms.createField(), resMainDeck, turnData.outDeck.addCards(turnData.field.deck)
      , turnData.trump, 0
    )
  }

  def refuelPlayerDeck(missing: Int, playerDeck: CardDeckInterface, mainDeck: CardDeckInterface): (CardDeckInterface, CardDeckInterface) = {
    var resPlayerDeck = playerDeck
    var resMainDeck = mainDeck
    for(i <- Range(0, missing, 1)) { // first fill from first attack
      val (newMainDeck, card) = resMainDeck.pop()
      resMainDeck = newMainDeck
      resPlayerDeck = resPlayerDeck.addCard(card)
    }
    (resPlayerDeck, resMainDeck)
  }

  def refuelPlayerDecks(
     playerDecks: List[CardDeckInterface], mainDeck: CardDeckInterface, defendPlayer: Int
   ): (List[CardDeckInterface], CardDeckInterface) = {
    if (mainDeck.deck.isEmpty) { // if empty return same elements back
      (playerDecks, mainDeck)
    } else {
      var resMainDeck = mainDeck
      var resPlayerDecks = playerDecks
      var defendPlayerDeck = playerDecks(defendPlayer)
      val maxPlayer = playerDecks.length
      val firstPlayer = getLeftPlayer(defendPlayer, maxPlayer)
      var firstPlayerDeck = playerDecks(firstPlayer)
      var (resFirstPlayerDeck, firstMainDeck) = refuelPlayerDeck (
        getMissingCardsCount(firstPlayerDeck, resMainDeck.deck.length), firstPlayerDeck, resMainDeck
      )
      resMainDeck = firstMainDeck

      if(maxPlayer > 2) {
        val secondPlayer = getRightPlayer(defendPlayer, maxPlayer)
        var secondPlayerDeck = playerDecks(secondPlayer)
        var (resSecondPlayerDeck, secondMainDeck) = refuelPlayerDeck (
          getMissingCardsCount(secondPlayerDeck, resMainDeck.deck.length), secondPlayerDeck , resMainDeck
        )
        resMainDeck = secondMainDeck
        resPlayerDecks = resPlayerDecks.updated(secondPlayer, resSecondPlayerDeck)
      }

      var (resDefendPlayerDeck, defendMainDeck) = refuelPlayerDeck (
        getMissingCardsCount(defendPlayerDeck, resMainDeck.deck.length), defendPlayerDeck, resMainDeck
      )
      resPlayerDecks = resPlayerDecks.updated(firstPlayer, resFirstPlayerDeck)
      resPlayerDecks = resPlayerDecks.updated(defendPlayer, resDefendPlayerDeck)
      (resPlayerDecks, defendMainDeck)
    }
  }

  def getMissingCardsCount(cardDeck: CardDeckInterface, availableCards: Int): Int = {
    if(cardDeck.deck.length >= 6) 0 else {
      var missingCards = (6 - cardDeck.deck.length)
      if(missingCards > availableCards) missingCards = availableCards
      missingCards
    }
  }

  def setRightAttacker(turnData: TurnDataInterface, turnType: Int): TurnDataInterface = {
    round.createTurnData (
      turnData.players, turnData.playerDecks, getRightPlayer(turnData.defendPlayer, turnData.players.length), turnData.defendPlayer
      , turnData.field, turnData.mainDeck, turnData.outDeck
      , turnData.trump, turnType
    )
  }

  def setRightAttacker(turnData: TurnDataInterface): TurnDataInterface = {
    setRightAttacker(turnData, 1)
  }

  def removeEmptyPlayer(turnData: TurnDataInterface): TurnDataInterface = {
    var resPlayers: ListBuffer[Player] = new ListBuffer[Player]
    var resPlayerDecks: ListBuffer[CardDeckInterface] = new ListBuffer[CardDeckInterface]

    for(i <- turnData.players.indices) {
      val playerDeck = turnData.playerDecks(i)
      if(playerDeck.deck.nonEmpty) {
        resPlayerDecks += playerDeck
        resPlayers += turnData.players(i)
      }
    }

    round.createTurnData (
      resPlayers.toList, resPlayerDecks.toList, turnData.currentPlayer, turnData.defendPlayer
      , turnData.field, turnData.mainDeck, turnData.outDeck
      , turnData.trump, turnData.turnType
    )
  }
}

/*
  def setLeftAttacker(turnData: TurnDataInterface): TurnDataInterface = {
    round.createTurnData (
      turnData.players, turnData.playerDecks, getLeftPlayer(turnData.defendPlayer, turnData.players.length), turnData.defendPlayer
      , turnData.field, turnData.mainDeck, turnData.outDeck
      , turnData.trump, 1
    )
  }
 */