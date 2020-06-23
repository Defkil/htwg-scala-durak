package de.htwg.se.durak.controller

import de.htwg.se.durak.model.RoundData
import org.scalatest._
import org.junit.runner.{RunWith, Runner}
import org.scalatest.matchers.should.Matchers


@RunWith(classOf[Runner])
class RoundFactorySpec  extends WordSpec with Matchers {
  "An RoundFactorySpec" should {
    "default routes -1" in {
      val roundFactory = new RoundFactory
      var temp = roundFactory.getInstance(-1, None)
      temp.siteID should be(-1)
      temp.param should be(None)
    }
    "default routes 0" in {
      val roundFactory = new RoundFactory
      var temp = roundFactory.getInstance(0, None)
      temp.siteID should be(0)
      temp.param should be(None)
    }
    "default routes 1" in {
      val roundFactory = new RoundFactory
      var temp = roundFactory.getInstance(1, None)
      temp.siteID should be(1)
      temp.param should be(None)
    }
    "default routes 2" in {
      val roundFactory = new RoundFactory
      var temp = roundFactory.getInstance(2, None)
      temp.siteID should be(2)
      temp.param should be(None)
    }
    "default routes 3" in {
      val roundFactory = new RoundFactory
      var temp = roundFactory.getInstance(3, None)
      temp.siteID should be(3)
      temp.param should be(None)
    }
    "default routes 10" in {
      val roundFactory = new RoundFactory
      var temp = roundFactory.getInstance(10, None)
      temp.siteID should be(10)
      temp.param should be(None)
    }
    "default routes 11" in {
      val roundFactory = new RoundFactory
      var temp = roundFactory.getInstance(11, None)
      temp.siteID should be(11)
      temp.param should be(None)
    }
    "default routes 12" in {
      val roundFactory = new RoundFactory
      var temp = roundFactory.getInstance(12, None)
      temp.siteID should be(12)
      temp.param should be(None)
    }
    "default routes 13" in {
      val roundFactory = new RoundFactory
      var temp = roundFactory.getInstance(13, None)
      temp.siteID should be(13)
      temp.param should be(None)
    }
    "default routes 404" in {
      val roundFactory = new RoundFactory
      var temp = roundFactory.getInstance(404, None)
      temp.siteID should be(0)
      temp.param should be(None)
    }
    "all again because of a bug from coveralls.io" in {
      val roundFactory = new RoundFactory
      var temp = roundFactory.getInstance(-1, None).siteID should be(-1)
      temp = roundFactory.getInstance(-1, None).param should be(None)
      temp = roundFactory.getInstance(0, None).siteID should be(0)
      temp = roundFactory.getInstance(0, None).param should be(None)
      temp = roundFactory.getInstance(1, None).siteID should be(1)
      temp = roundFactory.getInstance(1, None).param should be(None)
      temp = roundFactory.getInstance(2, None).siteID should be(2)
      temp = roundFactory.getInstance(2, None).param should be(None)
      temp = roundFactory.getInstance(3, None).siteID should be(3)
      temp = roundFactory.getInstance(3, None).param should be(None)
      temp = roundFactory.getInstance(10, None).siteID should be(10)
      temp = roundFactory.getInstance(10, None).param should be(None)
      temp = roundFactory.getInstance(11, None).siteID should be(11)
      temp = roundFactory.getInstance(11, None).param should be(None)
      temp = roundFactory.getInstance(12, None).siteID should be(12)
      temp = roundFactory.getInstance(12, None).param should be(None)
      temp = roundFactory.getInstance(13, None).siteID should be(13)
      temp = roundFactory.getInstance(13, None).param should be(None)
      temp = roundFactory.getInstance(404, None).siteID should be(0)
      temp = roundFactory.getInstance(404, None).param should be(None)
    }
  }
}
