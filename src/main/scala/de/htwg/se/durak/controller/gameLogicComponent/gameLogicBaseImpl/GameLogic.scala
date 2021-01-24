package de.htwg.se.durak.controller.gameLogicComponent.gameLogicBaseImpl

import com.google.inject.Inject
import de.htwg.se.durak.controller.gameLogicComponent.{GameLogicInterface, GameStrategyInterface, GameStrategyMenuInterface}
import de.htwg.se.durak.model.gameElementsComponent.GameElementsInterface
import de.htwg.se.durak.model.roundComponent.RoundInterface

class GameLogic @Inject() (val gameElements: GameElementsInterface, val round: RoundInterface) extends GameLogicInterface {
  val menu: GameStrategyMenuInterface = new GameStrategyMenu(round)
  val strategy = new GameStrategy(gameElements, round)
  var get: GameStrategyInterface = strategy
}
