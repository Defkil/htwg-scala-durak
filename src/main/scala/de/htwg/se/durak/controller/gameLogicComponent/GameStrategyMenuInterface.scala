package de.htwg.se.durak.controller.gameLogicComponent

import de.htwg.se.durak.model.roundComponent.GameDataInterface

trait GameStrategyMenuInterface {
  def handleMenu(gameData: GameDataInterface, input: String): GameDataInterface
  def handleCalibrationInfo(gameData: GameDataInterface, input: String): GameDataInterface
  def handleCalibrationList(gameData: GameDataInterface, input: String): GameDataInterface
  def handleSave(gameData: GameDataInterface, input: String): GameDataInterface
}
