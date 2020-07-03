package de.htwg.se.durak.controller.gameLogicComponent

import de.htwg.se.durak.model.GameData
import de.htwg.se.durak.model.gameElementsComponent.GameElementsInterface

trait GameLogicInterface {
  var get: GameStrategyInterface
  val menu: GameStrategyMenuInterface
  def setLocalhost(): Unit
  def setElements(elms: GameElementsInterface): Unit
  //def setMultiplayer(): Unit
}
