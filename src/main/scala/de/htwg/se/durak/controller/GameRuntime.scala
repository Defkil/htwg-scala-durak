package de.htwg.se.durak.controller

import de.htwg.se.durak.model.{GameData, RoundData, TurnData}
import de.htwg.se.durak.utilities.{Observable, UndoManager}

case class GameRuntime() extends Observable {
  private val undoManager = new UndoManager
  val roundFactory = new RoundFactory

  var screenSize: Int = 10
  var roundStack: List[GameData] = List(GameData(roundFactory.getInstance(0, None), None))
  def roundData: RoundData = roundStack.last.roundData
  def turnData: Option[TurnData] = roundStack.last.turnData

  def runRound(param: String): Unit = {
    println(roundStack)
    undoManager.doStep(RoundCommand(param, this))
  }

  def undo: Unit = {
    undoManager.undoStep
    notifyObservers
  }

  def inputError(): Unit = {
    //todo add error msg to roundData
    println("INPUT ERROR")
    notifyObservers
  }
}






/*

    roundData = roundData.siteID match {
      case 0 => param match {
        case "0" => roundTemplate.get(3, None)
        case "1" => roundTemplate.get(1, None)
        case "3" => roundTemplate.get(-1, None)
      }
      case 1 => roundTemplate.get(2, None)
      case 2 =>
        screenSize = param.toInt
        roundTemplate.get(0, None)
      case 3 => // player name select
        //todo add player
        //todo start game
        println(param)
        //turnData = logic.initiateGame(regex.findAllIn(roundData.param).toList)
        roundTemplate.get(10, Some(List("Static Name")))
      case 10 => // next turn
        //todo implementation
        if(exampleOnly) {
          exampleOnly = false
          roundTemplate.get(11, None)
        } else {
          exampleOnly = true
          roundTemplate.get(12, None)
        }
      case 11 => // attacker
        //todo implementation
        roundTemplate.get(10, Some(List("Static Name")))
      case 12 => // defender
        //todo implementation
        roundTemplate.get(13, None)
      case 13 => // finished
        //todo implementation
        roundTemplate.get(0, None)
    }
    notifyObservers
 */


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