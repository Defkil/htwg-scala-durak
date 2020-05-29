package de.htwg.se.durak.controller

import java.util.Scanner

import de.htwg.se.durak.model.{Card, CardStack, Field, Player}
import de.htwg.se.durak.controller.{GameLogic, GameTable}
import de.htwg.se.durak.utilities.Tui

import scala.collection.mutable.ListBuffer

case class CmdRuntime() {
  val s = new Scanner(System.in).useDelimiter("\n")
  val t = Tui(s)
  val logic = new GameLogic()
  var playerSize: Int = 0

  def start(): Unit = {
    openMenu() match {
      case 0 => startGame()
      case 1 => t.calibrate(); start()
    }
  }

  def startGame(): Unit = {
    val (players, stackSize) = getGameInfo()
    val gameTable = new GameTable()
    val gameCardStack = new CardStack(stackSize)
    val playerStacks = gameTable.createPlayerCardStack(players)

    gameCardStack.generateStack()

    gameTable.handOutCards(playerStacks, gameCardStack)

    // example data vor testing tui
    val field = new Field(ListBuffer[Card](Card(6, 1), Card(7, 1)))


    var gameStatus: Boolean = true
    var attacker: Int = 0
    var defender: Int = 1
    //val field = new Field()
    val turn = 0
    while (gameStatus) {
      gameLoop(players, playerStacks, attacker, defender, field, turn, "")
    }

    t.endScreen()
    start()
  }

  /** game loop
   *
   * @param players player list
   * @param playerStacks player stacks
   * @param attacker attacker id
   * @param defender defender id
   * @param field game field
   * @param turn 0=attacker turn 1=defender turn
   * @return
   */
  def gameLoop(players: List[Player], playerStacks: List[CardStack],
               attacker: Int, defender: Int, field: Field, turn: Int, msg: String): Boolean = {
    val turnPlayer = if(turn==0) attacker else defender

    t.nextPlayerScreen(players(turnPlayer).toString)

    if(turn == 0) {
      val attackerCard = t.attackScreen(players, playerStacks, attacker, field, "")
      println(logic.canAttack(playerStacks(turnPlayer).getAllCards()(attackerCard), field))
      t.continueScanner()
    } else {

    }
    true
  }

  def openMenu(): Int = {
    t.clear()
    t.write(Array(
      "Willkommen im Spiel Durak",
      "Folgende Befehle stehen zur Auswahl",
      "0     Spiel starten",
      "1     Konsole kalibrieren"
    ))
    s.nextInt()
  }

  def playerNamesToPlayer(array: Array[String]): List[Player] = {
    val playerList = new ListBuffer[Player]()
    for(player <- array) playerList += new Player(player)
    playerList.toList
  }

  def getGameInfo(): (List[Player], Int) = {
    t.write(Array(
      "Spielernamen getrennt mit einem Leerzeichen eingeben",
      "2-6 Spieler"
    ))
    val playerStrings = s.next.split(" ")
    playerStrings.length match {
      case 1 => t.write("Mindestens 2 Spieler"); getGameInfo()
      case l if l > 6 => t.write("Maximal 6 Spieler"); getGameInfo()
      case l if l > 1 && l < 5 => {
        t.write(Array(
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


}
