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
    if (args.length > 0) input = args(0)
    if (!input.isEmpty) tui.processInputLine(input)
    else do {
      input = readLine()
      tui.processInputLine(input)
    } while (!runtime.roundData.siteID.equals(100))
  }
}
