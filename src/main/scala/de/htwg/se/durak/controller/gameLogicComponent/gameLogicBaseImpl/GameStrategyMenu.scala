package de.htwg.se.durak.controller.gameLogicComponent.gameLogicBaseImpl

import de.htwg.se.durak.controller.gameLogicComponent.GameStrategyMenuInterface
import de.htwg.se.durak.model.roundComponent.{GameDataInterface, RoundInterface, roundBaseImpl}

case class GameStrategyMenu(round: RoundInterface) extends  GameStrategyMenuInterface{
  var roundDataFactory: RoundDataFactory = new RoundDataFactory(round)

  /**
   * Handle menu input
   *
   * @param gameData GameData Element
   * @param input User input
   * @return New GameData
   */
  def handleMenu(gameData: GameDataInterface, input: String): GameDataInterface = {
    roundBaseImpl.GameData(input match {
      case "0" => roundDataFactory.getInstance(3)
      case "1" => roundDataFactory.getInstance(1)
      case "3" => roundDataFactory.getInstance(-1)
    }, None)
  }

  /**
   * Handle calibration info
   *
   * @param gameData GameData Element
   * @param input User input
   * @return New GameData
   */
  def handleCalibrationInfo(gameData: GameDataInterface, input: String): GameDataInterface = {
    roundBaseImpl.GameData(roundDataFactory.getInstance(2), None)
  }

  /**
   * Handle calibration list
   *
   * @param gameData GameData Element
   * @param input User input
   * @return New GameData
   */
  def handleCalibrationList(gameData: GameDataInterface, input: String): GameDataInterface = {
    roundBaseImpl.GameData(roundDataFactory.getInstance(0), None)
  }

  /**
   * Handle game save
   *
   * @param gameData GameData Element
   * @param input User input
   * @return New GameData
   */
  def handleSave(gameData: GameDataInterface, input: String): GameDataInterface = {
    roundBaseImpl.GameData(roundDataFactory.getInstance(4), None)
  }
}
