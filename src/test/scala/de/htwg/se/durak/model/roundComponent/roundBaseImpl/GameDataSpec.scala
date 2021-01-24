package de.htwg.se.durak.model.roundComponent.roundBaseImpl

import de.htwg.se.durak.model.gameElementsComponent.gameElementsBaseImpl.{Card, GameElements}
import de.htwg.se.durak.model.playerComponent.Player
import org.scalatest.{Matchers, WordSpec}

class GameDataSpec extends WordSpec with Matchers {
  "GameData" should {
    "create GameData without TurnData" in {
      val gameData = GameData(new RoundData(0, List("0")), None)
      gameData.roundData.siteID should be(0)
      gameData.roundData.validateInputList.head should be("0")
    }

    "create GameData with TurnData" in {
      val gameElements = new GameElements()
      val gameData = GameData(
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
      gameData.roundData.siteID should be(0)
      gameData.roundData.validateInputList.head should be("1")
    }

    "test unapply" in {
      val gameData = GameData(new RoundData(0, List("0")), None)
      GameData.unapply(gameData)
    }
  }
}
