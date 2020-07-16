package DurakGUI

import de.htwg.se.durak.controller.GameDataChanged
import de.htwg.se.durak.controller.controllerComponent.ControllerInterface
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

  def attackerScreen(param: Option[List[String]]) = {
    stage.scene = new Board(this,controller)
  }

  def defenderScreen(param: Option[List[String]]) = {
    stage.scene = new Board(this,controller)
  }

  def route(siteID: Int, param: Option[List[String]]): Unit = { //: List[String] = {
    println("Site-ID = " + siteID)
    siteID match {
      //case -1 => closeScreen()
      //case 0 => ""
      //case 1 => "" //calibrationInfoScreen()
      //case 2 => "" //calibrationListScreen()
      case 3 => controller.solve(input)
      case 10 => println("Hallo")
      case 11 => attackerScreen(param)
      //case 12 => "" //playerScreen(param.getOrElse(List("")).head)
      case 13 => defenderScreen(param)
      case 14 => controller.solve("0")
      //case 15 => "" //finishedScreen(param)
    }
  }
}
