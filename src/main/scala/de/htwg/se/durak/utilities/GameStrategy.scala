package de.htwg.se.durak.utilities

import de.htwg.se.durak.controller.{GameRuntime, GameStrategyLocalhost}
import de.htwg.se.durak.model.GameData

trait TraitGameStrategy {
  def playerSelect(gameData: GameData, input: String): GameData
  def nextTurn(gameData: GameData, input: String): GameData
  def attackTurn(gameData: GameData, input: String): GameData
  def parseAttackTurn(gameData: GameData, input: String): GameData
}

object GameStrategy {
  val localhost = new GameStrategyLocalhost
  //val multiplayer = new GameStrategyMultiplayer
  var get: TraitGameStrategy = new GameStrategyLocalhost
  def setLocalhost(): Unit = get = localhost
  //def setMultiplayer(): Unit = get = multiplayer
}
