package de.htwg.se.durak.model.roundComponent.roundBaseImpl

import org.scalatest._

class RoundDataSpec extends WordSpec with Matchers {
  "GameData" should {
    "shortcut function" in {
      val roundData = new RoundData(0, List("a"))
      roundData.siteID should be(0)
      roundData.validateInputList.head should be("a")
    }

    "full parameter" in {
      val roundData = new RoundData(0, List("a"), None, None)
      roundData.siteID should be(0)
      roundData.validateInputList.head should be("a")
    }
  }

  "test unapply" in {
    val roundData = new RoundData(0, List("a"))
    RoundData.unapply(roundData)
  }
}
