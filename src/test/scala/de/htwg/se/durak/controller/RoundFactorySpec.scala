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
      roundFactory.getInstance(-1, None).param should be(None)
    }
    "default routes 0" in {
      roundFactory.getInstance(0, None).siteID should be(0)
      roundFactory.getInstance(0, None).param should be(None)
    }
    "default routes 1" in {
      roundFactory.getInstance(1, None).siteID should be(1)
      roundFactory.getInstance(1, None).param should be(None)
    }
    "default routes 2" in {
      roundFactory.getInstance(2, None).siteID should be(2)
      roundFactory.getInstance(2, None).param should be(None)
    }
    "default routes 3" in {
      roundFactory.getInstance(3, None).siteID should be(3)
      roundFactory.getInstance(3, None).param should be(None)
    }
    "default routes 10" in {
      roundFactory.getInstance(10, None).siteID should be(10)
      roundFactory.getInstance(10, None).param should be(None)
    }
    "default routes 11" in {
      roundFactory.getInstance(11, None).siteID should be(11)
      roundFactory.getInstance(11, None).param should be(None)
    }
    "default routes 12" in {
      roundFactory.getInstance(12, None).siteID should be(12)
      roundFactory.getInstance(12, None).param should be(None)
    }
    "default routes 13" in {
      roundFactory.getInstance(13, None).siteID should be(13)
      roundFactory.getInstance(13, None).param should be(None)
    }
    "default routes 404" in {
      roundFactory.getInstance(404, None).siteID should be(0)
      roundFactory.getInstance(404, None).param should be(None)
    }
    "all again because of a bug from coveralls.io" in {
      roundFactory.getInstance(-1, None).siteID should be(-1)
      roundFactory.getInstance(0, None).siteID should be(0)
      roundFactory.getInstance(1, None).siteID should be(1)
      roundFactory.getInstance(2, None).siteID should be(2)
      roundFactory.getInstance(3, None).siteID should be(3)
      roundFactory.getInstance(10, None).siteID should be(10)
      roundFactory.getInstance(11, None).siteID should be(11)
      roundFactory.getInstance(12, None).siteID should be(12)
      roundFactory.getInstance(13, None).siteID should be(13)
      roundFactory.getInstance(404, None).siteID should be(0)
    }
  }
}
