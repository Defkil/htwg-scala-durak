package de.htwg.se.durak.aview

import de.htwg.se.durak.controller.GameDataChanged
import de.htwg.se.durak.controller.controllerComponent.ControllerInterface
import de.htwg.se.durak.model.gameElementsComponent.{CardDeckInterface, CardInterface, FieldInterface}

import scala.collection.mutable.ListBuffer
import scala.swing.Reactor

case class Tui(controller: ControllerInterface) extends Reactor {
  val MIN_SIZE = 6

  listenTo(controller)

  def processInputLine(param: String): Unit = {
    param match {
      case "redo" => controller.redo
      case "undo" => controller.undo
      case "save" => controller.save
      case "load" => controller.load
      case _ =>
        val roundData = controller.roundData
        if (roundData.validateInputList.head == "func")
          if (roundData.validateInput.get(param)) controller.solve(param) else controller.inputError
        else
          if (roundData.validateInputList.contains(param)) controller.solve(param) else controller.inputError
    }
  }

  def print(string: String): Unit = println(string)

  reactions += {
    case event: GameDataChanged => printTui()
  }

  def printTui(): Unit = {
    val site = route(controller.roundData.siteID, controller.roundData.param)
    val output = site ++ spacer(controller.screenSize - site.length)
    output.foreach(print)
  }

  def spacer(size: Int): List[String] = {
    var res: ListBuffer[String] = new ListBuffer[String]()
    for(i <- 1 to size) res += ""
    res.toList
  }

  def route(siteID: Int, param: Option[List[String]]): List[String] = {
    siteID match {
      case -1 => closeScreen(param.getOrElse(List("")).head)
      case 0 => menuScreen(param.getOrElse(List("")).head)
      case 1 => calibrationInfoScreen()
      case 2 => calibrationListScreen()
      case 3 => playerScreen(param.getOrElse(List("")).head)
      case 4 => saveScreen()
      case 10 => nextTurnScreen(param.getOrElse(List("")).head)
      case 11 => playScreen(param)
      case 12 => playScreen(param)
    }
  }


  def closeScreen(param: String): List[String] = {
    List(param,"","","","Auf wiedersehen!")
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

  def saveScreen(): List[String] = {
    List(
      "Das Spiel wurde gespeichert",
      "Auf wiedersehen!"
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
    def createCard(rank: Int, symbol: Int) = controller.gameElements.createCard(rank, symbol)
    List("Nächster Spieler ist: " + controller.turnData.get.players(controller.turnData.get.currentPlayer).toString,
      "Im nächsten Fenster kann man je nach Situation mit s den Angriff beenden",
      "oder die Karten aufnehmen (als Verteidiger)",
      "Karten Legende:",
      "Karten Rang: 2 - 10 gleich, Bube: " + createCard(11, 1).rankUnicode + ", "
        + "Dame: " + createCard(12, 1).rankUnicode + ", "
        + "König: " + createCard(13, 1).rankUnicode + ", "
        + "Ass: " + createCard(14, 1).rankUnicode,
      "Karten Symbol: Pik: " + createCard(2, 1).symbolUnicode + ", Karo: " + createCard(2, 2).symbolUnicode
        + ", Kreuz: " + createCard(2, 3).symbolUnicode + ", Herz: " + createCard(2, 4).symbolUnicode,
      "Fortfahren mit 0"
    )
  }


  def finishedScreen(param: Option[List[String]]): List[String] = {
    List("todo finishedScreen",
      param.getOrElse(List("")).head
    )
  }

  def helperFormatCard(card: CardInterface): String = {
    var res = card.symbolUnicode + " " + card.rankUnicode
    for(i <- Range(res.length, 11, 1)) res += " "
    res
  }

  def helperSpacerString(size: Int): String = {
    var res = ""
    for(i <- Range(0, size, 1)) res += "-"
    res
  }

  def symbolUnicode(id: Int): String = id match {
    case 1 => "\u2660"
    case 2 => "\u2666"
    case 3 => "\u2663"
    case 4 => "\u2665"
  }

  def playScreen(param: Option[List[String]]): List[String] = {
    val turnData = controller.turnData.get
    helperPrintField(
      turnData.players(turnData.defendPlayer).toString,
      turnData.players(turnData.currentPlayer).toString,
      turnData.field,
      turnData.playerDecks(turnData.currentPlayer),
      turnData.mainDeck.deck.length,
      turnData.trump,
      turnData.turnType,
      controller.roundData.validateInputList
    )
  }



  def helperPrintField(deffer: String, actual: String, field: FieldInterface
                       , playerDeck: CardDeckInterface, mainDeckLength: Int, trump: Int, turnType: Int
                       , cardOptions: List[String]): List[String] = {
    var fieldFirstLine = ""
    var fieldSecondLine = ""
    var playerCards = ""
    var possibleInput = ""

    // generate Field
    for (i <- Range(0, field.size, 2)) {
      fieldFirstLine += "| " + helperFormatCard(field.deck(i))
      if(i + 1 < field.size) fieldSecondLine += "| " + helperFormatCard(field.deck(i + 1))
      else fieldSecondLine += "|            "
    }
    if(fieldFirstLine.length == 0) {
      fieldFirstLine =  "|            "
      fieldSecondLine = "|            "
    }

    // generate PlayerDeck
    for (i <- Range(0, playerDeck.deck.size, 1)) {
      if(i != 0) playerCards += ", "
      playerCards += i + ": (" + playerDeck.deck(i).symbolUnicode + " " + playerDeck.deck(i).rankUnicode + ")"
    }

    // list possible input options
    if(cardOptions.head != "func") {
      for(i <- Range(0, cardOptions.length, 1)) {
        possibleInput += cardOptions(i) + ", "
      }
    }
    possibleInput = possibleInput.dropRight(2)

    // add attacker if its not the turn of the deffer
    val playerMsg = if(deffer != actual) "Verteidiger: " + deffer +"\t\taktueller Angreifer: " + actual else "aktueller Verteildiger: \t" + deffer

    val turnTypeMsg = "\tRunden Typ: " + (turnType match {
      case 0 => "Angriff/Verteidigung"
      case 1 => "Angriff vom vorherigen Spieler ausgesetzt"
      case 2 => "Verteidiger nimmt die Karten auf"
    })
    println(deffer + " - " + actual)
    List(
      playerMsg + "\tTrump: " + symbolUnicode(trump) + turnTypeMsg,
      "Karten im Stapel: " + mainDeckLength,
      "              " + helperSpacerString(fieldFirstLine.length + 1),
      "Angriff:      " + fieldFirstLine + "|",
      "Verteidigung: " + fieldSecondLine + "|",
      "              " + helperSpacerString(fieldFirstLine.length + 1),
      "Karten in der Hand: " + playerCards,
      "Mögliche Eingaben: " + possibleInput
    )
  }
}
