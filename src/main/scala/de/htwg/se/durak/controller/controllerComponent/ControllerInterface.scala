package de.htwg.se.durak.controller.controllerComponent

import de.htwg.se.durak.controller.gameLogicComponent.GameLogicInterface
import de.htwg.se.durak.model.roundComponent.{GameDataInterface, RoundDataInterface, TurnDataInterface}
import de.htwg.se.durak.model.gameElementsComponent.GameElementsInterface
import de.htwg.se.durak.utilities.UndoManager

import scala.swing.Publisher

trait ControllerInterface extends Publisher {
  def undoManager: UndoManager
  var screenSize: Int

  val gameElements: GameElementsInterface
  val gameLogic: GameLogicInterface

  var roundStack: List[GameDataInterface]
  def gameData: GameDataInterface = roundStack.last
  def roundData: RoundDataInterface = gameData.roundData
  def turnData: Option[TurnDataInterface] = gameData.turnData

  def undo: Unit
  def redo: Unit

  def save: Unit
  def load: Unit

  def solve(param: String): Unit
  def inputError: Unit
}
