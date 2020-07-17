package de.htwg.se.durak

import de.htwg.se.durak.controller.GameDataChanged
import de.htwg.se.durak.aview.{GUI, Tui}
import de.htwg.se.durak.controller.controllerComponent.ControllerInterface
import com.google.inject.{Guice, Injector}

import scala.io.StdIn.readLine

object DurakGUI {
  val injector: Injector = Guice.createInjector(new DurakModule)
  val controller: ControllerInterface = injector.getInstance(classOf[ControllerInterface])
  val gui = new GUI(controller)
  val tui: Tui = Tui(controller)
  controller.publish(new GameDataChanged)
  def main(args: Array[String]): Unit = {
    var input: String = ""
    gui.main(Array(""))
    do {
      input = readLine()
      tui.processInputLine(input)
    } while (!List(-1, 4, 5).contains(controller.roundStack.last.roundData.siteID))//controller.roundStack.last.roundData.siteID.equals(-1))
  }
}