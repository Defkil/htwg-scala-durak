package de.htwg.se.durak
import de.htwg.se.durak.controller.GameDataChanged
import de.htwg.se.durak.aview.Tui
import de.htwg.se.durak.controller.controllerComponent.ControllerInterface
import com.google.inject.{Guice, Injector}
import scala.io.StdIn.readLine

object DurakTUI {
  val injector: Injector = Guice.createInjector(new DurakModule)
  val controller: ControllerInterface = injector.getInstance(classOf[ControllerInterface])
  val tui: Tui = Tui(controller)

  controller.publish(new GameDataChanged)

  /**
   * Starting game TUI
   *
   * @param args Start arguments
   */
  def main(args: Array[String]): Unit = {
    var input: String = ""
    while (!List(-1, 4, 5).contains(controller.roundStack.last.roundData.siteID)) {
      input = readLine()
      tui.processInputLine(input)
    }
  }
}

