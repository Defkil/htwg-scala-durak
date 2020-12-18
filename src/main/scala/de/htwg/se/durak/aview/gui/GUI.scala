package de.htwg.se.durak.aview.gui

import de.htwg.se.durak.aview.gui.scenes.{MainMenu, PlayScene, PlayerSelectScene, WaitScene}
import de.htwg.se.durak.controller.GameDataChanged
import de.htwg.se.durak.controller.controllerComponent.ControllerInterface
import javafx.application.Platform
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage

import scala.swing.Reactor


class GUI(controller: ControllerInterface) extends JFXApp with Reactor {
  var input = ""
  listenTo(controller)
  def updateGUI(): Unit = {
    route(controller.roundData.siteID, controller.roundData.param)
  }

  reactions += {
    case event: GameDataChanged => updateGUI()
  }

  stage = new PrimaryStage {
    title = "Durak"
    scene = new MainMenu(GUI.this, controller)
  }

  def route(siteID: Int, param: Option[List[String]]): Unit = { //: List[String] = {
    siteID match {
      case -1 => Platform.exit()
      case 0 => stage.scene =  new MainMenu(GUI.this, controller)
      case 3 => stage.scene = new PlayerSelectScene(GUI.this, controller)
      case 10 => stage.scene = new WaitScene(GUI.this, controller)
      case 11 => stage.scene = new PlayScene(GUI.this, controller)
      case 12 => stage.scene = new PlayScene(GUI.this, controller)
    }
  }
}
