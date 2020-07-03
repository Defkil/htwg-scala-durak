package de.htwg.se.durak.controller.gameLogicComponent

import de.htwg.se.durak.model.GameData

trait GameStrategyMenuInterface {
  def handleMenu(gameData: GameData, input: String): GameData
  def handleCalibrationInfo(gameData: GameData, input: String): GameData
  def handleCalibrationList(gameData: GameData, input: String): GameData
}
