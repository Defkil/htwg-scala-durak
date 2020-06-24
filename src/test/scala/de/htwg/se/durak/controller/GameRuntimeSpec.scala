package de.htwg.se.durak.controller

import de.htwg.se.durak.model.{CardDeck, Field, GameData, Player, RoundData, TurnData}
import org.scalatest._

class GameRuntimeSpec extends WordSpec with Matchers {
  "A GameRuntime" when {
    val runtime = new GameRuntime()
    "size" in {
      runtime.roundStack = runtime.roundStack :+ new GameData(
        new RoundData(0, (s: String) => true, None),
        Some(new TurnData(List(new Player("name")), List(new CardDeck()), 0,
          new Field(), "msg", new CardDeck()))
      )
      runtime.turnData.get.msg should be("msg")
    }
  }
}
