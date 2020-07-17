package de.htwg.se.durak.aview

import de.htwg.se.durak.controller.GameDataChanged
import de.htwg.se.durak.controller.controllerComponent.ControllerInterface
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage

import scala.swing.Reactor

/**
 * Making a windows and making a new scene on each game turn
 * @param controller to get gamedata and add oneself to be updated
 */
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
    scene = new MainMenu(GUI.this,controller)
  }
  //def changeScene(index:Int):Unit = {
  //  stage = new PrimaryStage {
  //    index match {
  //      case 0 => scene = new MainMenu(GUI.this, controller)
  //      case 1 => scene = new Board(GUI.this, controller)
  //    }
  //  }
  //}

  def route(siteID: Int, param: Option[List[String]]): Unit = { //: List[String] = {
    println("Site-ID = " + siteID)
    println("param = " + param)
    println("input = " + input)
    siteID match {
      //case -1 => closeScreen()
      //case 0 => ""
      //case 1 => "" //calibrationInfoScreen()
      //case 2 => "" //calibrationListScreen()
      case 3 => controller.solve(input)
      case 10 => controller.solve("0")
      case 11 => stage.scene = new Board(GUI.this,controller)
      case 12 => stage.scene = new Board(GUI.this,controller)
      case 13 =>
      case 14 => //controller.solve("0")
      //case 15 => "" //finishedScreen(param)
    }
  }
}
