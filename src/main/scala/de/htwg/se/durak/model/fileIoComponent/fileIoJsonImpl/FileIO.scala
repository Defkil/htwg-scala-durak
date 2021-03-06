package de.htwg.se.durak.model.fileIoComponent.fileIoJsonImpl

import com.google.inject.Inject
import de.htwg.se.durak.model.fileIoComponent.FileIOInterface
import de.htwg.se.durak.model.gameElementsComponent.{CardDeckInterface, CardInterface, FieldInterface, GameElementsInterface}
import de.htwg.se.durak.model.playerComponent.Player
import de.htwg.se.durak.model.roundComponent.{GameDataInterface, RoundDataInterface, RoundInterface, TurnDataInterface}
import play.api.libs.json.{JsValue, Json}

import scala.collection.mutable.ListBuffer
import scala.io.Source

class FileIO @Inject() (val gameElements: GameElementsInterface, val round: RoundInterface) extends FileIOInterface {

  /**
   * Save GameData to a file
   *
   * @param gameDataList GameData that should be saved
   */
  override def save(gameDataList: List[GameDataInterface]): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("gameData.json"))
    pw.write(Json.prettyPrint(gameDataToJson(gameDataList)))
    pw.close()
  }

  /**
   * Load saved file
   *
   * @return List of GameData
   */
  override def load: List[GameDataInterface] = {
    val source: String = Source.fromFile("gameData.json").getLines.mkString
    val json: JsValue = Json.parse(source)
    val list = json.as[List[JsValue]]
    var res: List[GameDataInterface] = List()
    for(i <- list.indices) {
      val elm = list(i)
      val roundData = loadRoundData((elm \ "roundData").get)
      val turnData = loadTurnData((elm \ "turnData").get)
      res = res :+ round.createGameData(roundData, turnData)
    }
    res
  }

  /**
   * Convert RoundData to json
   *
   * @param gameDataList GameData which should be converted to json
   * @return json element from the parameter
   */
  def gameDataToJson(gameDataList: List[GameDataInterface]): JsValue = {
    Json.toJson(
      gameDataList.map { gameData => {
        val roundData: JsValue = roundDataToJson(gameData.roundData)
        val turnData: JsValue = if(gameData.turnData.isEmpty) Json.obj() else {
          val turnData = gameData.turnData.get
          Json.obj(
            "players" -> playerListToJson(turnData.players)
            , "playerDecks" -> playerDecksToJson(turnData.playerDecks)
            , "currentPlayer" -> turnData.currentPlayer
            , "defendPlayer" -> turnData.defendPlayer
            , "field" -> cardDeckToJson(turnData.field.deck)
            , "mainDeck" -> cardDeckToJson(turnData.mainDeck.deck)
            , "outDeck" -> cardDeckToJson(turnData.outDeck.deck)
            , "trump" -> turnData.trump
            , "turnType" -> turnData.turnType
          )
        }
        Json.obj("roundData" -> roundData, "turnData" -> turnData)
      }}
    )
  }

  /**
   * Convert RoundData to json
   *
   * @param roundData RoundData which should be converted to json
   * @return json element from the parameter
   */
  def roundDataToJson(roundData: RoundDataInterface): JsValue = {
    Json.obj(
      "siteID" -> roundData.siteID,
      "validateInputList" -> roundData.validateInputList.mkString(","),
      "param" -> roundData.param.getOrElse(List("")).mkString(",")
    )
  }

  /**
   * Convert PlayerDecks to json
   *
   * @param playerDecks PlayerDecks which should be converted to json
   * @return json element from the parameter
   */
  def playerDecksToJson(playerDecks: List[CardDeckInterface]): JsValue = {
    Json.toJson(
      playerDecks.map { elm => {
        cardDeckToJson(elm.deck)
      }}
    )
  }

  /**
   * Convert CardDeck to json
   *
   * @param cardDeck CardDeck which should be converted to json
   * @return json element from the parameter
   */
  def cardDeckToJson(cardDeck: List[CardInterface]): JsValue = {
    Json.toJson(
      cardDeck.map { card => {
       Json.obj("rank" -> card.rank, "symbol" -> card.symbol)
      }}
    )
  }

  /**
   * Convert PlayerList to json
   *
   * @param playerList PlayerList which should be converted to json
   * @return json element from the parameter
   */
  def playerListToJson(playerList: List[Player]): JsValue = {
    Json.toJson(
      playerList.map { player => {
        Json.obj("name" -> player.toString)
      }}
    )
  }

  /**
   * Load RoundData from json
   *
   * @param elm json data wich should be parsed
   * @return RoundData based on the parameter
   */
  def loadRoundData(elm: JsValue): RoundDataInterface = {
    round.createRoundData(
      (elm \ "siteID").get.toString.toInt
      , (elm \ "validateInputList").get.toString.split(",").toList
      , None
      , if((elm \ "param").get.toString != "") Some((elm \ "param").get.toString.split(",").toList) else None
    )
  }

  /**
   * Load TurnData from json
   *
   * @param elm json data wich should be parsed
   * @return TurnData based on the parameter
   */
  def loadTurnData(elm: JsValue): Option[TurnDataInterface] = {
    if(elm.toString() == "{}") None else {
      val players: List[Player] = loadPlayers((elm \ "players").as[List[JsValue]])
      val playerDecks: List[CardDeckInterface] = loadCardDecks((elm \ "playerDecks").get)
      val currentPlayer: Int = (elm \ "currentPlayer").as[Int]
      val defendPlayer = (elm \ "currentPlayer").as[Int]
      val field = loadField((elm \ "field").get)
      val mainDeck = loadCardDeck((elm \ "mainDeck").get)
      val outDeck = loadCardDeck((elm \ "outDeck").get)
      val trump = (elm \ "trump").as[Int]
      val turnType = (elm \ "turnType").as[Int]
      Some(round.createTurnData(
        players, playerDecks, currentPlayer, defendPlayer, field, mainDeck, outDeck, trump, turnType
      ))
    }
  }

  /**
   * Load CardDecks from json
   *
   * @param elms json data wich should be parsed
   * @return CardDecks based on the parameter
   */
  def loadCardDecks(elms: JsValue): List[CardDeckInterface] = {
    var res: ListBuffer[CardDeckInterface] = new ListBuffer
    val jsCardDecks: List[JsValue] = elms.as[List[JsValue]]
    jsCardDecks.foreach(elm => {
      res = res += loadCardDeck(elm)
    })
    res.toList
  }

  /**
   * Load CardDeck from json
   *
   * @param elms json data wich should be parsed
   * @return CardDeck based on the parameter
   */
  def loadCardDeck(elms: JsValue): CardDeckInterface = {
    var res: CardDeckInterface = gameElements.createCardDeck()
    elms.as[List[JsValue]].foreach(elm => {
      res = res.addCard(gameElements.createCard((elm \ "rank").as[Int], (elm \ "symbol").as[Int]))
    })
    res
  }

  /**
   * Load Field from json
   *
   * @param elms json data wich should be parsed
   * @return Field based on the parameter
   */
  def loadField(elms: JsValue): FieldInterface = gameElements.createField(loadCardDeck(elms))

  /**
   * Load PlayerList from json
   *
   * @param prePlayers json data wich should be parsed
   * @return PlayerList based on the parameter
   */
  def loadPlayers(prePlayers: List[JsValue]): List[Player] = {
    var res: ListBuffer[Player] = ListBuffer()
    prePlayers.foreach(elm => {
      res += Player((elm \ "name").get.toString)
    })
    res.toList
  }
}
