package de.htwg.se.durak

import java.util.Scanner

import de.htwg.se.durak.controller.{GameLogic, GameObservable, GameTable, GameRuntime, ObserverIn}
import de.htwg.se.durak.model.{Card, CardStack, Player, RoundData}
import de.htwg.se.durak.view.Tui

object Durak {
  def main(args: Array[String]): Unit = {
    val runtime = new GameRuntime
    val roundManager = new ObserverIn
    val tui = new Tui(roundManager)

    // setup round manager with observers
    roundManager.addObservable(tui)
    roundManager.data = runtime.start()
    runtime.setSpacer = (spacerSize: Int) => { tui.screenSize = spacerSize }

    // game loop
    var gameActive = true
    while(gameActive) {
      roundManager.gm.notifyObservers()
      tui.output.foreach(println)

      // todo confirm input
      val s = new Scanner(System.in).useDelimiter("\n")


      var inputValid = false
      var param = ""
      var paramInt = -1
      var paramArray:Array[String] = Array()
      roundManager.data.nextInput match {
        case 0 => {
          paramInt = s.nextInt()
          if(paramInt <= roundManager.data.inputMaxInt) inputValid = true
        }
        case 1 => {
          param = s.next()
          if(param.length <= roundManager.data.inputMaxStringSize) inputValid = true
        }
        case 2 => {
          paramArray = s.next.split(" ")
          if(
            paramArray.length <= roundManager.data.inputMaxLineSpacers &&
            paramArray.length >= roundManager.data.inputMinLineSpacers
          ) inputValid = true
        }
      }

      if(inputValid) {
        roundManager.data = runtime.getNextRound(
          param = param,
          paramInt = paramInt,
          paramArray = paramArray,
          lastRound = roundManager.data
        )
        if(roundManager.data == null) gameActive = false
      }
    }
  }
}

/*
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

    val stack = new CardStack(48)
    stack.generateStack()
    val table = new GameTable()
    val players = makePlayers()
    println(players)
    val logic = new GameLogic(table, players)
    val stackList = table.createPlayerCardStack(players)
    table.handOutCards(players, stackList, stack)
    val cards = stack.popCards(2)
    println(cards)
    val c = cards(0)
    println(c.toString + ", values: " + c.get() + ", weight: " + c.getWeight())
    stack.removeCard(3)
    println(stack)
    stack.clear()
    println(stack.getSize)
    println(stack.getAllCards())

    val stack2 = new CardStack(32)
  }
 */