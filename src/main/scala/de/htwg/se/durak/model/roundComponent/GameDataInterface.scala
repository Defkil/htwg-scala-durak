package de.htwg.se.durak.model.roundComponent

trait GameDataInterface {
  val roundData: RoundDataInterface
  val turnData: Option[TurnDataInterface]
}
