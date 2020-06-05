package de.htwg.se.durak.controller

import de.htwg.se.durak.model.RoundData

case class GameRuntime() {
  val logic = new GameLogic()
  var playerSize: Int = 0
  var test = 1
  val roundManager: ObserverData = new ObserverData
  var setSpacer: (Int) => Unit = null

  def start(): RoundData = {
    RoundData(siteID = 0, nextInput = 0, inputMaxInt = 1)
  }

  var exampleOnly = true
  def getNextRound(param: String = "", paramInt: Int = -1, paramArray: Array[String] = Array(""), lastRound: RoundData): RoundData = {
    lastRound.siteID match {
      case 0 => // menu
        //todo add multiplayer
        //todo close game
        if(paramInt == 0) {
          return RoundData(siteID = 3, nextInput = 2, inputMinLineSpacers = 2, inputMaxLineSpacers = 6)
        } else if(paramInt == 1) {
          return RoundData(siteID = 1, nextInput = 0, inputMaxInt = 0)
        }
        RoundData(siteID = 0, nextInput = 0, inputMaxInt = 1)
      case 1 => // calibration info
        RoundData(siteID = 2, nextInput = 0, inputMaxInt = 20)
      case 2 => // calibration list 1 - 20
        setSpacer(paramInt)
        start()
      case 3 => // player name select
        //todo add player
        //todo start game
        RoundData(siteID = 10, nextInput = 0, inputMaxInt = 20, param = Array("Static Name"))
      case 10 => // next turn
        //todo implementation
        if(exampleOnly) {
          exampleOnly = false
          RoundData(siteID = 11, nextInput = 0, inputMaxInt = 0)
        } else {
          exampleOnly = true
          RoundData(siteID = 12, nextInput = 0, inputMaxInt = 0)
        }
      case 11 => // attacker
        //todo implementation
        RoundData(siteID = 10, nextInput = 0, inputMaxInt = 6, param = Array("Static Name"))
      case 12 => // defender
        //todo implementation
        RoundData(siteID = 13, nextInput = 0, inputMaxInt = 6)
      case 13 => // finished
        //todo implementation
        start()
    }
  }
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
}

def cmdParserInt(con: (Int) => Boolean, view: (String) => Array[String]): (Int, String) = {
  val s = new Scanner(System.in).useDelimiter("\n")
  val temp = s.nextInt()
  (1, "")
}*/