package de.htwg.se.durak.model

import org.scalatest._
import org.junit.runner.{Runner, RunWith}

@RunWith(classOf[Runner])
class PlayerSpec extends WordSpec with Matchers {
  "A Player" when { "new" should {
    val player = Player("Your Name")
    "have a name"  in {
      player.name should be("Your Name")
    }
    "have a nice String representation" in {
      player.toString should be("Your Name")
    }
  }}


}
