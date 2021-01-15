package de.htwg.se.durak.model.fileIoComponent.fileIoXmlImpl

import com.google.inject.Inject
import de.htwg.se.durak.model.fileIoComponent.FileIOInterface
import de.htwg.se.durak.model.gameElementsComponent.{CardDeckInterface, CardInterface, GameElementsInterface}
import de.htwg.se.durak.model.playerComponent.Player
import de.htwg.se.durak.model.roundComponent.{GameDataInterface, RoundDataInterface, RoundInterface, TurnDataInterface}

import scala.collection.mutable.ListBuffer
import scala.xml.{Elem, NodeSeq}

class FileIO @Inject() (val gameElements: GameElementsInterface, val round: RoundInterface) extends FileIOInterface  {
  override def save(gameDataList: List[GameDataInterface]): Unit = {
    scala.xml.XML.save("gameData.xml",
      <gamedata>
        {
        for {data <- gameDataList} yield dataToXml(data)
        }
      </gamedata>)
  }

  def dataToXml(data: GameDataInterface): Elem = {
    val roundData = data.roundData
    val turnData = data.turnData
    <rounddata>
      <siteid>{roundData.siteID}</siteid>
      <validateinputlist>{roundData.validateInputList}</validateinputlist>
      <param>{roundData.param}</param>
      {if(turnData.isEmpty) <turnddata></turnddata> else turnDataToXml(turnData.get)}
    </rounddata>
  }

  def turnDataToXml(turnData: TurnDataInterface): Elem = {
    <turndata>
      {playerListToXml(turnData.players)}
      <playerdecks>{playerDecksToXml(turnData.playerDecks)}</playerdecks>
      <currentplayer>{turnData.currentPlayer}</currentplayer>
      <defendplayer>{turnData.defendPlayer}</defendplayer>
      <field>{cardDeckToXml(turnData.field.deck)}</field>
      <maindeck>{cardDeckToXml(turnData.mainDeck.deck)}</maindeck>
      <outdeck>{cardDeckToXml(turnData.outDeck.deck)}</outdeck>
      <trump>{turnData.trump}</trump>
      <turntype>{turnData.turnType}</turntype>
    </turndata>
  }

  def playerListToXml(playerList: List[Player]): Elem = {
    <players>{for { player <- playerList } yield <player>{player}</player>}</players>
  }

  def cardDeckToXml(cardDeck: List[CardInterface]): List[Elem] = {
    for {card <- cardDeck } yield <card><rank>{card.rank}</rank><symbol>{card.symbol}</symbol></card>
  }

  def playerDecksToXml(playerDecks: List[CardDeckInterface]): Elem = {
    <player>{for {playerDeck <- playerDecks } yield cardDeckToXml(playerDeck.deck)}</player>
  }

  def saveString(data: String): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("gameSata.xml"))
    pw.write(data)
    pw.close()
  }

  override def load: List[GameDataInterface] = {
    val source = scala.xml.XML.loadFile("gameData.xml")
    var res: List[GameDataInterface] = List()
    (source \\ "gamedata" \\ "rounddata").foreach { data => {
      val roundData = loadRoundData((data \\ "rounddata"))
      val turnData = loadTurnData((data \\ "turndata"))
      res = res :+ round.createGameData(roundData, turnData)
    }}
    res
  }

  def loadRoundData(elm: NodeSeq): RoundDataInterface = {
    round.createRoundData(
      (elm \\ "siteid").text.toInt,
      (elm \\ "validateinputlist").text.split(" ").toList,
      None,
      Some((elm \\ "param").text.split(" ").toList)
    )
  }

  def loadTurnData(elm: NodeSeq): Option[TurnDataInterface] = {
    if(elm.isEmpty) None else {
      val players: List[Player] = loadPlayerList((elm \\ "players"))
      val playerDecks: List[CardDeckInterface] = loadPlayerDecks((elm \\ "playerdecks"))
      val currentPlayer: Int = (elm \\ "currentplayer").text.toInt
      val defendPlayer = (elm \\ "defendplayer").text.toInt
      val field = gameElements.createField(loadCardDeck((elm \\ "field")))
      val mainDeck: CardDeckInterface = loadCardDeck((elm \\ "maindeck"))
      val outDeck = loadCardDeck((elm \\ "outdeck"))
      val trump = (elm \\ "trump").text.toInt
      val turnType = (elm \\ "turntype").text.toInt
      Some(round.createTurnData(
        players, playerDecks, currentPlayer, defendPlayer, field, mainDeck, outDeck, trump, turnType
      ))
    }
  }

  def loadCardDeck(elm: NodeSeq): CardDeckInterface = {
    var res: CardDeckInterface = gameElements.createCardDeck()
    (elm \\ "card").foreach{ card => {
      res = res.addCard(gameElements.createCard((card \\ "rank").text.toInt, (card \\ "symbol").text.toInt))
    }}
    res
  }

  def loadPlayerDecks(elm: NodeSeq): List[CardDeckInterface] = {
    var res: ListBuffer[CardDeckInterface] = new ListBuffer
    (elm \\ "player").foreach { player => {
      res = res += loadCardDeck(player)
    }}
    res.toList
  }

  def loadPlayerList(elm: NodeSeq): List[Player] = {
    var res: ListBuffer[Player] = ListBuffer()
    (elm \\ "player").foreach { player => {
      res += Player(player.text)
    }}
    res.toList
  }
}
