package de.htwg.se.durak.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.durak.controller.controllerComponent.ControllerInterface
import de.htwg.se.durak.model.roundComponent.GameDataInterface
import de.htwg.se.durak.utilities.Command

//noinspection ScalaStyle
case class SolveCommand(input: String, controller: ControllerInterface) extends Command {
  var memento: List[GameDataInterface] = controller.roundStack
  override def doStep: Unit = {
    var memento: List[GameDataInterface] = controller.roundStack
    controller.roundStack = controller.roundStack :+ (controller.roundData.siteID match {
      case 0 => controller.gameLogic.menu.handleMenu(controller.gameData, input)
      case 1 => controller.gameLogic.menu.handleCalibrationInfo(controller.gameData, input)
      case 2 => {
        controller.screenSize = input.toInt
        controller.gameLogic.menu.handleCalibrationList(controller.gameData, input)
      }
      case 3 => controller.gameLogic.get.playerSelect(controller.gameData, input)
      case 4 => controller.gameLogic.menu.handleSave(controller.gameData, input) // save step
      case 10 => controller.gameLogic.get.nextTurn(controller.gameData, input)
      case 11 => controller.gameLogic.get.attackTurn(controller.gameData, input)
      case 12 => controller.gameLogic.get.defendTurn(controller.gameData, input)
    })
  }

  override def undoStep: Unit = {
    val new_memento: List[GameDataInterface] = controller.roundStack
    controller.roundStack = memento
    memento = new_memento
  }

  override def redoStep: Unit = {
    val new_memento: List[GameDataInterface] = controller.roundStack
    controller.roundStack = memento
    memento = new_memento
  }
}
