package de.htwg.se.durak.model.gameElementsComponent.gameElementsBaseImpl

import de.htwg.se.durak.model.gameElementsComponent.{CardInterface, GameElementsInterface}

/** Card with rank and a type
 *
 * possible values are: rank=(2-14), cardType(1-4)
 *
 * rank starts from 2 - 10 with the same cards 2 - 10
 * after that rank=card 11=Bube, 12=Dame, 13=König, 14=Ass
 *
 * cardTypes are 1=Pik, 2=Karo, 3=Kreuz, 4=Herz
 *
 * @param rank card rank (2-14)
 * @param symbol card symbol (1-4)
 */
case class Card(rank: Int, symbol: Int) extends CardInterface {
  def rankString: String = rank match {
    case 2 => "2"
    case 3 => "3"
    case 4 => "4"
    case 5 => "5"
    case 6 => "6"
    case 7 => "7"
    case 8 => "8"
    case 9 => "9"
    case 10 => "10"
    case 11 => "Bube"
    case 12 => "Dame"
    case 13 => "König"
    case 14 => "Ass"
    case -1 => ""
  }

  def symbolString: String = symbol match {
    case 1 => "Pik"
    case 2 => "Karo"
    case 3 => "Kreuz"
    case 4 => "Herz"
    case -1 => ""
  }

  def symbolUnicode: String = symbol match {
    case 1 => "\u2660"
    case 2 => "\u2666"
    case 3 => "\u2663"
    case 4 => "\u2665"
    case -1 => ""
  }

  def rankUnicode: String = rank match {
    case 2 => "2"
    case 3 => "3"
    case 4 => "4"
    case 5 => "5"
    case 6 => "6"
    case 7 => "7"
    case 8 => "8"
    case 9 => "9"
    case 10 => "10"
    case 11 => "\u265F"
    case 12 => "\u265B"
    case 13 => "\u265A"
    case 14 => "A"
    case -1 => ""
  }
  /** custom toString
   * example "Rang: X, Typ: Y"
   *
   * @return String
   */
  override def toString: String = {

    "Rang: " + symbolString +", Typ: " + rankString
  }
}
