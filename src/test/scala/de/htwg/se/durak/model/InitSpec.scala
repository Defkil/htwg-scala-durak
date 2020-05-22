package de.htwg.se.durak.model

import de.htwg.se.durak.controller.{GameLogic, GameTableSpc}
import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class InitSpec extends WordSpec with Matchers {
  /*val table = new GameTable()
  val players = List(new Player("Toni"), new Player("Tim"))
  val logic = new GameLogic(table, players)
  val cardStack = new CardStack()
  val cards = cardStack.generateStack()
  cards.size should be(48)
  val playerCardStack = table.createPlayerCardStack(players)
  table.handOutCards(players, playerCardStack, cardStack)

  var numberOfCards = cardStack.getSize
  for(i <- 0 until players.size) {
    numberOfCards += playerCardStack(i).getSize
  }

  numberOfCards should be(48)

  val table2 = new GameTable()
  val players2 = List(new Player("Toni"), new Player("Tim"))
  val logic2 = new GameLogic(table2, players2)
  val cardStack2 = new CardStack(36)
  val cards2 = cardStack2.generateStack()
  cards2.size should be(36)

  val playerCardStack2 = table2.createPlayerCardStack(players2)
  table2.handOutCards(players2, playerCardStack2, cardStack2)

  var numberOfCards2 = cardStack2.getSize
  for(i <- 0 until players2.size) {
    numberOfCards2 += playerCardStack2(i).getSize
  }

  numberOfCards2 should be(36)*/
}
