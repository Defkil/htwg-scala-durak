package de.htwg.se.durak.controller.gameLogicComponent

import de.htwg.se.durak.model.GameData

trait GameStrategyInterface {
  def playerSelect(gameData: GameData, input: String): GameData
  def nextTurn(gameData: GameData, input: String): GameData
  def attackTurn(gameData: GameData, input: String): GameData
}
