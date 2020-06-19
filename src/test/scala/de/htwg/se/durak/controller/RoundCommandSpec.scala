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
      runtime.roundStack = List(new GameData(new RoundData(0, (s: String) => true, None), None))
      val roundCommand1 = new RoundCommand("0", runtime)
      runtime.roundData.siteID should be(0)
      roundCommand1.doStep
      runtime.roundData.siteID should be(3)

      runtime.roundStack = List(new GameData(new RoundData(0, (s: String) => true, None), None))
      val roundCommand2 = new RoundCommand("1", runtime)
      runtime.roundData.siteID should be(0)
      roundCommand2.doStep
      runtime.roundData.siteID should be(1)

      runtime.roundStack = List(new GameData(new RoundData(0, (s: String) => true, None), None))
      val roundCommand3 = new RoundCommand("3", runtime)
      runtime.roundData.siteID should be(0)
      roundCommand3.doStep
      runtime.roundData.siteID should be(-1)
    }

    "open site 1" in {
      val runtime = new GameRuntime
      runtime.roundStack = List(new GameData(new RoundData(1, (s: String) => true, None), None))
      val roundCommand1 = new RoundCommand("0", runtime)
      runtime.roundData.siteID should be(1)
      roundCommand1.doStep
      runtime.roundData.siteID should be(2)
    }

    "open site 2" in {
      val runtime = new GameRuntime
      runtime.roundStack = List(new GameData(new RoundData(2, (s: String) => true, None), None))
      val roundCommand1 = new RoundCommand("11", runtime)
      runtime.roundData.siteID should be(2)
      roundCommand1.doStep
      runtime.roundData.siteID should be(0)
      runtime.screenSize should be(11)
    }

    "open site 3" in {
      val runtime = new GameRuntime
      runtime.roundStack = List(new GameData(new RoundData(3, (s: String) => true, None), None))
      val roundCommand1 = new RoundCommand("0", runtime)
      runtime.roundData.siteID should be(3)
      roundCommand1.doStep
      runtime.roundData.siteID should be(10)
    }

    "open site 10" in {
      val runtime = new GameRuntime
      runtime.roundStack = List(new GameData(new RoundData(10, (s: String) => true, None), None))
      val roundCommand1 = new RoundCommand("0", runtime)
      runtime.roundData.siteID should be(10)
      roundCommand1.doStep
      runtime.roundData.siteID should be(11)
    }

    "open site 11" in {
      val runtime = new GameRuntime
      runtime.roundStack = List(new GameData(new RoundData(11, (s: String) => true, None), None))
      val roundCommand1 = new RoundCommand("0", runtime)
      runtime.roundData.siteID should be(11)
      roundCommand1.doStep
      runtime.roundData.siteID should be(10)
    }

    "open site 12" in {
      val runtime = new GameRuntime
      runtime.roundStack = List(new GameData(new RoundData(12, (s: String) => true, None), None))
      val roundCommand1 = new RoundCommand("0", runtime)
      runtime.roundData.siteID should be(12)
      roundCommand1.doStep
      runtime.roundData.siteID should be(13)
    }

    "open site 13" in {
      val runtime = new GameRuntime
      runtime.roundStack = List(new GameData(new RoundData(13, (s: String) => true, None), None))
      val roundCommand1 = new RoundCommand("0", runtime)
      runtime.roundData.siteID should be(13)
      roundCommand1.doStep
      runtime.roundData.siteID should be(0)
    }
  }
}
