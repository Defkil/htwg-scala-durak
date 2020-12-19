package de.htwg.se.durak.aview

import de.htwg.se.durak.controller.controllerComponent.controllerBaseImpl.Controller
import de.htwg.se.durak.controller.gameLogicComponent.gameLogicBaseImpl.GameLogic
import de.htwg.se.durak.model.gameElementsComponent.gameElementsBaseImpl.{Card, CardDeck, Field, GameElements}
import de.htwg.se.durak.model.roundComponent.roundBaseImpl.{Round, RoundData}
import org.scalatest._

class MockTui(runtime: Controller) extends Tui(runtime: Controller) {
  var messages: Seq[String] = Seq()

  override def print(s: String): Unit = messages = messages :+ s
}

class TuiSpec extends WordSpec with Matchers {
  "Tui" should  {
    val gameElements = new GameElements()
    val round = new Round()
    val gameRuntime = new Controller(gameElements, new GameLogic(gameElements, round), round)
    val mockTui = new MockTui(gameRuntime)
    val tui = Tui(gameRuntime)
    tui.processInputLine("0")
    tui.processInputLine("a b")
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
      tui.route(10, Some(List(""))).size should be(7)
      tui.route(11, Some(List(""))).size should be(8)
      tui.route(12, Some(List(""))).size should be(8)
    }

    "processInputLine" in {
      val gameElements = new GameElements()
      val round = new Round()
      val gameRuntime = new Controller(gameElements, new GameLogic(gameElements, round), round)
      val tui = Tui(gameRuntime)
      gameRuntime.roundData.siteID should be(0)
      tui.processInputLine("1")
      gameRuntime.roundData.siteID should be(1)
      tui.processInputLine("undo")
      gameRuntime.roundData.siteID should be(0)
      tui.processInputLine("invalid input")
      gameRuntime.roundData.siteID should be(0)
    }

    "helperFormatCard" in {
      val gameElements = new GameElements()
      val round = new Round()
      val gameRuntime = new Controller(gameElements, new GameLogic(gameElements, round), round)
      val tui = Tui(gameRuntime)
      val stringToTest = tui.helperFormatCard(Card(2, 1))
      stringToTest.length should be(11)
    }

    /*"helperPrintField with one card and one option" in {
      val gameElements = new GameElements()
      val round = new Round()
      val gameRuntime = new Controller(gameElements, new GameLogic(gameElements, round), round)
      val tui = Tui(gameRuntime)

      val deffer = "Spieler 1"
      val actual = "Spieler 2"
      val field = new Field(CardDeck(List(Card(2,1))))
      val playerDeck = new CardDeck(List(Card(3,1), Card(4,2)))
      val res = tui.helperPrintField(deffer, actual, field, playerDeck, List("0", "1"))
      res.length should be(7)
      res should be(List(
        "Verteildiger: Spieler 1",
        "              --------------",
        "Angriff:      | ♠ 2        |",
        "Verteidigung: |            |",
        "              --------------",
        "Karten in der Hand: 0: (♠ 3), 1: (♦ 4)",
        "Mögliche Eingaben: s, 0",
      ))
    }*/
  }
}
