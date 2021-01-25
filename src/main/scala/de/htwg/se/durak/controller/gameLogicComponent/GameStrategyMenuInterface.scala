package de.htwg.se.durak.controller.gameLogicComponent

import de.htwg.se.durak.model.roundComponent.GameDataInterface

trait GameStrategyMenuInterface {
  /**
   * Handle menu input
   *
   * @param gameData GameData Element
   * @param input User input
   * @return New GameData
   */
  def handleMenu(gameData: GameDataInterface, input: String): GameDataInterface

  /**
   * Handle calibration info
   *
   * @param gameData GameData Element
   * @param input User input
   * @return New GameData
   */
  def handleCalibrationInfo(gameData: GameDataInterface, input: String): GameDataInterface

  /**
   * Handle calibration list
   *
   * @param gameData GameData Element
   * @param input User input
   * @return New GameData
   */
  def handleCalibrationList(gameData: GameDataInterface, input: String): GameDataInterface

  /**
   * Handle game save
   *
   * @param gameData GameData Element
   * @param input User input
   * @return New GameData
   */
  def handleSave(gameData: GameDataInterface, input: String): GameDataInterface
}
