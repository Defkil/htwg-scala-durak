package de.htwg.se.durak.controller.gameLogicComponent

import de.htwg.se.durak.model.roundComponent.GameDataInterface

trait GameStrategyInterface {
  /**
   * Handle player selection
   *
   * @param gameData GameData Element
   * @param input User input
   * @return New GameData
   */
  def playerSelect(gameData: GameDataInterface, input: String): GameDataInterface

  /**
   * Handle next turn
   *
   * @param gameData GameData Element
   * @param input User input
   * @return New GameData
   */
  def nextTurn(gameData: GameDataInterface, input: String): GameDataInterface

  /**
   * Handle attack turn
   *
   * @param gameData GameData Element
   * @param input User input
   * @return New GameData
   */
  def attackTurn(gameData: GameDataInterface, input: String): GameDataInterface

  /**
   * Handle defend turn
   *
   * @param gameData GameData Element
   * @param input User input
   * @return New GameData
   */
  def defendTurn(gameData: GameDataInterface, input: String): GameDataInterface
}
