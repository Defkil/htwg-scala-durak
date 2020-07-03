package de.htwg.se.durak.utilities

import de.htwg.se.durak.controller.gameLogicComponent.GameStrategyInterface
import org.scalatest._

class GameStrategyInterfaceSpec extends WordSpec with Matchers {
  /*"A GameStrategy" should {
    "default should be localhost" in {
      GameStrategy.get should be(GameStrategy.localhost)
    }
    /*"set strategy to multiplayer" in {
      GameStrategy.setMultiplayer()
      GameStrategy.get should be(GameStrategy.multiplayer)
    }*/
    "set strategy to localhost" in {
      GameStrategy.setLocalhost()
      GameStrategy.get should be(GameStrategy.localhost)
    }
  }*/
}
