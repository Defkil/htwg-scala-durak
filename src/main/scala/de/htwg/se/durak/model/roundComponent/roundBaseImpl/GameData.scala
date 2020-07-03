package de.htwg.se.durak.model.roundComponent.roundBaseImpl

import de.htwg.se.durak.model.roundComponent.{GameDataInterface, RoundDataInterface, TurnDataInterface}

case class GameData(roundData: RoundDataInterface, turnData: Option[TurnDataInterface]) extends GameDataInterface {

}
