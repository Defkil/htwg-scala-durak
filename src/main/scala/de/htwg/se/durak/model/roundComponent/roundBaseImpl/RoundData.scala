package de.htwg.se.durak.model.roundComponent.roundBaseImpl

import de.htwg.se.durak.model.roundComponent.RoundDataInterface

case class RoundData (
      siteID: Int,
      validateInputList: List[String],
      validateInput: Option[String => Boolean],
      param: Option[List[String]]
) extends RoundDataInterface {
  def this(siteID: Int, validateInputList: List[String]) = {
    this(siteID, validateInputList, None, None)
  }
}
