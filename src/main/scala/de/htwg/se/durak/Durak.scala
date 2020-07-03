package de.htwg.se.durak

import com.google.inject.{Guice, Injector}

import scala.io.StdIn.readLine
import de.htwg.se.durak.controller.GameDataChanged
import de.htwg.se.durak.aview.Tui
import de.htwg.se.durak.controller.controllerComponent.ControllerInterface

object Durak {
  val injector: Injector = Guice.createInjector(new DurakModule)
  val controller: ControllerInterface = injector.getInstance(classOf[ControllerInterface])
  val tui: Tui = Tui(controller)
  controller.publish(new GameDataChanged)

  def main(args: Array[String]): Unit = {
    var input: String = ""

    do {
      input = readLine()
      tui.processInputLine(input)
    } while (!controller.roundStack.last.roundData.siteID.equals(-1))
  }
}
