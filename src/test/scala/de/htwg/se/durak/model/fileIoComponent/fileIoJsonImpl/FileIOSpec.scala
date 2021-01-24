package de.htwg.se.durak.model.fileIoComponent.fileIoJsonImpl

import de.htwg.se.durak.model.fileIoComponent.fileIoJsonImpl
import de.htwg.se.durak.model.gameElementsComponent.{CardDeckInterface, FieldInterface}
import de.htwg.se.durak.model.gameElementsComponent.gameElementsBaseImpl.{CardDeck, Field, GameElements}
import de.htwg.se.durak.model.playerComponent.Player
import de.htwg.se.durak.model.roundComponent.{GameDataInterface, RoundDataInterface}
import de.htwg.se.durak.model.roundComponent.roundBaseImpl.{GameData, Round, RoundData, TurnData}
import org.scalatest._

class FileIOSpec extends WordSpec with Matchers{
  "FileIO JSON" should  {
    "save file and load" in {
      val gameElements = new GameElements()
      val gameDataList: List[GameDataInterface] = List(
        new GameData(
          new RoundData(0, List("1")),
          Option(new TurnData(
            List(Player("PlayerA"), Player("PlayerB")),
            List(),
            0,
            1,
            gameElements.createField(),
            gameElements.createCardDeck(),
            gameElements.createCardDeck(),
            1,
            0
          ))
        )
      )

      val fileIoJson = new fileIoJsonImpl.FileIO(new GameElements(), new Round())
      fileIoJson.save(gameDataList)

      val res:List[GameDataInterface] = fileIoJson.load
      println(res.toString())
      res.toString() should equal("List(GameData(RoundData(0,List(\"1\"),None,Some(List(\"\"))),Some(TurnData(List(\"PlayerA\", \"PlayerB\"),List(),0,0,Field(CardDeck(List())),CardDeck(List()),CardDeck(List()),1,0))))")
    }
  }
}
