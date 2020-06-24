package de.htwg.se.durak.aview

import de.htwg.se.durak.controller.GameRuntime

import org.scalatest._

class MockTui(runtime: GameRuntime) extends Tui(runtime: GameRuntime) {
  var messages: Seq[String] = Seq()

  override def print(s: String): Unit = messages = messages :+ s
}

class TuiSpec extends WordSpec with Matchers {
  "Tui" should  {
    val gameRuntime = new GameRuntime
    val mockTui = new MockTui(gameRuntime)
    val tui = Tui(gameRuntime)
    "update" in {
      mockTui.update()
      mockTui.messages should be(List("Willkommen im Spiel Durak", "Folgende Befehle stehen zur Auswahl", "0     Spiel starten", "1     Konsole kalibrieren", "2     Multiplayer", "3     Spiel schließen", "", "", "", "")
      )
    }
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
      val screen = tui.closeScreen()
      screen.size should be(1)
      screen should be(List("Auf wiedersehen!"))
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
      val screen = tui.nextTurnScreen("")
      screen.size should be(2)
      screen should be(List("Naechster Spieler ist:",
        ""
      ))
    }

    "attackerScreen" in {
      val screen = tui.attackerScreen(None)
      screen.size should be(2)
      screen should be(List("todo attackerScreen", ""))
    }

    "defenderScreen" in {
      val screen = tui.defenderScreen(None)
      screen.size should be(2)
      screen should be(List("todo defenderScreen", ""))
    }

    "finishedScreen" in {
      val screen = tui.finishedScreen(None)
      screen.size should be(2)
      screen should be(List("todo finishedScreen", ""))
    }

    "route" in {
      tui.route(-1, None).size should be(1)
      tui.route(0, None).size should be(7)
      tui.route(1, None).size should be(3)
      tui.route(2, None).size should be(20)
      tui.route(3, None).size should be(3)
      tui.route(10, None).size should be(2)
      tui.route(11, None).size should be(2)
      tui.route(12, None).size should be(2)
      tui.route(13, None).size should be(2)
    }

    "processInputLine" in {
      val gameRuntime = new GameRuntime
      val tui = Tui(gameRuntime)
      gameRuntime.roundData.siteID should be(0)
      tui.processInputLine("1")
      gameRuntime.roundData.siteID should be(1)
      tui.processInputLine("undo")
      gameRuntime.roundData.siteID should be(0)
      tui.processInputLine("invalid input")
      gameRuntime.roundData.siteID should be(0)
    }
  }
}
