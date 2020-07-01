package de.htwg.se.durak.controller

import de.htwg.se.durak.model.{CardDeck, Field, GameData, Player, RoundData, TurnData}
import org.scalatest._

class GameRuntimeSpec extends WordSpec with Matchers {
  "A GameRuntime" when {
    val runtime = new GameRuntime()
    "size" in {
      runtime.roundStack = runtime.roundStack :+ new GameData(
        new RoundData(0, Some((s: String) => true), None, None),
        Some(new TurnData(List(new Player("name")), List(new CardDeck()), 0, 1,
          new Field(), new CardDeck(), trump = -1))
      )
      // runtime.turnData.get should exist()
    }
  }
}
