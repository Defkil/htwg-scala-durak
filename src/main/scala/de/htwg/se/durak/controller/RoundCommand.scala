package de.htwg.se.durak.controller

import de.htwg.se.durak.model.GameData
import de.htwg.se.durak.utilities.Command

case class RoundCommand(input: String, runtime: GameRuntime) extends Command {
  var exampleOnly = true
  override def doStep: Unit = {
    val roundData = runtime.roundData.siteID match {
      case 0 => input match {
        case "0" => runtime.roundFactory.getInstance(3, None)
        case "1" => runtime.roundFactory.getInstance(1, None)
        case "3" => runtime.roundFactory.getInstance(-1, None)
      }
      case 1 => runtime.roundFactory.getInstance(2, None)
      case 2 =>
        runtime.screenSize = input.toInt
        runtime.roundFactory.getInstance(0, None)
      case 3 => // player name select
        //todo add player
        //todo start game
        println(input)
        //turnData = logic.initiateGame(regex.findAllIn(roundData.param).toList)
        runtime.roundFactory.getInstance(10, Some(List("Static Name")))
      case 10 => // next turn
        //todo implementation
        if(exampleOnly) {
          exampleOnly = false
          runtime.roundFactory.getInstance(11, None)
        } else {
          exampleOnly = true
          runtime.roundFactory.getInstance(12, None)
        }
      case 11 => // attacker
        //todo implementation
        runtime.roundFactory.getInstance(10, Some(List("Static Name")))
      case 12 => // defender
        //todo implementation
        runtime.roundFactory.getInstance(13, None)
      case 13 => // finished
        //todo implementation
        runtime.roundFactory.getInstance(0, None)
    }
    runtime.roundStack = runtime.roundStack :+ new GameData(roundData, None)
    runtime.notifyObservers
  }

  override def undoStep: Unit = {
    runtime.roundStack = runtime.roundStack.init
    runtime.notifyObservers
  }
}//todo hier das undo realisieren
