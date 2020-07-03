package de.htwg.se.durak.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.durak.controller.controllerComponent.ControllerInterface
import de.htwg.se.durak.utilities.Command

//noinspection ScalaStyle
case class SolveCommand(input: String, controller: ControllerInterface) extends Command {
  override def doStep: Unit = {
    controller.roundStack = controller.roundStack :+ (controller.roundData.siteID match {
      case 0 => controller.gameLogic.menu.handleMenu(controller.gameData, input)
      case 1 => controller.gameLogic.menu.handleCalibrationInfo(controller.gameData, input)
      case 2 => {
        //todo min. size 7
        controller.screenSize = input.toInt
        controller.gameLogic.menu.handleCalibrationList(controller.gameData, input)
      }
      case 3 => controller.gameLogic.get.playerSelect(controller.gameData, input)
      case 10 => controller.gameLogic.get.nextTurn(controller.gameData, input)
      case 11 => controller.gameLogic.get.attackTurn(controller.gameData, input)
    })
  }

  override def undoStep: Unit = {
    controller.roundStack = controller.roundStack.init
  }

  //override def redoStep: Unit = {
    //todo: implement
  //}
}
