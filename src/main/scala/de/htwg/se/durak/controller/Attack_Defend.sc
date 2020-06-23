import de.htwg.se.durak.controller.GameLogic
import de.htwg.se.durak.model.{Card, CardDeck, Field, Player, TurnData}


def moveCardToField(oldTurnData: TurnData, c: Int):TurnData = {
  val activePlayer = oldTurnData.playerId
  val playerStacks = oldTurnData.playerStacks.toArray
  val card = playerStacks(activePlayer).removeCard(c)
  val newField = oldTurnData.field.copy()
  val mainStack = oldTurnData.mainStack.copy()
  newField.addAttack(card)

  println(activePlayer == oldTurnData.players.size - 1)
  def nextPlayer:Int = if (activePlayer == oldTurnData.players.size - 1)  0 else activePlayer + 1
  TurnData(oldTurnData.players, playerStacks.toList, nextPlayer, newField, oldTurnData.msg, mainStack)
}

val logic = new GameLogic
val a = logic.initiateGame(Array("Hallo","Hi"), "")

for(p <- a.playerStacks) println(p.getSize + " : " + p.cards)

println(a.field.stack.cards)

val b = moveCardToField(a, 0)

for(p <- b.playerStacks) println(p.getSize + " : " + p.deck)

println(b.field.stack.deck)

val c = moveCardToField(b, 0)

for(p <- c.playerStacks) println(p.getSize + " : " + p.deck)

println(c.field.stack.deck)

