package de.htwg.se.durak.model.fileIoComponent.fileIoJsonImpl

import de.htwg.se.durak.model.fileIoComponent.fileIoJsonImpl
import de.htwg.se.durak.model.gameElementsComponent.gameElementsBaseImpl.GameElements
import de.htwg.se.durak.model.roundComponent.{GameDataInterface, RoundDataInterface}
import de.htwg.se.durak.model.roundComponent.roundBaseImpl.{GameData, Round, RoundData}
import org.scalatest._

class FileIOSpec extends WordSpec with Matchers{
  "FileIO JSON" should  {
    "save file and load" in {
      val gameDataList: List[GameDataInterface] = List(
        new GameData(
          new RoundData(0, List("1")),
          None
        )
      )

      val fileIoJson = new fileIoJsonImpl.FileIO(new GameElements(), new Round())
      fileIoJson.save(gameDataList)

      val res:List[GameDataInterface] = fileIoJson.load
      res.toString() should equal("List(GameData(RoundData(0,List(\"1\"),None,Some(List(\"\"))),None))")
    }
  }
}
