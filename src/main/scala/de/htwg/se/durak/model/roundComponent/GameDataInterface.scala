package de.htwg.se.durak.model.roundComponent

/**
 * Data for State Pattern
 *
 * RoundData for information about the actual Round
 * TurnData for information about
 */
trait GameDataInterface {
  /**
   * RoundData with actual site, possible inputs and custom parameter
   *
   * @return Current RoundData
   */
  val roundData: RoundDataInterface

  /**
   * TurnData with all game information
   *
   * @return Current TurnData
   */
  val turnData: Option[TurnDataInterface]
}
