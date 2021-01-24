package de.htwg.se.durak.aview.gui.scenes

import de.htwg.se.durak.aview.gui.GUI
import de.htwg.se.durak.controller.controllerComponent.ControllerInterface
import javafx.scene.input.MouseEvent
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Scene
import scalafx.scene.control.{Button, TextField}
import scalafx.scene.layout.VBox
import scalafx.scene.paint.Color

/**
 * Main scene of the game
 *
 * @param gui base gui instance
 * @param controller controller instance
 */
class MainMenu(gui: GUI, controller: ControllerInterface) extends Scene {
  fill = Color.rgb(38, 38, 38)
  content = new VBox {
    padding = Insets(40, 20, 40, 20)
    spacing = (20)
    alignment = Pos.Center
    alignmentInParent = Pos.CenterLeft
    children = Seq(
      new Button("Spiel starten") {
        prefWidth = (200)
        prefHeight = (40)
        onMouseClicked = (t: MouseEvent) => startGame()
      },
      new Button("Spiel schließen") {
        prefWidth = (200)
        prefHeight = (40)
        onMouseClicked = (t: MouseEvent) => closeGame()
      },
    )
  }

  /**
   * Starts the game
   */
  private def startGame(): Unit = {
    controller.solve("0")
  }

  /**
   * Close the game
   */
  private def closeGame(): Unit = {
    controller.solve("3")
  }
}
