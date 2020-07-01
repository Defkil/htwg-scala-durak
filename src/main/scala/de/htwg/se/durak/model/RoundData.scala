package de.htwg.se.durak.model


case class RoundData(
      siteID: Int,
      validateInput: Option[String => Boolean],
      validateInputList: Option[List[String]],
      param: Option[List[String]]
)
