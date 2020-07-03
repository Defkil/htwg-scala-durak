package de.htwg.se.durak.model


case class RoundData(
      siteID: Int,
      validateInputList: List[String],
      validateInput: Option[String => Boolean],
      param: Option[List[String]]
) {
  def this(siteID: Int, validateInputList: List[String]) = {
    this(siteID, validateInputList, None, None)
  }
}
