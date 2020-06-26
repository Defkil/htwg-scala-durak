package de.htwg.se.durak.controller

import de.htwg.se.durak.model.{Card, CardDeck, Player}

import scala.collection.mutable.ListBuffer

case class GameTable() {
  val CARD_PER_HAND = 6
  def generateDeck(size: Int): List[Card] = {
    val cards = new ListBuffer[Card]
    val y = 0
    val x = 0
    val fromCounter = if (size == 36) 5 else 2
    for (x <- fromCounter until 14)
      for (y <- 1 to 4)
        cards += Card(x, y)
    util.Random.shuffle(cards).toList
  }

  def createPlayers(strings: Array[String]): List[Player] = {
    val l = new ListBuffer[Player]
    for(s <- strings) l += new Player(s)
    l.toList
  }

  def createPlayerDecks(size:Int): List[CardDeck] = {
    val stack = new ListBuffer[CardDeck]()
    for(player <- 0 until size) stack += new CardDeck()
    stack.toList
  }

  def handOutCardsStart(mainDeck: CardDeck, playerDecks: List[CardDeck]): (CardDeck, List[CardDeck]) = {
    var newPlayerDecks = new ListBuffer[CardDeck]
    var newMainDeck = mainDeck
    for (i <- Range(0, playerDecks.length, 1)) {
      var newPlayerDeck = new CardDeck
      for (j <- Range(0, CARD_PER_HAND, 1)) {
        var (tempMainDeck, cardToAdd) = newMainDeck.pop()
        newPlayerDeck = newPlayerDeck.addCard(cardToAdd)
        newMainDeck = tempMainDeck
      }
      newPlayerDecks += newPlayerDeck
    }
    (newMainDeck, newPlayerDecks.toList)
  }

  def getFirstPlayer(playerDecks: List[CardDeck], trump: Int): Int = {
    var lowTrumpCard: Option[Card] = None
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
/*val CARDS_PER_PLAYER = 6
def createPlayerCardStack(players: List[Player]): List[CardStack] = {
  val stack = new ListBuffer[CardStack]()
  for(player <- players)
    stack += new CardStack(0)
  stack.toList
}
def handOutCards(cardStackList: List[CardStack], cardStack: CardStack): Unit = {
  for(i <- cardStackList) {
    for(j <- 0 until CARDS_PER_PLAYER) {
      i.addCard(cardStack.popCard())
    }
  }
}*/
}