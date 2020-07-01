package de.htwg.se.durak.controller

import de.htwg.se.durak.model.{GameData, TurnData}
import de.htwg.se.durak.utilities.{Command, GameStrategy}

//noinspection ScalaStyle
case class RoundCommand(input: String, runtime: GameRuntime) extends Command {
  val gameStrategyMenu = new GameStrategyMenu
  override def doStep: Unit = {
    runtime.roundStack = runtime.roundStack :+ (runtime.roundData.siteID match {
      case 0 => gameStrategyMenu.handleMenu(runtime.gameData, input)
      case 1 => gameStrategyMenu.handleCalibrationInfo(runtime.gameData, input)
      case 2 => {
        //todo min. size 7
        runtime.screenSize = input.toInt
        gameStrategyMenu.handleCalibrationList(runtime.gameData, input)
      }
      case 3 => GameStrategy.get.playerSelect(runtime.gameData, input)
      case 10 => GameStrategy.get.nextTurn(runtime.gameData, input)
      case 11 => GameStrategy.get.attackTurn(runtime.gameData, input)
      case 12 => GameStrategy.get.parseAttackTurn(runtime.gameData, input)
    })
  }

  override def undoStep: Unit = {
    runtime.roundStack = runtime.roundStack.init
  }
}
