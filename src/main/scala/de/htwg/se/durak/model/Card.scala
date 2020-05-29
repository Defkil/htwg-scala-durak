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
 * @param cardType card type (1-4)
 */
case class Card(rank: Int, cardType: Int) {

  /** returns a tupel with rank and type from the card
   *
   * @return (rank, type)
   */
  def get(): (Int, Int) = {
    (getRank, getType)
  }

  /** returns card rank
   *
   * @return rank
   */
  def getRank: Int = rank

  /** returns card type
   *
   * @return cardType
   */
  def getType: Int = cardType

  /** return card weight
   *
   * @return
   */
  def getWeight(): Int = {
    cardType match {
      case 1 => rank
      case 2 => rank + 14
      case 3 => rank + 14 * 2
      case 4 => rank + 14 * 3
    }
  }

  /** custom toString
   * example "Rang: X, Typ: Y"
   *
   * @return
   */
  override def toString: String = {
    var s = "Rang: "
    rank match {
      case 2 => s+= "2"
      case 3 => s+= "3"
      case 4 => s+= "4"
      case 5 => s+= "5"
      case 6 => s+= "6"
      case 7 => s += "7"
      case 8 => s += "8"
      case 9 => s += "9"
      case 10 => s += "10"
      case 11 => s += "Bube"
      case 12 => s += "Dame"
      case 13 => s += "König"
      case 14 => s += "Ass"
    }
    s += ", Typ: "
    cardType match {
      case 1 => s += "Pik"
      case 2 => s += "Karo"
      case 3 => s += "Kreuz"
      case 4 => s += "Herz"
    }
    s
  }

  def tuiString: String = {
    var s = ""
    cardType match {
      case 1 => s += "Pik"
      case 2 => s += "Karo"
      case 3 => s += "Kreuz"
      case 4 => s += "Herz"
    }

    s += " "

    rank match {
      case 2 => s+= "2"
      case 3 => s+= "3"
      case 4 => s+= "4"
      case 5 => s+= "5"
      case 6 => s+= "6"
      case 7 => s += "7"
      case 8 => s += "8"
      case 9 => s += "9"
      case 10 => s += "10"
      case 11 => s += "Bube"
      case 12 => s += "Dame"
      case 13 => s += "König"
      case 14 => s += "Ass"
    }
    s += ", "

    s
  }
}
