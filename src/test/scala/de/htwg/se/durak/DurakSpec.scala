package de.htwg.se.durak

import java.io.ByteArrayInputStream

import org.scalatest._

class DurakSpec extends WordSpec with Matchers {
  "Durak" should  {
    "stat and close (site 0 -> site -1 -> exit gameloop)" in {
      Durak.controller.roundData.siteID should be(0)
      val in = new ByteArrayInputStream(("3").getBytes)
      Console.withIn(in)  {
        Durak.main(Array[String]())
        Durak.controller.roundData.siteID should be(-1)
      }
    }
  }
}
