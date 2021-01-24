package de.htwg.se.durak.controller.gameLogicComponent.gameLogicBaseImpl

import de.htwg.se.durak.model.roundComponent.roundBaseImpl.Round
import org.scalatest._

class RoundDataFactorySpec extends WordSpec with Matchers {
  "RoundDataFactory" should {
    "create" in {
      val roundDataFactory = new RoundDataFactory(new Round)
      val elm11 = roundDataFactory.getInstance(11)
      elm11.siteID should be(11)
      val elm12 = roundDataFactory.getInstance(12)
      elm12.siteID should be(12)
      val elm13 = roundDataFactory.getInstance(13)
      elm13.siteID should be(13)
      val elm13full = roundDataFactory.getInstance(13, None)
      elm13full.siteID should be(13)
    }
  }
}
