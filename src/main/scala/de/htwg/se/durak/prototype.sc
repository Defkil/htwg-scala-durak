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

  def removeCard(): Unit = {
  }

  def addCard(): Unit = {
  }

  def changeCardPosition(): Unit = {
  }

  def getAllCards(): ListBuffer[Card] = {
    cards
  }

  def popCard(): Unit = {
  }

  def popCards(): Unit = {
  }

  def debugLog() : Unit = {
    for (card <- cards) {
      println(card.get())
    }
  }

  def clear() : Unit = {

  }
}

val stack = new CardStack(36)
stack.generateStack()
stack.debugLog()






class Player() {

}

val player1 = new Player()
val player2 = new Player()
val playerList: List[Player] = List(player1, player2)

class GameTable(players: List[Player], size: Int) {
  // die Klasse sollte hier nicht sein, aber wegen der einen Datei halt so
  class Game(gameTable: GameTable) {
    var trump: Int = -1
    def start(lastWinner: Int): Unit = {
      //todo add lastWinner
      start()
    }
    def start(): Unit = {
      prepareStart()
    }
    private def prepareStart(): Unit = {
      trump = 1 + Random.nextInt(3) // set random trump
    }
  }



  val coveredCards = new CardStack()
  var playerCards: List[CardStack] = List()
  def setup(): Unit= {
    playerCards = createPlayerCardsStack()
    val game = new Game(this)
    game.start()
  }

  private def createPlayerCardsStack(): List[CardStack] = {
    val stack = new ListBuffer[CardStack]()
    for(player <- players)
      stack += new CardStack(0)
    stack.toList
  }
}

val gameTable = new GameTable(playerList, 36)
gameTable.setup()


