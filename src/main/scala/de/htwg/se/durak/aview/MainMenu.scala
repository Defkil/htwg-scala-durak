package DurakGUI

import de.htwg.se.durak.controller.controllerComponent.ControllerInterface
import de.htwg.se.durak.controller.controllerComponent.controllerBaseImpl.SolveCommand
import javafx.scene.input.MouseEvent
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Scene
import scalafx.scene.control.{Button, TextField}
import scalafx.scene.image.ImageView
import scalafx.scene.layout.VBox
import scalafx.scene.paint.Color
import scalafx.scene.text.Text

class MainMenu(f:GUI, controller: ControllerInterface) extends Scene{
  private val playerNames = new Text("2 - 6 Spielernamen eingeben, durch Leerzeichen getrennt") {
    fill = Color.OrangeRed
  }

  fill = Color.rgb(38, 38, 38)
  content = new VBox {
    padding = Insets(40, 20, 40, 20)
    spacing = (20)
    alignment = Pos.Center
    alignmentInParent = Pos.CenterLeft
    children = Seq(
      new ImageView("de/htwg/se/durak/aview/Durak-Logo.png"),
      new Button("Spiel starten") {
        prefWidth = (200)
        prefHeight = (40)
        onMouseClicked = (t: MouseEvent) => startGame()
      },
      new Button("Multiplayer") {
        prefWidth = (200)
        prefHeight = (40)
        onMouseClicked = (t: MouseEvent) => println("Nicht implementiert")
      },
      playerNames,
      new TextField() {
        prefWidth = 200
        prefHeight = 40
      }
    )
  }
  private def startGame(): Unit = {
    f.input = playerNames.getText
  }
}
