package de.htwg.se.durak.model

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
case class Card(rank: Int, symbol: Int) {
  /** custom toString
   * example "Rang: X, Typ: Y"
   *
   * @return String
   */
  override def toString: String = {
    def getRank(): String = rank match {
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
    }
    def getCardType(): String = symbol match {
      case 1 => "Pik"
      case 2 => "Karo"
      case 3 => "Kreuz"
      case 4 => "Herz"
    }
    "Rang: " + getRank() +", Typ: " + getCardType()
  }
}
