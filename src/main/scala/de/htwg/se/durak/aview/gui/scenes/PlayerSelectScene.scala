package de.htwg.se.durak.aview.gui.scenes

import de.htwg.se.durak.aview.gui.GUI
import de.htwg.se.durak.controller.controllerComponent.ControllerInterface
import javafx.scene.input.MouseEvent
import scalafx.geometry.{Insets, Pos}
import scalafx.scene.Scene
import scalafx.scene.control.{Button, Label, TextField}
import scalafx.scene.layout.VBox
import scalafx.scene.paint.Color

class PlayerSelectScene(controller: ControllerInterface) extends Scene {
  fill = Color.rgb(38, 38, 38)

  val info = new Label("Für jeden Spieler einen Namen hinzufügen. Mindestens zwei.")
  info.setTextFill(Color.web("#fff"))

  val playerOne = new TextField()
  val playerTwo = new TextField()
  val playerThree = new TextField()
  val playerFour = new TextField()
  val playerFive = new TextField()
  val playerSix = new TextField()

  content = new VBox {
    padding = Insets(40, 20, 40, 20)
    spacing = (20)
    alignment = Pos.Center
    alignmentInParent = Pos.CenterLeft
    children = Seq(
      info,
      playerOne,
      playerTwo,
      playerThree,
      playerFour,
      playerFive,
      playerSix,
      new Button("Spieler auswahl") {
        prefWidth = (200)
        prefHeight = (40)
        onMouseClicked = (t: MouseEvent) => startGame()
      },
    )
  }
  private def startGame(): Unit = {
    if(playerOne.getText() != "" && playerTwo.getText() != "") {
      var cmd = playerOne.getText() + " " + playerTwo.getText()
      if(playerThree.getText() != "") cmd += playerThree.getText()
      if(playerFour.getText() != "") cmd += playerFour.getText()
      if(playerFive.getText() != "") cmd += playerFive.getText()
      if(playerSix.getText() != "") cmd += playerSix.getText()
      controller.solve(cmd)
    }
  }
}
