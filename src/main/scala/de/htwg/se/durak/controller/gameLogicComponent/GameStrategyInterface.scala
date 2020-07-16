package de.htwg.se.durak.controller.gameLogicComponent

import de.htwg.se.durak.model.roundComponent.GameDataInterface

trait GameStrategyInterface {
  def playerSelect(gameData: GameDataInterface, input: String): GameDataInterface
  def nextTurn(gameData: GameDataInterface, input: String): GameDataInterface
  def attackTurn(gameData: GameDataInterface, input: String): GameDataInterface
  def defendTurn(gameData: GameDataInterface, input: String): GameDataInterface
}
