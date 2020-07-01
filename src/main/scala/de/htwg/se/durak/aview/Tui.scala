package de.htwg.se.durak.aview

import de.htwg.se.durak.controller.{GameDataChanged, GameRuntime}
import de.htwg.se.durak.model.{Card, CardDeck, Field}

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
    if(runtime.roundData.validateInput.isDefined) {
      if(runtime.roundData.validateInput.get(param)) runtime.runRound(param)
      else runtime.inputError()
    } else {
      if(runtime.roundData.validateInputList.get.contains(param)) runtime.runRound(param)
      else runtime.inputError()
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
      case 12 => playerScreen(param.getOrElse(List("")).head)
      case 13 => defenderScreen(param)
      case 14 => playerScreen(param.getOrElse(List("")).head)
      case 15 => finishedScreen(param)
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
    List("Nächster Spieler ist: " + msg,
      "Im nächsten Fenster kann man je nach Situation mit s den Angriff beenden",
      "oder die Karten aufnehmen (als Verteidiger)",
      "Karten Legende:",
      "Karten Rang: 2 - 10 gleich, Bube: " + Card(11, 1).rankUnicode + ", "
        + "Dame: " + Card(12, 1).rankUnicode + ", "
        + "König: " + Card(13, 1).rankUnicode + ", "
        + "Ass: " + Card(14, 1).rankUnicode,
      "Karten Symbol: Pik: " + Card(2, 1).symbolUnicode + ", Karo: " + Card(2, 2).symbolUnicode
        + ", Kreuz: " + Card(2, 3).symbolUnicode + ", Herz: " + Card(2, 4).symbolUnicode
    )
  }

  def attackerScreen(param: Option[List[String]]): List[String] = {
    val turnData = runtime.turnData.get
    helperPrintField(
      turnData.players(turnData.defendPlayer).toString,
      turnData.field,
      turnData.playerDecks(turnData.currentPlayer),
      if(param.isDefined) param.get.head else ""
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

  def helperFormatCard(card: Card): String = {
    var res = card.symbolUnicode + " " + card.rankUnicode
    for(i <- Range(res.length, 11, 1)) res += " "
    res
  }

  def helperSpacerString(size: Int): String = {
    var res = ""
    for(i <- Range(0, size, 1))
      res += "-"
    res
  }

  def helperPrintField(deffer: String, field: Field, playerDeck: CardDeck, cardOptions: String): List[String] = {
    var fieldFirstLine = ""
    var fieldSecondLine = ""
    var playerCards = ""
    var possibleInput = "s"

    // generate Field
    for (i <- Range(0, field.size, 2)) {
      fieldFirstLine += "| " + helperFormatCard(field.deck(i))
      if(i + 1 < field.size) fieldSecondLine += "| " + helperFormatCard(field.deck(i + 1))
      else fieldSecondLine += "|            "
    }
    if(fieldFirstLine.length == 0) {
      fieldFirstLine = "|            "
      fieldSecondLine = "|            "
    }

    // generate PlayerDeck
    for (i <- Range(0, playerDeck.deck.size, 1)) {
      if(i != 0) playerCards += ", "
      playerCards += i + ": (" + playerDeck.deck(i).symbolUnicode + " " + playerDeck.deck(i).rankUnicode + ")"
    }

    // list possible input options
    possibleInput += cardOptions

    List(
      "Verteildiger: " + deffer,
      "              " + helperSpacerString(fieldFirstLine.length + 1),
      "Angriff:      " + fieldFirstLine + "|",
      "Verteidigung: " + fieldSecondLine + "|",
      "              " + helperSpacerString(fieldFirstLine.length + 1),
      "Karten in der Hand: " + playerCards,
      "Mögliche Eingaben: " + possibleInput
    )
  }
//  def fieldScreen(param: Option[List[String]]): List[String] = {
//
//  }
}
