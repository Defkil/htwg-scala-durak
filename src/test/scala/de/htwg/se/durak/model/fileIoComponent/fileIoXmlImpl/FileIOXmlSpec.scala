package de.htwg.se.durak.model.fileIoComponent.fileIoJsonImpl

import de.htwg.se.durak.model.fileIoComponent.fileIoXmlImpl
import de.htwg.se.durak.model.gameElementsComponent.gameElementsBaseImpl.{Card, GameElements}
import de.htwg.se.durak.model.playerComponent.Player
import de.htwg.se.durak.model.roundComponent.{GameDataInterface, RoundDataInterface}
import de.htwg.se.durak.model.roundComponent.roundBaseImpl.{GameData, Round, RoundData, TurnData}
import org.scalatest._

class FileIOXmlSpec extends WordSpec with Matchers{
  "FileIO XML" should  {
    "save file and load without TurnData" in {
      val gameDataList: List[GameDataInterface] = List(
        GameData(
          new RoundData(0, List("1")),
          None
        )
      )

      val fileIOXml = new fileIoXmlImpl.FileIO(GameElements(), new Round())
      fileIOXml.save(gameDataList)

      val res:List[GameDataInterface] = fileIOXml.load
      res.toString() should equal("List(GameData(RoundData(0,List(1),None,Some(List(None))),None))")
    }

    "save file and load with TurnData" in {
      val gameElements = new GameElements()
      val gameDataList: List[GameDataInterface] = List(
        GameData(
          new RoundData(0, List("1")),
          Option(TurnData(
            List(Player("PlayerA"), Player("PlayerB")),
            List(gameElements.createCardDeck(List(new Card(5,1), new Card(6,1)))),
            0,
            1,
            gameElements.createField(),
            gameElements.createCardDeck(List(new Card(4,1))),
            gameElements.createCardDeck(),
            1,
            0
          ))
        )
      )

      val fileIOXml = new fileIoXmlImpl.FileIO(GameElements(), new Round())
      fileIOXml.save(gameDataList)

      val res:List[GameDataInterface] = fileIOXml.load
      println(res.toString())
      res.toString() should equal("List(GameData(RoundData(0,List(1),None,Some(List(None))),Some(TurnData(List(PlayerA, PlayerB),List(CardDeck(List(Rang: 5, Typ: Pik, Rang: 6, Typ: Pik))),0,1,Field(CardDeck(List())),CardDeck(List(Rang: 4, Typ: Pik)),CardDeck(List()),1,0))))")
    }
  }
}
