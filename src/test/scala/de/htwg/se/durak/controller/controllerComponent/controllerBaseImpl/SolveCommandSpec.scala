package de.htwg.se.durak.controller.controllerComponent.controllerBaseImpl

import de.htwg.se.durak.aview.Tui
import de.htwg.se.durak.controller.gameLogicComponent.gameLogicBaseImpl.GameLogic
import de.htwg.se.durak.model.gameElementsComponent.gameElementsBaseImpl.GameElements
import de.htwg.se.durak.model.roundComponent.roundBaseImpl.Round
import org.scalatest._

class SolveCommandSpec  extends WordSpec with Matchers {
  "SolveCommand" should {
    "play a game" in {
      val gameElements = new GameElements()
      val round = new Round()
      val gameRuntime = new Controller(gameElements, new GameLogic(gameElements, round), round)
      val tui = Tui(gameRuntime)
      tui.processInputLine("0")
      tui.processInputLine("a b")
      tui.processInputLine("0")
      tui.processInputLine("0")
      tui.processInputLine("0")
      tui.processInputLine("s")
      tui.processInputLine("0")
      tui.processInputLine("s")
      gameRuntime.roundData.siteID should be(10)
    }

    "calibration of the screen" in {
      val gameElements = new GameElements()
      val round = new Round()
      val gameRuntime = new Controller(gameElements, new GameLogic(gameElements, round), round)
      val tui = Tui(gameRuntime)
      tui.processInputLine("1")
      tui.processInputLine("0")
      tui.processInputLine("10")
      gameRuntime.roundData.siteID should be(0)
    }
  }
}
