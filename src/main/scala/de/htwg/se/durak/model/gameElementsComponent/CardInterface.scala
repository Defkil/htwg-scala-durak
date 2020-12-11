package de.htwg.se.durak.model.gameElementsComponent

/**
 * Card with Unicode and String properties
 */
trait CardInterface {
  /**
   * Card rank
   *
   * @return Card rank
   */
  val rank: Int

  /**
   * Card symbol
   *
   * @return Card symbol
   */
  val symbol: Int

  /**
   * Rank as string
   *
   * @return Card rank as simple string
   */
  def rankString: String

  /**
   * Symbol as string
   *
   * @return Card symbol as simple string
   */
  def symbolString: String

  /**
   * Rank as unicode
   *
   * @return Card rank as unicode string
   */
  def rankUnicode: String

  /**
   * Symbol as unicode
   *
   * @return Card symbol as unicode string
   */
  def symbolUnicode: String

  /**
   * Card rank and symbol as string
   * example "Rang: X, Typ: Y"
   *
   * @return Card rank and symbol as string
   */
  def toString: String
}
