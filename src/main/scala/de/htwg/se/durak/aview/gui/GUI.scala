package de.htwg.se.durak.aview.gui

import de.htwg.se.durak.aview.gui.scenes.{MainMenu, PlayScene, PlayerSelectScene, WaitScene}
import de.htwg.se.durak.controller.GameDataChanged
import de.htwg.se.durak.controller.controllerComponent.ControllerInterface
import javafx.application.Platform
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene

import scala.swing.Reactor


class GUI(controller: ControllerInterface) extends JFXApp with Reactor {
  var input = ""
  listenTo(controller)
  def updateGUI(): Unit = {
    if(controller.roundData.siteID == -1) {
      Platform.exit()
    } else {
      stage.scene = route(controller.roundData.siteID, controller.roundData.param)
    }
  }

  reactions += {
    case event: GameDataChanged => updateGUI()
  }

  stage = new PrimaryStage {
    title = "Durak"
  }
  updateGUI() // set scene

    def route(siteID: Int, param: Option[List[String]]): Scene = { //: List[String] = {
    siteID match {
      case 0 => new MainMenu(controller)
      case 3 => new PlayerSelectScene(controller)
      case 10 => new WaitScene(controller)
      case 11 => new PlayScene(controller)
      case 12 => new PlayScene(controller)
    }
  }
}
