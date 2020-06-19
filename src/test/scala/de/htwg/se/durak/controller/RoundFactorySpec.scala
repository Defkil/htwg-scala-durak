package de.htwg.se.durak.controller

import de.htwg.se.durak.model.RoundData
import org.scalatest._
import org.junit.runner.{RunWith, Runner}
import org.scalatest.matchers.should.Matchers


@RunWith(classOf[Runner])
class RoundFactorySpec  extends WordSpec with Matchers {
  "An RoundFactorySpec" should {
    val roundFactory = new RoundFactory
    "default routes -1" in {
      roundFactory.getInstance(-1, None).siteID should be(-1)
    }
    "default routes 0" in {
      roundFactory.getInstance(0, None).siteID should be(0)
    }
    "default routes 1" in {
      roundFactory.getInstance(1, None).siteID should be(1)
    }
    "default routes 2" in {
      roundFactory.getInstance(2, None).siteID should be(2)
    }
    "default routes 3" in {
      roundFactory.getInstance(3, None).siteID should be(3)
    }
    "default routes 10" in {
      roundFactory.getInstance(10, None).siteID should be(10)
    }
    "default routes 11" in {
      roundFactory.getInstance(11, None).siteID should be(11)
    }
    "default routes 12" in {
      roundFactory.getInstance(12, None).siteID should be(12)
    }
    "default routes 13" in {
      roundFactory.getInstance(13, None).siteID should be(13)
    }
    "default routes 404" in {
      roundFactory.getInstance(404, None).siteID should be(0)
    }
  }
}
