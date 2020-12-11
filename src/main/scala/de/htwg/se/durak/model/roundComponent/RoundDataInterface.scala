package de.htwg.se.durak.model.roundComponent

/**
 * RoundData with actual site, possible inputs and custom parameter
 */
trait RoundDataInterface {
  /**
   * ID of the selected site/game state
   *
   * @return Actual site
   */
  val siteID: Int

  /**
   * Prioritized input function
   *
   * @return function which returns a boolean dependent on the parameter
   */
  val validateInput: Option[String => Boolean]

  /**
   * Possible inputs if [[RoundDataInterface.validateInput]] is None
   *
   * @return List of strings with possible options
   */
  val validateInputList: List[String]

  /**
   * Optional custom parameter
   *
   * @return Optional list of string parameter
   */
  val param: Option[List[String]]
}
