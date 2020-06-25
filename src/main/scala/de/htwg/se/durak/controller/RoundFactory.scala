package de.htwg.se.durak.controller

import de.htwg.se.durak.model.RoundData

/*
   *
   * Sites:
   * 0 = menu
   * 1 = calibration info
   * 2 = calibration list 1 - 20
   * 3 = player name select
   * 10 = start game
   * old 10 = next turn
   * old 11 = attacker
   * old 12 = defender
   * old 13 = finished
   * -1 = close game id
   */

//noinspection ScalaStyle
case class RoundFactory() {
  def getInstance(id: Int, params: Option[List[String]]): RoundData = {
    val res = id match {
      case -1 => RoundData(-1, (param:String) => true, params)
      case 0 => RoundData(0, (param:String) => param.matches("[0,1,3]"), params)
      case 1 => RoundData(1, (param:String) => param.toInt == 0, params)
      case 2 => RoundData(2, (param:String) => param matches("([0-1]?[0-9]|20)"), params)
      case 3 => RoundData(3, (param:String) => (1 < param.split(" ").length && param.split(" ").length < 7), params)

      case 10 => RoundData(10, (param:String) => true, params)
      case 11 => RoundData(11, (param:String) => true, params)
      case 12 => RoundData(12, (param:String) => true, params)
      case 13 => RoundData(13, (param:String) => true, params)
      case default => RoundData(0, _ matches("[0-2]"), params)
    }
    res
  }
}
