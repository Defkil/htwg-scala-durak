package de.htwg.se.durak

import de.htwg.se.durak.model.Player

object Durak {
  def main(args: Array[String]): Unit = {
    val student = Player("Your Name")
    println("Hello, " + student.name)
  }
}
