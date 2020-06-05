package de.htwg.se.durak.view

import de.htwg.se.durak.controller.{GameRuntime, Observer, Router}
import de.htwg.se.durak.model.RoundData

case class Tui(gameRuntime: Router) extends Observer{
  var output: Array[String] = _

  val MIN_SIZE = 6
  val MAX_SIZE = 20

  def update(): Unit = {
    output = getSite(gameRuntime.data)
  }

  /**
   *
   * Sites:
   * 0 = menu
   * 1 = calibration info
   * 2 = calibration list 1 - 20
   * 10 = next turn
   * 11 = attacker
   * 12 = defender
   * 13 = finished
   *
   * @param roundData
   * @return
   */
  def getSite(roundData: RoundData): Array[String] = {
    roundData.siteID match {
      case 0 => menuScreen(nullToString(roundData.param, 0))
      case 1 => calibrationInfoScreen()
      case 2 => calibrationListScreen()
      case default => null
    }
  }

  def menuScreen(msg:String): Array[String] = {
    Array("Willkommen im Spiel Durak",
      "Folgende Befehle stehen zur Auswahl",
      "0     Spiel starten",
      "1     Konsole kalibrieren",
      "",
      msg
    )
  }

  def calibrationInfoScreen(): Array[String] = {
    Array(
      "Im naechsten Fenster die hoechste sichtbare Zahl angeben",
      "Mit 0 die Kalibrierung starten, danach startet sich das Spiel neu",
      "mit der neuen Kalibrierung"
    )
  }

  def calibrationListScreen(): Array[String] = {
    Array(
      "Im naechsten Fenster die hoechste sichtbare Zahl angeben",
      "Mit 0 die Kalibrierung starten, danach startet sich das Spiel neu",
      "mit der neuen Kalibrierung"
    )
  }

  def nullToString(param: Array[String], id: Int): String ={
    if(param != null && param(id) != null) param(id) else ""
  }
}
