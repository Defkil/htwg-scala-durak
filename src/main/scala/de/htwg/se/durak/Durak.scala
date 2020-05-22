package de.htwg.se.durak

import java.util.Scanner

import de.htwg.se.durak.controller.{GameLogic, GameTable}
import de.htwg.se.durak.model.{Card, CardStack, Player}

object Durak {
  val s = new Scanner(System.in)
  def makePlayers(): List[Player] = {
    val array = Array.ofDim[Player](2)
    println("2 Spielernamen eingeben")
    for(i <- 0 until array.size) array(i) = new Player(s.next())
    array.toList
  }

  def main(args: Array[String]): Unit = {
//    val student = Player("Your Name")
//    println("Hello, " + student.name)

//    val c = new Card(7,3)
//    println(c)

    val stack = new CardStack()
    stack.generateStack()
    val table = new GameTable()
    val players = makePlayers()
    println(players)
    val logic = new GameLogic(table, players)
    val stackList = table.createPlayerCardStack(players)
    table.handOutCards(players, stackList, stack)
    val cards = stack.popCards(2)
    println(cards)
    stack.removeCard(3)
    stack.clear()
    println(stack.getSize)
    println(stack.getAllCards())

    val stack2 = new CardStack(32)
  }
}
