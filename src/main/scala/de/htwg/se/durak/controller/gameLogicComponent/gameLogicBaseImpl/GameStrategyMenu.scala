package de.htwg.se.durak.controller.gameLogicComponent.gameLogicBaseImpl

import de.htwg.se.durak.controller.gameLogicComponent.GameStrategyMenuInterface
import de.htwg.se.durak.model.GameData

case class GameStrategyMenu() extends  GameStrategyMenuInterface{
  def handleMenu(gameData: GameData, input: String): GameData = {
    GameData(input match {
      case "0" => RoundFactory.getInstance(3)
      case "1" => RoundFactory.getInstance(1)
      case "3" => RoundFactory.getInstance(-1)
    }, None)
  }

  def handleCalibrationInfo(gameData: GameData, input: String): GameData = {
    GameData(RoundFactory.getInstance(2), None)
  }
  def handleCalibrationList(gameData: GameData, input: String): GameData = {
    GameData(RoundFactory.getInstance(0), None)
  }
}
