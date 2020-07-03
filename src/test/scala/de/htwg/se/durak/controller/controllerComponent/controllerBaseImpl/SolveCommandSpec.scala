package de.htwg.se.durak.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.durak.model.{GameData, RoundData}
import org.scalatest._

class SolveCommandSpec extends WordSpec with Matchers {
  /*"A RoundCommand" when {
    "open site 0" in {
      val runtime = new Controller
      runtime.roundStack = List(new GameData(new RoundData(0, List("func"), Some((s: String) => true), None), None))
      val roundCommand1 = new SolveCommand("0", runtime)
      runtime.roundData.siteID should be(0)
      roundCommand1.doStep
      runtime.roundData.siteID should be(3)

      runtime.roundStack = List(new GameData(new RoundData(0, List("func"), Some((s: String) => true), None), None))
      val roundCommand2 = new SolveCommand("1", runtime)
      runtime.roundData.siteID should be(0)
      roundCommand2.doStep
      runtime.roundData.siteID should be(1)

      runtime.roundStack = List(new GameData(new RoundData(0, List("func"), Some((s: String) => true), None), None))
      val roundCommand3 = new SolveCommand("3", runtime)
      runtime.roundData.siteID should be(0)
      roundCommand3.doStep
      runtime.roundData.siteID should be(-1)
    }

    "open site 1" in {
      val runtime = new Controller
      runtime.roundStack = List(new GameData(new RoundData(1, List("func"), Some((s: String) => true), None), None))
      val roundCommand1 = new SolveCommand("0", runtime)
      runtime.roundData.siteID should be(1)
      roundCommand1.doStep
      runtime.roundData.siteID should be(2)
    }

    "open site 2" in {
      val runtime = new Controller
      runtime.roundStack = List(new GameData(new RoundData(2, List("func"), Some((s: String) => true), None), None))
      val roundCommand1 = new SolveCommand("11", runtime)
      runtime.roundData.siteID should be(2)
      roundCommand1.doStep
      runtime.roundData.siteID should be(0)
      runtime.screenSize should be(11)
    }

    /*
    dissabled sites
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
    }*/
  }*/
}
