package de.htwg.se.durak

import java.io.ByteArrayInputStream

import org.scalatest._
import org.junit.runner.{RunWith, Runner}
import org.scalatest.matchers.should.Matchers

@RunWith(classOf[Runner])
class DurakSpec extends WordSpec with Matchers {
  "Durak" should {
    "open menu" in {
      Durak.main(Array[String]("s"))
      Durak.runtime.roundData.siteID should be(0)
    }
  }
}
