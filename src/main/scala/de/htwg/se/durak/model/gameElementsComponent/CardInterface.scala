package de.htwg.se.durak.model.gameElementsComponent

trait CardInterface {
  val rank: Int
  val symbol: Int
  def rankString: String
  def symbolString: String
  def symbolUnicode: String
  def rankUnicode: String
  def toString: String
}
