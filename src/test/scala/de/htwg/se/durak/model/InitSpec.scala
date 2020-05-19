package de.htwg.se.durak.model

import de.htwg.se.durak.controller.{GameLogic, GameTable}
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class InitSpec extends WordSpec with Matchers {
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

  numberOfCards should be(48)
}
