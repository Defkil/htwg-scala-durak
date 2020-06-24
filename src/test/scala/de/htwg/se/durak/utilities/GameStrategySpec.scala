package de.htwg.se.durak.utilities

import org.scalatest._

class GameStrategySpec extends WordSpec with Matchers {
  "A GameStrategy" should {
    "default should be localhost" in {
      GameStrategy.get should be(GameStrategy.localhost)
    }
    "set strategy to multiplayer" in {
      GameStrategy.setMultiplayer()
      GameStrategy.get should be(GameStrategy.multiplayer)
    }
    "set strategy to localhost" in {
      GameStrategy.setLocalhost()
      GameStrategy.get should be(GameStrategy.localhost)
    }
  }
}
