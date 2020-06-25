package de.htwg.se.durak.aview

import de.htwg.se.durak.controller.{GameDataChanged, GameRuntime}

import scala.collection.mutable.ListBuffer
import scala.swing.Reactor

case class Tui(runtime: GameRuntime) extends Reactor {
  val MIN_SIZE = 6

  listenTo(runtime)

  def processInputLine(param: String): Unit = {
    if(param == "undo") {
      runtime.undo
      return
    }
    if(runtime.roundData.validateInput(param)) {
      runtime.runRound(param)
    } else {
      runtime.inputError()
    }
  }

  def print(string: String): Unit = println(string)

  reactions += {
    case event: GameDataChanged => printTui()
  }

  def printTui(): Unit = {
    val site = route(runtime.roundData.siteID, runtime.roundData.param)
    val output = site ++ spacer(runtime.screenSize - site.length)
    output.foreach(print)
  }

  def spacer(size: Int): List[String] = {
    var res: ListBuffer[String] = new ListBuffer[String]()
    for(i <- 1 to size) res += ""
    res.toList
  }

  def route(siteID: Int, param: Option[List[String]]): List[String] = {
    siteID match {
      case -1 => closeScreen()
      case 0 => menuScreen(param.getOrElse(List("")).head)
      case 1 => calibrationInfoScreen()
      case 2 => calibrationListScreen()
      case 3 => playerScreen(param.getOrElse(List("")).head)
      case 10 => nextTurnScreen(param.getOrElse(List("")).head)
      case 11 => attackerScreen(param)
      case 12 => defenderScreen(param)
      case 13 => finishedScreen(param)
    }
  }


  def closeScreen(): List[String] = {
    List("Auf wiedersehen!")
  }

  def menuScreen(param: String): List[String] = {
    List("Willkommen im Spiel Durak",
      "Folgende Befehle stehen zur Auswahl",
      "0     Spiel starten",
      "1     Konsole kalibrieren",
      "2     Multiplayer",
      "3     Spiel schließen",
      param
    )
  }

  def calibrationInfoScreen(): List[String] = {
   List(
     "Im naechsten Fenster die hoechste sichtbare Zahl angeben",
     "Mit 0 die Kalibrierung starten, danach startet sich das Spiel neu",
     "mit der neuen Kalibrierung"
   )
  }

  def calibrationListScreen(): List[String] = {
    List(
      "20",
      "19",
      "18",
      "17",
      "16",
      "15",
      "14",
      "13",
      "12",
      "11",
      "10",
      "9",
      "8",
      "7",
      "6",
      "5",
      "4",
      "3",
      "2",
      "1",
    )
  }

  def playerScreen(msg: String): List[String] = {
    List("Spielernamen getrennt mit einem Leerzeichen eingeben",
      "2-6 Spieler",
      msg
    )
  }

  def nextTurnScreen(msg: String): List[String] = {
    List("Naechster Spieler ist:",
      msg
    )
  }

  def attackerScreen(param: Option[List[String]]): List[String] = {
    List("todo attackerScreen",
      param.getOrElse(List("")).head
    )
  }

  def defenderScreen(param: Option[List[String]]): List[String] = {
    List("todo defenderScreen",
      param.getOrElse(List("")).head
    )
  }

  def finishedScreen(param: Option[List[String]]): List[String] = {
    List("todo finishedScreen",
      param.getOrElse(List("")).head
    )
  }

//  def fieldScreen(param: Option[List[String]]): List[String] = {
//
//  }
}
