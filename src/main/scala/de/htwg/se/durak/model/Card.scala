package de.htwg.se.durak.model

case class Card(rank: Int, cardType: Int) {
  def get(): (Int, Int) = {
    (getRank, getType)
  }

  def getRank: Int = rank

  def getType: Int = cardType

  def getWeight(): Int = {
    cardType match {
      case 1 => rank
      case 2 => rank + 14
      case 3 => rank + 14 * 2
      case 4 => rank + 14 * 3
    }
  }

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
}
