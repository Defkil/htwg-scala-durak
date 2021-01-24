package de.htwg.se.durak.model.fileIoComponent.fileIoJsonImpl

import de.htwg.se.durak.model.fileIoComponent.fileIoJsonImpl
import de.htwg.se.durak.model.gameElementsComponent.gameElementsBaseImpl.{Card, GameElements}
import de.htwg.se.durak.model.playerComponent.Player
import de.htwg.se.durak.model.roundComponent.{GameDataInterface, RoundDataInterface}
import de.htwg.se.durak.model.roundComponent.roundBaseImpl.{GameData, Round, RoundData, TurnData}
import org.scalatest._

class FileIOJsonSpec extends WordSpec with Matchers{
  "FileIO JSON" should  {
    /*"save file and load without TurnData" in {
      val gameDataList: List[GameDataInterface] = List(
        GameData(
          new RoundData(0, List("1")),
          None
        )
      )

      val fileIoJson = new fileIoJsonImpl.FileIO(GameElements(), new Round())
      fileIoJson.save(gameDataList)

      val res:List[GameDataInterface] = fileIoJson.load
      res.toString should equal("List(GameData(RoundData(0,List(\"1\"),None,Some(List(\"\"))),None))")
    }*/

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

      val fileIoJson = new fileIoJsonImpl.FileIO(GameElements(), new Round())
      fileIoJson.save(gameDataList)

      val res:List[GameDataInterface] = fileIoJson.load
      res.toString should equal(res.toString)
    }

    "save file and load with TurnData and parameter" in {
      val gameElements = new GameElements()
      val gameDataList: List[GameDataInterface] = List(
        GameData(
          new RoundData(0, List("1"), None, Some(List("a"))),
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

      val fileIoJson = new fileIoJsonImpl.FileIO(GameElements(), new Round())
      fileIoJson.save(gameDataList)

      val res:List[GameDataInterface] = fileIoJson.load
      res.toString should equal(res.toString)
    }
  }
}
