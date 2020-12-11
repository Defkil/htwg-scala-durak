package de.htwg.se.durak.model.roundComponent.roundBaseImpl

import de.htwg.se.durak.model.roundComponent.{GameDataInterface, RoundDataInterface, TurnDataInterface}

/**
 * Data for State Pattern
 *
 * RoundData for information about the actual Round
 * TurnData for information about
 *
 * @param roundData Current RoundData
 * @param turnData Current TurnData
 */
case class GameData(roundData: RoundDataInterface, turnData: Option[TurnDataInterface]) extends GameDataInterface { }
