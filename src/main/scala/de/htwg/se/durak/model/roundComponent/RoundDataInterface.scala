package de.htwg.se.durak.model.roundComponent

trait RoundDataInterface {
  val siteID: Int
  val validateInputList: List[String]
  val validateInput: Option[String => Boolean]
  val param: Option[List[String]]
}
