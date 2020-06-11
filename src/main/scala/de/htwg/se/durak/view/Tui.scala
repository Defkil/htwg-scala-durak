package de.htwg.se.durak.view

import de.htwg.se.durak.controller.{Observer, ObserverData}

case class Tui(gameRuntime: ObserverData) extends Observer{
  var output: Array[String] = _
  var screenSize: Int = 10

  val MIN_SIZE = 6

  def update(): Unit = {
    output = route(gameRuntime.data.siteID, gameRuntime.data.param)
  }

  def spacer(size: Int): Array[String] = {
    var res: Array[String] = Array()
    for(i <- 1 to size) res = res ++ Array("")
    res
  }

  /*
   *
   * Sites:
   * 0 = menu
   * 1 = calibration info
   * 2 = calibration list 1 - 20
   * 3 = player name select
   * 10 = next turn
   * 11 = attacker
   * 12 = defender
   * 13 = finished
   */
  def route(siteID: Int, param: Array[String]): Array[String] = {
    siteID match {
      case 0 => menuScreen(nullToString(param, 0))
      case 1 => calibrationInfoScreen()
      case 2 => calibrationListScreen()
      case 3 => playerScreen(nullToString(param, 0))
      case 10 => nextTurnScreen(nullToString(param, 0))
      case 11 => attackerScreen(nullToString(param, 0))
      case 12 => defenderScreen(nullToString(param, 0))
      case 13 => finishedScreen(nullToString(param, 0))
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
    Array.concat(temp, spacer(screenSize - temp.length))
  }

  def calibrationInfoScreen(): Array[String] = {
   val temp = Array(
     "Im naechsten Fenster die hoechste sichtbare Zahl angeben",
     "Mit 0 die Kalibrierung starten, danach startet sich das Spiel neu",
     "mit der neuen Kalibrierung"
   )
    Array.concat(temp, spacer(screenSize - temp.length))
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
    println(param)
    if(param != null && param(id) != null) param(id) else ""
  }

  def playerScreen(msg:String): Array[String] = {
    val temp = Array("Spielernamen getrennt mit einem Leerzeichen eingeben",
      "2-6 Spieler",
      msg
    )
    Array.concat(temp, spacer(screenSize - temp.length))
  }

  def nextTurnScreen(msg:String): Array[String] = {
    val temp = Array("Naechster Spieler ist:",
      msg
    )
    Array.concat(temp, spacer(screenSize - temp.length - 1), Array("Mit 0 fortfahren"))
  }

  def attackerScreen(msg:String): Array[String] = {
    val temp = Array("Angriff, bitte Karte auswählen",
      msg
    )
    Array.concat(temp, spacer(screenSize - temp.length - 1), Array("Mit 0 fortfahren"))
  }

  def defenderScreen(msg:String): Array[String] = {
    val temp = Array("Defender",
      msg
    )
    Array.concat(temp, spacer(screenSize - temp.length - 1), Array("Mit 0 fortfahren"))
  }

  def finishedScreen(msg:String): Array[String] = {
    val temp = Array("todo finishedScreen",
      msg
    )
    Array.concat(temp, spacer(screenSize - temp.length - 1), Array("Mit 0 fortfahren"))
  }
}
