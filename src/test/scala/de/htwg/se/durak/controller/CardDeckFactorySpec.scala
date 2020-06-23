package de.htwg.se.durak.controller

import org.scalatest._
import org.junit.runner.{Runner, RunWith}
import org.scalatest.matchers.should.Matchers

@RunWith(classOf[Runner])
class CardDeckFactorySpec extends WordSpec with Matchers {
  "CardStackFactory" should {
    val cardStackFactory = new CardStackFactory
    "generate player stack" in {
      cardStackFactory.getInstance(0).size should be(0)
    }
    "generate playing stack" in {
      cardStackFactory.getInstance(1).size should be(0)
    }
    "generate outside stack" in {
      cardStackFactory.getInstance(2).size should be(0)
    }
  }
}
