package de.htwg.se.durak.controller.gameLogicComponent.gameLogicBaseImpl

import de.htwg.se.durak.model.roundComponent.roundBaseImpl.Round
import org.scalatest._

class RoundDataFactorySpec extends WordSpec with Matchers {
  "RoundDataFactory" should {
    "create" in {
      val roundDataFactory = new RoundDataFactory(new Round)
      var elm = roundDataFactory.getInstance(-1)
      elm.siteID should be(-1)
      elm = roundDataFactory.getInstance(0)
      elm.siteID should be(0)
      elm = roundDataFactory.getInstance(1)
      elm.siteID should be(1)
      elm = roundDataFactory.getInstance(2)
      elm.siteID should be(2)
      elm = roundDataFactory.getInstance(3)
      elm.siteID should be(3)
      elm = roundDataFactory.getInstance(4)
      elm.siteID should be(4)
      elm = roundDataFactory.getInstance(10)
      elm.siteID should be(10)
      elm = roundDataFactory.getInstance(11)
      elm.siteID should be(11)
      elm = roundDataFactory.getInstance(12)
      elm.siteID should be(12)
      elm = roundDataFactory.getInstance(13)
      elm.siteID should be(13)
    }

    "create with full parameter" in {
      val roundDataFactory = new RoundDataFactory(new Round)
      var elm = roundDataFactory.getInstance(-1, None)
      elm.siteID should be(-1)
      elm = roundDataFactory.getInstance(0, None)
      elm.siteID should be(0)
      elm = roundDataFactory.getInstance(1, None)
      elm.siteID should be(1)
      elm = roundDataFactory.getInstance(2, None)
      elm.siteID should be(2)
      elm = roundDataFactory.getInstance(3, None)
      elm.siteID should be(3)
      elm = roundDataFactory.getInstance(4, None)
      elm.siteID should be(4)
      elm = roundDataFactory.getInstance(10, None)
      elm.siteID should be(10)
      elm = roundDataFactory.getInstance(11, None)
      elm.siteID should be(11)
      elm = roundDataFactory.getInstance(12, None)
      elm.siteID should be(12)
      elm = roundDataFactory.getInstance(13, None)
      elm.siteID should be(13)
    }
  }
}
