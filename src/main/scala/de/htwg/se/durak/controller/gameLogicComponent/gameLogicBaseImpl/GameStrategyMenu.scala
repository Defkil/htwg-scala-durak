package de.htwg.se.durak.controller.gameLogicComponent.gameLogicBaseImpl

import de.htwg.se.durak.controller.gameLogicComponent.GameStrategyMenuInterface
import de.htwg.se.durak.model.roundComponent.{GameDataInterface, RoundInterface, roundBaseImpl}

case class GameStrategyMenu(round: RoundInterface) extends  GameStrategyMenuInterface{
  var roundDataFactory: RoundDataFactory = new RoundDataFactory(round)
  def handleMenu(gameDataI: GameDataInterface, input: String): GameDataInterface = {
    roundBaseImpl.GameData(input match {
      case "0" => roundDataFactory.getInstance(3)
      case "1" => roundDataFactory.getInstance(1)
      case "3" => roundDataFactory.getInstance(-1)
    }, None)
  }

  def handleCalibrationInfo(gameData: GameDataInterface, input: String): GameDataInterface = {
    roundBaseImpl.GameData(roundDataFactory.getInstance(2), None)
  }
  def handleCalibrationList(gameData: GameDataInterface, input: String): GameDataInterface = {
    roundBaseImpl.GameData(roundDataFactory.getInstance(0), None)
  }
}
