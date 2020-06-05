package de.htwg.se.durak.controller

import java.util.Scanner

import de.htwg.se.durak.model.{CardStack, Field, Player, RoundData, TurnData}
import de.htwg.se.durak.utilities.TuiOld
import de.htwg.se.durak.view.Tui

case class GameRuntime() {
  val logic = new GameLogic()
  var playerSize: Int = 0
  var test = 1
  val roundManager: Router = new Router

  def start(): RoundData = {
    RoundData(siteID = 0, nextInput = 0, inputMaxInt = 1)
  }


  //todo overloading
  def getNextRound(param: Int, lastRound: RoundData): RoundData = {
    lastRound.siteID match {
      case 0 => {

      }
    }



    RoundData(siteID = 0, nextInput = 0, inputMaxInt = 1)
  }




  def run(cb: () => Unit): Unit = {
    cb()
    /*openMenu() match {
      case 0 => startGame()
      case 1 => t.calibrate(); start()
      case default => startGame()
    }*/
  }

  def loop(): Unit = {

  }











  /*def startGame(): Unit = {
    val (players, stackSize) = t.getGameInfo()
    val gameTable = new GameTable()
    val gameCardStack = new CardStack(stackSize)
    val playerStacks = gameTable.createPlayerCardStack(players)

    gameCardStack.generateStack()

    gameTable.handOutCards(playerStacks, gameCardStack)

    // example data vor testing tui
    val field = new Field()


    var gameStatus: Boolean = true
    var attacker: Int = 0
    var defender: Int = 1
    //val field = new Field()
    val turn = 0
    while (gameStatus) {
      gameLoop(players, playerStacks, attacker, defender, field, turn, "")
    }

    t.endScreen()
    //start()
  }*/

  /* * game loop
   *
   * @param players player list
   * @param playerStacks player stacks
   * @param attacker attacker id
   * @param defender defender id
   * @param field game field
   * @param turn 0=attacker turn 1=defender turn
   * @return
   */
  /*def gameLoop(players: List[Player], playerStacks: List[CardStack],
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
  }*/

  /*
  def openMenu(): Int = {
    t.clear()
    t.write(Array(
      "Willkommen im Spiel Durak",
      "Folgende Befehle stehen zur Auswahl",
      "0     Spiel starten",
      "1     Konsole kalibrieren"
    ))

    /*var (nextCmd, error) = (null, "")
    while(nextCmd == null) {
      (nextCmd, error) = cmdParserInt((param) => {
        if(param == 0 || param == 1) true
        false
      }, t.menuScreen)
    }*/

    s.nextInt()
  }*/

  def cmdParserInt(con: (Int) => Boolean, view: (String) => Array[String]): (Int, String) = {
    val s = new Scanner(System.in).useDelimiter("\n")
    val temp = s.nextInt()

    (1, "")
  }




}
