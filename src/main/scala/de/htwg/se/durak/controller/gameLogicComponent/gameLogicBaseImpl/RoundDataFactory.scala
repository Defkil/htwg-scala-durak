package de.htwg.se.durak.controller.gameLogicComponent.gameLogicBaseImpl

import de.htwg.se.durak.model.roundComponent.{RoundDataInterface, RoundInterface}

/**
 * creating a ready instance of RoundDataInterface and checking whether param is valid
 * @param round decribes the situation in game
 */
class RoundDataFactory(round: RoundInterface) {
  def getInstance(id: Int): RoundDataInterface = {
    getInstance(id, None)
  }

  /**
   *
   * @param id is the current situation
   * @param params are optional inputs from user
   * @return
   */
  //noinspection ScalaStyle
  def getInstance(id: Int, params: Option[List[String]]): RoundDataInterface = {
    val res = id match {
      case -1 => round.createRoundData(-1, List("func"), Some((param:String) => true), params)
      case 0 => round.createRoundData(0, List("0", "1", "3"), None, params)
      case 1 => round.createRoundData(1, List("0"), None, params)
      case 2 => round.createRoundData(2, List("func"), Some((param:String) => param matches("([0-1]?[0-9]|20)")), params)
      case 3 => round.createRoundData(3, List("func"), Some((param:String) => (1 < param.split(" ").length && param.split(" ").length < 7)), params)
      case 4 => round.createRoundData(4, List("func"), Some((param:String) => true), params)
      case 10 => round.createRoundData(10, List("0"), None, params)
      case 11 => round.createRoundData(11, List("func"), Some((param:String) => param.matches("[0-5]")), params)
      case 12 => round.createRoundData(12, List("func"), Some((param:String) => true), params) // defend turn
      case 13 => round.createRoundData(13, List("func"), Some((param:String) => true), params)
      // case default => round.createRoundData(0, List("func"), Some(_ matches("[0-2]")), params)
    }
    res
  }
}
