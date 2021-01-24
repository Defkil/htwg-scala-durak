package de.htwg.se.durak

import de.htwg.se.durak.controller.GameDataChanged
import org.scalatest.{Matchers, WordSpec}

class DurakGUISpec extends WordSpec with Matchers {
  "Durak" should  {
    "stat and close (site 0 -> site -1 -> exit gameloop)" in {
      DurakGUI.controller.roundData.siteID should be(0)
      DurakGUI.tui.processInputLine("3")
      DurakGUI.controller.roundData.siteID should be(-1)
    }
  }
}