package de.htwg.se.durak.utilities

import java.util.Scanner

import de.htwg.se.durak.model.{Card, CardStack, Field, Player}

import scala.collection.mutable.ListBuffer

case class TuiOld(s:Scanner) {
  final val defaultSize = 10
  final val defaultTopSpace = 0
  var cmdSize: Int = defaultSize

  def clear(): Unit = spacer(cmdSize)

  def write(msg: String): Unit = write(Array(msg))

  def write(msgArray: Array[String]) {
    spacer(defaultTopSpace)
    for ( i <- msgArray.indices) {
      println(msgArray(i))
    }
    spacer(cmdSize - defaultTopSpace - msgArray.length)
  }

  def spacer(size: Int): Unit = for(i <- 1 to size) println("")

  def spacer(): Unit = spacer(1)



  def nextPlayerScreen(player:String): Unit = {
    println("Naechster Spieler ist: " + player)
    spacer(cmdSize - 2)
    println("Mit 0 fortfahren")
    continueScanner()
  }

  def attackScreen(players: List[Player], playerStacks: List[CardStack], id: Int, field: Field, msg: String): Int = {
    printPlayers(players, playerStacks)
    if(msg != "") println(msg) else spacer()
    if(field.size == 0) spacer(3) // else printField(field)
    spacer(cmdSize - 7)
    printHand(playerStacks(id))
    val inputCard = getInputCard(playerStacks(id).getAllCards().length)
    if(inputCard == -1) {
      attackScreen(players, playerStacks, id, field, "Es wurde keine valide Karte ausgewaehlt")
    } else {
      inputCard
    }
  }

  def defenseScreen(): Int = {
    continueScanner()
    1
  }

  def endScreen(): Int = {
    continueScanner()
    1
  }

  def continueScanner(): Unit = {
    while(new Scanner(System.in).nextInt()!=0) println("Mit 0 fortfahren")
  }

  def printPlayers(players: List[Player], playerStacks: List[CardStack]): Unit = {
    print("Spieler: ")
    for(i <- players.indices) {
      print(players(i).toString + " " + playerStacks(i).getAllCards().length + " Karten ")
    }
    println()
  }

  def printHand(cardStack: CardStack): Unit = {
    for(i <- cardStack.getAllCards().indices) print(i + " = " + cardStack.getAllCards()(i).tuiString)
    println()
  }

  def getInputCard(maxLength:Int): Int = {
    println("Waehle mit der Zahl eine Karte aus deiner Hand")

    val res = s.nextInt()

    if(res >= 0 && res <= maxLength) {
      res
    } else {
      -1
    }
  }

  def getGameInfo(): (List[Player], Int) = {
    write(Array(
      "Spielernamen getrennt mit einem Leerzeichen eingeben",
      "2-6 Spieler"
    ))
    val playerStrings = s.next.split(" ")
    playerStrings.length match {
      case 1 => write("Mindestens 2 Spieler"); getGameInfo()
      case l if l > 6 => write("Maximal 6 Spieler"); getGameInfo()
      case l if l > 1 && l < 5 => {
        write(Array(
          "Anzahl der Spielerkarten auswaehlen",
          "36     6-Ass",
          "48     2-Ass"
        ))
        var stackSize = s.nextInt();
        while(stackSize!=36&&stackSize!=48) {
          println("Mit 0 die Kalibrierung starten")
          stackSize = s.nextInt();
        }
        (playerNamesToPlayer(playerStrings), stackSize)
      }
      case l if l == 5 || l == 6 => (playerNamesToPlayer(playerStrings), 48)
    }
  }

  def playerNamesToPlayer(array: Array[String]): List[Player] = {
    val playerList = new ListBuffer[Player]()
    for(player <- array) playerList += new Player(player)
    playerList.toList
  }

  // done
  def menuScreen(msg:String): Array[String] = {
    Array("Willkommen im Spiel Durak",
      "Folgende Befehle stehen zur Auswahl",
      "0     Spiel starten",
      "1     Konsole kalibrieren",
      "",
      msg
    )
  }

  // done
  def calibrate(): Unit = {
    //todo min height
    write(Array(
      "Im naechsten Fenster die hoechste sichtbare Zahl angeben",
      "Mit 0 die Kalibrierung starten, danach startet sich das Spiel neu",
      "mit der neuen Kalibrierung"
    ))
    while(s.nextInt()!=0)
      println("Mit 0 die Kalibrierung starten")
    var testSize = 30
    while(testSize != 0) {
      println(testSize)
      testSize -= 1
    }
    cmdSize = s.nextInt()
  }
}
