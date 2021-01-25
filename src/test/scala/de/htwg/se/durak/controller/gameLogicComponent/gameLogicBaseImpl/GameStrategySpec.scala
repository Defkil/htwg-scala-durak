package de.htwg.se.durak.controller.gameLogicComponent.gameLogicBaseImpl

import de.htwg.se.durak.model.gameElementsComponent.gameElementsBaseImpl.{Card, GameElements}
import de.htwg.se.durak.model.playerComponent.Player
import de.htwg.se.durak.model.roundComponent.{GameDataInterface, TurnDataInterface}
import de.htwg.se.durak.model.roundComponent.roundBaseImpl.{GameData, Round, RoundData, TurnData}
import org.scalatest._

class GameStrategySpec extends WordSpec with Matchers {
  "GameStrategy" should {
    "nextTurn 0-3" in {
      val gameElements = new GameElements
      val gameStrategy = new GameStrategy(gameElements, new Round)
      val turnData: TurnDataInterface = TurnData (
        List(Player("PlayerA"), Player("PlayerB")),
        List(gameElements.createCardDeck(List(new Card(5,1), new Card(6,1)))),
        0,
        1,
        gameElements.createField(gameElements.createCardDeck(List(new Card(4,1)))),
        gameElements.createCardDeck(List(new Card(4,2))),
        gameElements.createCardDeck(),
        1,
        0
      )
      var gameData: GameDataInterface = new GameData(new RoundData(0,List("")), Option(turnData))
      gameData = gameStrategy.nextTurn(gameData, "")
      gameData.roundData.siteID should be(11)
    }

    "nextTurn 1-1" in {
      val gameElements = new GameElements
      val gameStrategy = new GameStrategy(gameElements, new Round)
      val turnData: TurnDataInterface = TurnData (
        List(Player("PlayerA"), Player("PlayerB")),
        List(gameElements.createCardDeck(List(new Card(5,1), new Card(6,1)))),
        0,
        1,
        gameElements.createField(gameElements.createCardDeck(List(new Card(4,1)))),
        gameElements.createCardDeck(List(new Card(4,2))),
        gameElements.createCardDeck(),
        1,
        1
      )
      var gameData: GameDataInterface = new GameData(new RoundData(0,List("")), Option(turnData))
      gameData = gameStrategy.nextTurn(gameData, "")
      gameData.roundData.siteID should be(11)
    }

    "nextTurn preexit" in {
      val gameElements = new GameElements
      val gameStrategy = new GameStrategy(gameElements, new Round)
      val turnData: TurnDataInterface = TurnData (
        List(Player("PlayerA"), Player("PlayerB")),
        List(
          gameElements.createCardDeck(List(new Card(5,1), new Card(6,1))),
          gameElements.createCardDeck(List(new Card(5,1), new Card(6,1)))
        ),
        0,
        1,
        gameElements.createField(),
        gameElements.createCardDeck(),
        gameElements.createCardDeck(),
        1,
        1
      )
      var gameData: GameDataInterface = new GameData(new RoundData(0,List("")), Option(turnData))
      gameData = gameStrategy.nextTurn(gameData, "")
      gameData.roundData.siteID should be(11)
    }

    "nextTurn exit" in {
      val gameElements = new GameElements
      val gameStrategy = new GameStrategy(gameElements, new Round)
      val turnData: TurnDataInterface = TurnData (
        List(Player("PlayerA")),
        List(gameElements.createCardDeck(List(new Card(5,1), new Card(6,1)))),
        0,
        1,
        gameElements.createField(),
        gameElements.createCardDeck(),
        gameElements.createCardDeck(),
        1,
        1
      )
      var gameData: GameDataInterface = new GameData(new RoundData(0,List("")), Option(turnData))
      gameData = gameStrategy.nextTurn(gameData, "")
      gameData.roundData.siteID should be(-1)
    }

    "test unapply" in {
      val gameStrategy = new GameStrategy(new GameElements, new Round)
      GameStrategy.unapply(gameStrategy)
    }
  }
}
