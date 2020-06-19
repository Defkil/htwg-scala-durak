package de.htwg.se.durak.controller

import de.htwg.se.durak.model.{GameData, RoundData}
import org.scalatest._
import org.junit.runner.{RunWith, Runner}
import org.scalatest.matchers.should.Matchers

@RunWith(classOf[Runner])
class RoundCommandSpec extends WordSpec with Matchers {
  "A RoundCommand" when {
    "open site 0" in {
      val runtime = new GameRuntime
      runtime.roundStack = runtime.roundStack :+ new GameData(new RoundData(0, (s: String) => true, None), None)
      val roundCommand = new RoundCommand("3", runtime)
      runtime.roundData.siteID should be(0)
      roundCommand.doStep
      runtime.roundData.siteID should be(-1)
    }
  }
}
