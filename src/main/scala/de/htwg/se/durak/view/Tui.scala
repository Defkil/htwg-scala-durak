package de.htwg.se.durak.view

import de.htwg.se.durak.controller.{GameRuntime, Observer, ObserverIn}
import de.htwg.se.durak.model.RoundData

case class Tui(gameRuntime: ObserverIn) extends Observer{
  var output: Array[String] = _
  var screenSize: Int = 10

  val MIN_SIZE = 6

  def update(): Unit = {
    println("ahh:: " + screenSize)
    output = getSite(gameRuntime.data)
  }

  def spacer(size: Int): Array[String] = {
    var res: Array[String] = Array()
    for(i <- 1 to size) res = res ++ Array("")
    res
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
    val temp = Array("Willkommen im Spiel Durak",
      "Folgende Befehle stehen zur Auswahl",
      "0     Spiel starten",
      "1     Konsole kalibrieren",
      "",
      msg
    )
    Array.concat(temp, spacer(screenSize - 6))
  }

  def calibrationInfoScreen(): Array[String] = {
   val temp = Array(
     "Im naechsten Fenster die hoechste sichtbare Zahl angeben",
     "Mit 0 die Kalibrierung starten, danach startet sich das Spiel neu",
     "mit der neuen Kalibrierung"
   )
    Array.concat(temp, spacer(screenSize - 3))
  }

  def calibrationListScreen(): Array[String] = {
    Array(
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

  def nullToString(param: Array[String], id: Int): String ={
    if(param != null && param(id) != null) param(id) else ""
  }
}
