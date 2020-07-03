package de.htwg.se.durak.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.durak.controller.controllerComponent.ControllerInterface
import de.htwg.se.durak.controller.gameLogicComponent.GameLogicInterface
import de.htwg.se.durak.model.gameElementsComponent.GameElementsInterface
import de.htwg.se.durak.controller.GameDataChanged
import de.htwg.se.durak.utilities.UndoManager
import com.google.inject.{Guice, Inject}
import scala.swing.Publisher

class Controller @Inject() (val gameElements: GameElementsInterface, val gameLogic: GameLogicInterface) extends ControllerInterface with Publisher  {
  gameLogic.setElements(gameElements)
  // lade hier hin alle componenten und verlinke sie untereinander

  val undoManager = new UndoManager
  var screenSize: Int = 10

  def solve(param: String): Unit = {
    undoManager.doStep(SolveCommand(param, this))
    publish(new GameDataChanged)
  }

  def undo: Unit = {
    undoManager.undoStep
    publish(new GameDataChanged)
  }

  def redo: Unit = {
    //todo: implement
  }

  def inputError(): Unit = {
    //todo add error msg to roundData
    println("INPUT ERROR")
    publish(new GameDataChanged)
  }
}
