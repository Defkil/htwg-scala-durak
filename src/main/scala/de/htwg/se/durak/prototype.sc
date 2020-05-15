import scala.collection.mutable.ListBuffer
import scala.util.Random

class Card(rank: Int, cardType: Int) {
  def get():(Int, Int) = {
    (rank, cardType)
  }

  def getImagePath(): String = {
    "todo"
  }
}
val testCard = new Card(10, 1)
val (testCardRank, testCardType) = testCard.get()


class CardStack {
  var cards = new ListBuffer[Card]()
  var size: Int = 0
  def this(getSize: Int, getCards: ListBuffer[Card]){
    this()
    cards = getCards
    size = getSize
  }
  def this(getSize: Int = 0){
    this()
    size = getSize
  }

  def generateStack(): ListBuffer[Card] = {
    val y = 0
    val x = 0
    if(size == 48){
      for( x <- 2 to 14)
        for( y <- 1 to 4)
          cards += (new Card(x, y))
    }else if(size == 36){
      for( x <- 6 to 14)
        for( y <- 1 to 4)
          cards += (new Card(x, y))
    }
    cards = util.Random.shuffle(cards)
    cards
  }

  def removeCard(position: Int): Unit = {
  }

  def addCard(c : Card): Unit = {
    cards += c
  }

  def changeCardPosition(from: Int, to: Int): Unit = {
  }

  def getAllCards(): ListBuffer[Card] = {
    cards
  }

  def popCard(): Unit = {
  }

  def popCards(numberOfCards: Int): Unit = {
  }

  def debugLog() : Unit = {
    for (card <- cards) {
      println(card.get())
    }
  }

  // delete all cards in cards
  def clear() : Unit = {

  }
}

val stack = new CardStack(36)
stack.generateStack()
stack.debugLog()






class Player() {

}

class GameTable() {
  def createPlayerCardStack(players: List[Player]): List[CardStack] = {
    val stack = new ListBuffer[CardStack]()
    for(player <- players)
      stack += new CardStack(0)
    stack.toList
  }
  def handOutCards(players: List[Player], cardStack:CardStack): Unit = {

  }
}

class GameLogic(gameTable: GameTable, playerList: List[Player]) {
  var trump: Int = -1
  var stackSize: Int = 0
  def start(lastWinner: Int): Unit = {
    //todo lastWinner==-1 need to check cards
    trump = 1 + Random.nextInt(3)
    val playerCards = gameTable.createPlayerCardStack(playerList)
    val hiddenCards = new CardStack(stackSize)
    hiddenCards.generateStack()
    val showedCards = new CardStack()

  }
  def start(): Unit = {
    start(-1)
  }

  def setStackSize(size: Int): Unit = {
    stackSize = size
  }

}

val player1 = new Player()
val player2 = new Player()
val playerList: List[Player] = List(player1, player2)

val gameTable = new GameTable()
val gameLogic = new GameLogic(gameTable, playerList)
gameLogic.setStackSize(36)
gameLogic.start()
