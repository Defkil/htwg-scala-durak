package de.htwg.se.durak.model.fileIoComponent.fileIoJsonImpl

import de.htwg.se.durak.model.fileIoComponent.FileIOInterface
import de.htwg.se.durak.model.roundComponent.GameDataInterface
import play.api.libs.json.{JsValue, Json}

class FileIO extends FileIOInterface{
  // override def load: List[GameDataInterface]
  override def save(grid: List[GameDataInterface]): Unit = {
    import java.io._
    val pw = new PrintWriter(new File("gameData.json"))
    pw.write(Json.prettyPrint(gridToJson(grid)))
    pw.close()
  }

  def gridToJson(gameDataList: List[GameDataInterface]): JsValue = {
    Json.toJson(
      gameDataList.map { gameData => {
        val roundData: JsValue = Json.obj(
          "siteID" -> gameData.roundData.siteID,
          "validateInputList" -> gameData.roundData.validateInputList.mkString(","),
          "param" -> gameData.roundData.param.getOrElse(List("")).mkString(",")
        )

        val turnData: JsValue = if(gameData.turnData.isEmpty) Json.obj() else Json.obj(

        )

        Json.obj("roundData" -> roundData, "turnData" -> turnData)
      }}
    )
  }
}
