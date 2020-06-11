package de.htwg.se.durak.model


case class RoundData(
      siteID: Int,
      validateInput: String => Boolean,
      param: Option[List[String]]
)
