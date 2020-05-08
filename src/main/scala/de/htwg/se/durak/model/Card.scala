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
      case 0 => s+= "6"
      case 1 => s += "7"
      case 2 => s += "8"
      case 3 => s += "9"
      case 4 => s += "10"
      case 5 => s += "Bube"
      case 6 => s += "Dame"
      case 7 => s += "König"
      case 8 => s += "Ass"
    }
    s
  }
}
