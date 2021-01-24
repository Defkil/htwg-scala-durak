package de.htwg.se.durak.aview.gui.scenes

import de.htwg.se.durak.aview.gui.GUI
import de.htwg.se.durak.controller.controllerComponent.ControllerInterface
import javafx.scene.input.MouseEvent
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Scene
import scalafx.scene.control.{Button, Label}
import scalafx.scene.layout.VBox
import scalafx.scene.paint.Color

class WaitScene (controller: ControllerInterface) extends Scene {
  val info = new Label("Nächster Spieler ist: " + controller.turnData.get.players(controller.turnData.get.currentPlayer).toString)
  info.setTextFill(Color.web("#fff"))
  fill = Color.rgb(38, 38, 38)
  content = new VBox {
    padding = Insets(40, 20, 40, 20)
    spacing = (20)
    alignment = Pos.Center
    alignmentInParent = Pos.CenterLeft
    children = Seq(
      info,
      new Button("Fortfahren") {
        prefWidth = (200)
        prefHeight = (40)
        onMouseClicked = (t: MouseEvent) => beginTurn()
      },
    )
    private def beginTurn(): Unit = {
      controller.solve("0")
    }
  }
}