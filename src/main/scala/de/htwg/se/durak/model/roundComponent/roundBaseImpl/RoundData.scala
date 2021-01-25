package de.htwg.se.durak.model.roundComponent.roundBaseImpl

import de.htwg.se.durak.model.roundComponent.RoundDataInterface

/**
 * RoundData with actual site, possible inputs and custom parameter
 *
 * @param siteID Actual site
 * @param validateInputList List of strings with possible options
 * @param validateInput function which returns a boolean dependent on the parameter
 * @param param ptional list of string parameter
 */
case class RoundData (
      siteID: Int,
      validateInputList: List[String],
      validateInput: Option[String => Boolean],
      param: Option[List[String]]
) extends RoundDataInterface {
  /**
   * RoundData with only InputList
   *
   * @param siteID  Actual site
   * @param validateInputList List of strings with possible options
   */
  def this(siteID: Int, validateInputList: List[String]) = {
    this(siteID, validateInputList, None, None)
  }
}
