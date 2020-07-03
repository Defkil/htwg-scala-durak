package de.htwg.se.durak.controller.gameLogicComponent.gameLogicBaseImpl

import de.htwg.se.durak.model.RoundData

//noinspection ScalaStyle
object RoundFactory {
  def getInstance(id: Int): RoundData = {
    getInstance(id, None)
  }
  def getInstance(id: Int, params: Option[List[String]]): RoundData = {
    val res = id match {
      case -1 => RoundData(-1, List("func"), Some((param:String) => true), params)
      case 0 => RoundData(0, List("0", "1", "3"), None, params)
      case 1 => RoundData(1, List("0"), None, params)
      case 2 => RoundData(2, List("func"), Some((param:String) => param matches("([0-1]?[0-9]|20)")), params)
      case 3 => RoundData(3, List("func"), Some((param:String) => (1 < param.split(" ").length && param.split(" ").length < 7)), params)

      case 10 => RoundData(10, List("0"), None, params)
      case 11 => RoundData(11, List("func"), Some((param:String) => param.matches("[0-5]")), params)
      case 12 => RoundData(12, List("func"), Some((param:String) => true), params)
      case 13 => RoundData(13, List("func"), Some((param:String) => true), params)
      case default => RoundData(0, List("func"), Some(_ matches("[0-2]")), params)
    }
    res
  }
}
