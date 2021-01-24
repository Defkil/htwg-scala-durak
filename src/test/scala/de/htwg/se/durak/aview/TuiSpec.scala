package de.htwg.se.durak.aview

import de.htwg.se.durak.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.durak.controller.gameLogicComponent.gameLogicBaseImpl.GameLogic
import de.htwg.se.durak.model.gameElementsComponent.gameElementsBaseImpl.{Card, CardDeck, Field, GameElements}
import de.htwg.se.durak.model.roundComponent.roundBaseImpl.{Round, RoundData}
import org.scalatest._

class TuiSpec extends WordSpec with Matchers {
  "Tui" should  {
    val gameElements = new GameElements()
    val round = new Round()
    val gameRuntime = new Controller(gameElements, new GameLogic(gameElements, round), round)
    val tui = Tui(gameRuntime)
    tui.processInputLine("0")
    tui.processInputLine("a b c")
    "print" in {
      val stream = new java.io.ByteArrayOutputStream()
      val testInt = "5"
      Console.withOut(stream) {
        tui.print(testInt)
      }
      new String(stream.toByteArray).take(1) should be("5")
    }

    "spacer" in {
      val spacerTest = tui.spacer(5)
      spacerTest.size should be(5)
    }

    "closeScreen" in {
      val screen = tui.closeScreen("")
      screen.size should be(5)
      screen should be(List("", "", "", "", "Auf wiedersehen!"))
    }

    "menuScreen" in {
      val screen = tui.menuScreen("")
      screen.size should be(7)
      screen should be(List("Willkommen im Spiel Durak",
        "Folgende Befehle stehen zur Auswahl",
        "0     Spiel starten",
        "1     Konsole kalibrieren",
        "2     Multiplayer",
        "3     Spiel schließen",
        ""
      ))
    }

    "calibrationInfoScreen" in {
      val screen = tui.calibrationInfoScreen()
      screen.size should be(3)
      screen should be(List(
        "Im naechsten Fenster die hoechste sichtbare Zahl angeben",
        "Mit 0 die Kalibrierung starten, danach startet sich das Spiel neu",
        "mit der neuen Kalibrierung"
      ))
    }

    "calibrationListScreen" in {
      val screen = tui.calibrationListScreen()
      screen.size should be(20)
      screen should be(List("20","19","18","17","16","15","14","13","12","11","10","9","8","7","6","5","4","3","2","1"))
    }

    "playerScreen" in {
      val screen = tui.playerScreen("")
      screen.size should be(3)
      screen should be(List("Spielernamen getrennt mit einem Leerzeichen eingeben",
        "2-6 Spieler",
        ""
      ))
    }

    "nextTurnScreen" in {
      val screen = tui.nextTurnScreen("a")
      screen.size should be(7)
      screen should be(List("Nächster Spieler ist: " + gameRuntime.turnData.get.players(gameRuntime.turnData.get.currentPlayer).toString,
        "Im nächsten Fenster kann man je nach Situation mit s den Angriff beenden",
        "oder die Karten aufnehmen (als Verteidiger)",
        "Karten Legende:",
        "Karten Rang: 2 - 10 gleich, Bube: ♟, Dame: ♛, König: ♚, Ass: A",
        "Karten Symbol: Pik: ♠, Karo: ♦, Kreuz: ♣, Herz: ♥",
        "Fortfahren mit 0"
      ))
    }

    "finishedScreen" in {
      val screen = tui.finishedScreen(None)
      screen.size should be(2)
      screen should be(List("todo finishedScreen", ""))
    }

    "route" in {
      tui.route(-1, None).size should be(5)
      tui.route(0, None).size should be(7)
      tui.route(1, None).size should be(3)
      tui.route(2, None).size should be(20)
      tui.route(3, None).size should be(3)
      tui.route(4, None).size should be(2)
      tui.route(10, Some(List(""))).size should be(7)
      tui.route(11, Some(List(""))).size should be(8)
      tui.route(12, Some(List(""))).size should be(8)
    }

    "processInputLine" in {
      val gameElements = new GameElements()
      val round = new Round()
      val gameRuntime2 = new Controller(gameElements, new GameLogic(gameElements, round), round)
      val tui2 = Tui(gameRuntime)
      gameRuntime2.roundData.siteID should be(0)
      tui2.processInputLine("save")
      tui2.processInputLine("load")
      tui2.processInputLine("1")
      tui2.processInputLine("undo")
      tui2.processInputLine("redo")
      tui2.processInputLine("invalid input")
      gameRuntime2.roundData.siteID should be(0)
    }

    "processInputLine Error" in {
      val gameElements = new GameElements()
      val round = new Round()
      val gameRuntime3 = new Controller(gameElements, new GameLogic(gameElements, round), round)
      val tui3 = Tui(gameRuntime3)
      tui3.processInputLine("0")
      tui3.processInputLine("asd asdasd sdsds sdsds sd sds dsd ds sdsd")
      gameRuntime3.roundData.siteID should be(3)
    }

    "helperFormatCard" in {
      val gameElements = new GameElements()
      val round = new Round()
      val gameRuntime = new Controller(gameElements, new GameLogic(gameElements, round), round)
      val tui = Tui(gameRuntime)
      val stringToTest = tui.helperFormatCard(Card(2, 1))
      stringToTest.length should be(11)
    }

    "helperPrintField with one card and one option" in {
      val gameElements = new GameElements()
      val round = new Round()
      val gameRuntime = new Controller(gameElements, new GameLogic(gameElements, round), round)
      val tui = Tui(gameRuntime)

      val deffer = "Spieler 1"
      val actual = "Spieler 2"
      val field = new Field(CardDeck(List(Card(2,1))))
      val playerDeck = new CardDeck(List(Card(3,1), Card(4,2)))

      var res = tui.helperPrintField(deffer, actual, field, playerDeck, 6, 1, 0, List("0", "1"))
      res.toString should be("List(Verteidiger: Spieler 1\t\taktueller Angreifer: Spieler 2\tTrump: ♠\tRunden Typ: Angriff/Verteidigung, Karten im Stapel: 6,               --------------, Angriff:      | ♠ 2        |, Verteidigung: |            |,               --------------, Karten in der Hand: 0: (♠ 3), 1: (♦ 4), Mögliche Eingaben: 0, 1)")

      res = tui.helperPrintField(deffer, actual, field, playerDeck, 6, 1, 1, List("0", "1"))
      res.toString should be("List(Verteidiger: Spieler 1\t\taktueller Angreifer: Spieler 2\tTrump: ♠\tRunden Typ: Angriff vom vorherigen Spieler ausgesetzt, Karten im Stapel: 6,               --------------, Angriff:      | ♠ 2        |, Verteidigung: |            |,               --------------, Karten in der Hand: 0: (♠ 3), 1: (♦ 4), Mögliche Eingaben: 0, 1)")

      res = tui.helperPrintField(deffer, actual, new Field(), playerDeck, 6, 1, 2, List("0", "2"))
      println(res.toString)
      res.toString should be("List(Verteidiger: Spieler 1\t\taktueller Angreifer: Spieler 2\tTrump: ♠\tRunden Typ: Verteidiger nimmt die Karten auf, Karten im Stapel: 6,               --------------, Angriff:      |            |, Verteidigung: |            |,               --------------, Karten in der Hand: 0: (♠ 3), 1: (♦ 4), Mögliche Eingaben: 0, 2)")
    }

    "symbolUnicode" in {
      tui.symbolUnicode(1) should be("\u2660")
      tui.symbolUnicode(2) should be("\u2666")
      tui.symbolUnicode(3) should be("\u2663")
      tui.symbolUnicode(4) should be("\u2665")
    }

    "test unapply" in {
      Tui.unapply(tui)
    }
  }
}
