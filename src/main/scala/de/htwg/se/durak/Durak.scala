package de.htwg.se.durak

import scala.io.StdIn.readLine
import de.htwg.se.durak.controller.GameRuntime
import de.htwg.se.durak.aview.Tui

object Durak {
  val runtime = new GameRuntime
  val tui = new Tui(runtime)
  runtime.notifyObservers

  def main(args: Array[String]): Unit = {
    var input: String = ""
    do {
      input = readLine()
      tui.processInputLine(input)
    } while (!runtime.roundData.siteID.equals(100))
  }
}

// param.getOrElse(List("")).head