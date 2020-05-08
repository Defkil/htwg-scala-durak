package de.htwg.se.durak.model

class Card(rank: Int, cardType: Int) {
  def get(): (Int, Int) = {
    (rank, cardType)
  }

  override def toString: String = {
    var s = "Typ: "
    cardType match {
      case 0 => s += "Pik"
      case 1 => s += "Karo"
      case 2 => s += "Kreuz"
      case 3 => s += "Herz"
    }
    s += ", Rang: "
    rank match {
      case 0 => s+= "2"
      case 1 => s+= "3"
      case 2 => s+= "4"
      case 3 => s+= "5"
      case 4 => s+= "6"
      case 5 => s += "7"
      case 6 => s += "8"
      case 7 => s += "9"
      case 8 => s += "10"
      case 9 => s += "Bube"
      case 10 => s += "Dame"
      case 11 => s += "König"
      case 12 => s += "Ass"
    }
    s
  }
}
