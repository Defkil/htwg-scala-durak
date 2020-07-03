package de.htwg.se.durak.controller.controllerComponent

import de.htwg.se.durak.controller.gameLogicComponent.gameLogicBaseImpl.RoundFactory
import de.htwg.se.durak.model.{GameData, RoundData, TurnData}
import de.htwg.se.durak.utilities.UndoManager

import scala.swing.Publisher

trait ControllerInterface extends Publisher {
  def undoManager: UndoManager
  def screenSize: Int

  //todo zu fragen ob die Logic hier "ok" ist
  var roundStack: List[GameData] = List(GameData(RoundFactory.getInstance(0, None), None))
  def gameData: GameData = roundStack.last
  def roundData: RoundData = gameData.roundData
  def turnData: Option[TurnData] = gameData.turnData

  def undo: Unit
  def redo: Unit
  def solve(param: String): Unit
  def inputError: Unit
}
