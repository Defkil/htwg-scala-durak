package de.htwg.se.durak.controller.gameLogicComponent.gameLogicBaseImpl

import de.htwg.se.durak.controller.gameLogicComponent.{GameLogicInterface, GameStrategyInterface, GameStrategyMenuInterface}
import de.htwg.se.durak.model.gameElementsComponent.GameElementsInterface
import de.htwg.se.durak.model.gameElementsComponent.gameElementsBaseImpl.GameElements

class GameLogic extends GameLogicInterface {
  var elm: GameElementsInterface = _
  var gameTable: GameTable = _
  val menu: GameStrategyMenuInterface = new GameStrategyMenu
  def setElements(elms: GameElementsInterface): Unit = {
    elm = elms
    gameTable = GameTable(elms)
  }
  val localhost = new GameStrategyLocalhost(this)
  //val multiplayer = new GameStrategyMultiplayer
  var get: GameStrategyInterface = localhost
  def setLocalhost(): Unit = get = localhost
  //def setMultiplayer(): Unit = get = multiplayer
}
