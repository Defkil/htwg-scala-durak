package de.htwg.se.durak.controller

import de.htwg.se.durak.model.{CardStack, Field, GameData, Player, RoundData, TurnData}
import org.scalatest._
import org.junit.runner.{RunWith, Runner}
import org.scalatest.matchers.should.Matchers

@RunWith(classOf[Runner])
class GameRuntimeSpec extends WordSpec with Matchers {
  "A GameRuntime" when {
    val runtime = new GameRuntime()
    "size" in {
      runtime.roundStack = runtime.roundStack :+ new GameData(
        new RoundData(0, (s: String) => true, None),
        Some(new TurnData(List(new Player("name")), List(new CardStack()), 0,
          new Field(), "msg", new CardStack()))
      )
      runtime.turnData.get.msg should be("msg")
    }
  }
}
