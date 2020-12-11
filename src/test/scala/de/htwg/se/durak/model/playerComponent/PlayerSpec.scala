package de.htwg.se.durak.model.playerComponent

import org.scalatest._

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
  "test unapply" in {
    val player = Player("Your Name")
    Player.unapply(player).get should be("Your Name")
  }

}
