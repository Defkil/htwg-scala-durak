package de.htwg.se.durak.controller

import de.htwg.se.durak.model.{GameData, TurnData}
import de.htwg.se.durak.utilities.{Command, GameStrategy}

case class RoundCommand(input: String, runtime: GameRuntime) extends Command {
  val gameStrategyMenu = new GameStrategyMenu
  override def doStep: Unit = {
    runtime.roundStack = runtime.roundStack :+ (runtime.roundData.siteID match {
      case 0 => GameStrategy.get.playerSelect(runtime.gameData, input)
      case 1 => gameStrategyMenu.openCalibrationInfo(runtime.gameData, input)
      case 2 => {
        runtime.screenSize = input.toInt
        gameStrategyMenu.openCalibrationList(runtime.gameData, input)
      }
      case 3 => GameStrategy.get.startGame(runtime.gameData, input)
    })
  }

  override def undoStep: Unit = {
    runtime.roundStack = runtime.roundStack.init
  }
}//todo hier das undo realisieren
