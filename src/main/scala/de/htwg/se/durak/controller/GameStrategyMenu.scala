package de.htwg.se.durak.controller

import de.htwg.se.durak.model.GameData

case class GameStrategyMenu() {
  val roundFactory = new RoundFactory
  def openMenu(gameData: GameData, input: String): GameData = {
    GameData(input match {
      case "0" => roundFactory.getInstance(3, None)
      case "1" => roundFactory.getInstance(1, None)
      case "3" => roundFactory.getInstance(-1, None)
    }, None)
  }

  def openCalibrationInfo(gameData: GameData, input: String): GameData = {
    GameData(roundFactory.getInstance(2, None), None)
  }
  def openCalibrationList(gameData: GameData, input: String): GameData = {
    GameData(roundFactory.getInstance(0, None), None)
  }
}
