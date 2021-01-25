package de.htwg.se.durak.controller.gameLogicComponent

trait GameLogicInterface {
  /**
   * Actual GameStrategy
   */
  var get: GameStrategyInterface

  /**
   * MenuStrategy
   */
  val menu: GameStrategyMenuInterface
}
