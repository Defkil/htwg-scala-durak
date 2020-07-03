package de.htwg.se.durak.controller.gameLogicComponent

trait GameLogicInterface {
  var get: GameStrategyInterface
  val menu: GameStrategyMenuInterface
  def setLocalhost(): Unit
  //def setMultiplayer(): Unit
}
