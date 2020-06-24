package de.htwg.se.durak.utilities

import de.htwg.se.durak.controller.{GameStrategyLocalhost, GameStrategyMultiplayer}

trait TraitGameStrategy {

}

object GameStrategy {
  val localhost = new GameStrategyLocalhost
  val multiplayer = new GameStrategyMultiplayer
  var get: TraitGameStrategy = new GameStrategyLocalhost
  def setLocalhost(): Unit = get = localhost
  def setMultiplayer(): Unit = get = multiplayer
}
