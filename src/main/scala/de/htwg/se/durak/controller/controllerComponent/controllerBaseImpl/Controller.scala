package de.htwg.se.durak.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.durak.controller.controllerComponent.ControllerInterface
import de.htwg.se.durak.controller.gameLogicComponent.GameLogicInterface
import de.htwg.se.durak.model.gameElementsComponent.GameElementsInterface
import de.htwg.se.durak.controller.GameDataChanged
import de.htwg.se.durak.utilities.UndoManager
import com.google.inject.Inject
import de.htwg.se.durak.model.roundComponent.{GameDataInterface, RoundInterface}

import scala.swing.Publisher

class Controller @Inject() (
                             val gameElements: GameElementsInterface,
                             val gameLogic: GameLogicInterface,
                             val round: RoundInterface
                            ) extends ControllerInterface with Publisher  {
  val undoManager = new UndoManager
  var screenSize: Int = 10
  override var roundStack: List[GameDataInterface] = List(round.createGameData(round.createRoundData(0, List("0", "1", "3")), None))

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
