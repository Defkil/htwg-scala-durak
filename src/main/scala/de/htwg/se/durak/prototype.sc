import de.htwg.se.durak.model.{Card, CardStack, Player}

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


case class CardStack() {
  var cards = new ListBuffer[Card]()
  var size: Int = 0
class CardStack {
  private var cards = new ListBuffer[Card]()
  private var size: Int = 48
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
      for( x <- 2 until 14)
        for( y <- 1 to 4)
          cards += (new Card(x, y))
    }else if(size == 36){
      for( x <- 6 to 14)
        for( y <- 1 until 4)
          cards += (new Card(x, y))
    }
    cards = util.Random.shuffle(cards)
    cards
  }

  def removeCard(position: Int): Unit = {
    if(position >= cards.size) throw new IndexOutOfBoundsException
    cards.remove(position)
  }

  def addCard(c:Card): Unit = {
    cards += c
  }

  def changeCardPosition(from: Int, to: Int): Unit = {
    if(from >= cards.size || to >= cards.size || from < 0 || to < 0) throw new IndexOutOfBoundsException
  }

  def getAllCards(): ListBuffer[Card] = {
    cards
  }

  def popCard(): Card = {
    cards.remove(cards.size - 1)
  }

  def popCards(numberOfCards: Int): Unit = {
    val a = Array.ofDim[Card](numberOfCards)
    for(i <- 0 until numberOfCards) a(i) = popCard()
    a.toList
  }

  def debugLog() : Unit = {
    for (card <- cards) {
      println(card.get())
    }
  }

  // delete all cards in cards
  def clear() : Unit = {
    cards.clear()
  }

  def getSize:Int = cards.size
}

val stack = new CardStack(36)
stack.generateStack()
stack.debugLog()






class Player(name:String) {

}

class GameTable() {
  val CARDS_PER_PLAYER = 6
  def createPlayerCardStack(players: List[Player]): List[CardStack] = {
    val stack = new ListBuffer[CardStack]()
    for(player <- players)
      stack += new CardStack(0)
    stack.toList
  }
  def handOutCards(players: List[Player], cardStackList: List[CardStack], cardStack: CardStack): Unit = {
    if(players.size != cardStackList.size) throw new Exception("Amout of players not like cardstacks per player")
    for(i <- 0 until players.size) {
      val stack = cardStackList(i)
      for(j <- 0 until CARDS_PER_PLAYER) {
        stack.addCard(cardStack.popCard())
      }
    }
  }
}
12
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

val player1 = new Player("")
val player2 = new Player("")
val playerList: List[Player] = List(player1, player2)

val gameTable = new GameTable()
val gameLogic = new GameLogic(gameTable, playerList)
gameLogic.setStackSize(36)
gameLogic.start()