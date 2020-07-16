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

  def countToWithS(size: Int): List[String] = {
    val res: ListBuffer[String] = ListBuffer("s")
    for(i <- Range(0, size, 1)) res += i.toString
    res.toList
  }
  def countTo(size: Int): List[String] = {
    val res: ListBuffer[String] = ListBuffer()
    for(i <- Range(0, size, 1)) res += i.toString
    res.toList
  }

  def getNextPlayer(currentPlayer: Int, maxPlayer: Int): Int = if(currentPlayer + 1 == maxPlayer) 0 else currentPlayer + 1

  def addCardToField(turnData: TurnDataInterface, input: Int, nextPlayer: Int): TurnDataInterface = {
    val card = turnData.playerDecks(turnData.currentPlayer).deck(input)
    val field = turnData.field.addCard(card)
    val playerDecks = turnData.playerDecks.updated(turnData.currentPlayer, turnData.playerDecks(turnData.currentPlayer).removeCard(card))
    round.createTurnData(turnData.players, playerDecks, nextPlayer, turnData.defendPlayer, field, turnData.mainDeck, turnData.outDeck, turnData.trump)
  }

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
    if(isTrump(attackCard, trump) && !isTrump(defendCard, trump)) { false }
    else if(isTrump(attackCard, trump) && isTrump(defendCard, trump)) { attackCard.rank <= defendCard.rank }
    else if(isTrump(defendCard, trump)) { true }
    else if(attackCard.symbol == defendCard.symbol) { attackCard.rank < defendCard.rank }
    else { false }
  }

  def isTrump(card: CardInterface, trump: Int): Boolean = card.symbol == trump

  def getLeftPlayer(currentPlayer: Int, maxPlayer: Int): Int = {
    if(currentPlayer == 0) { maxPlayer }
    else { currentPlayer - 1 }
  }
}
