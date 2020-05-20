import de.htwg.se.durak.controller.{GameLogic, GameTable}
import de.htwg.se.durak.model.{CardStack, Player}

val table = new GameTable()
val players = List(new Player("Toni"), new Player("Tim"))
val logic = new GameLogic(table, players)
val cardStack = new CardStack()
cardStack.generateStack()

println(cardStack.getSize)

val playerCardStack = table.createPlayerCardStack(players)
table.handOutCards(players, playerCardStack, cardStack)

var numberOfCards = cardStack.getSize
for(i <- 0 until players.size) {
  numberOfCards += playerCardStack(i).getSize
}

println(numberOfCards)