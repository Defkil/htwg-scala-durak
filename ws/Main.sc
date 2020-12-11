object DurakExample {
  override def toString: String = "Durak"
}
println(DurakExample)

case class Card(rank: Int, symbol: Int) {
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
  override def toString: String = "Rang: " + symbolString +", Typ: " + rankString
}

val card1 = new Card(2,2)
val card2 = new Card(3,2)

case class CardDeck(var deck: List[Card])  {
  def this() {
    this(List())
  }
  def size: Int = deck.size
  def addCard(card: Card): CardDeck = CardDeck((deck :+ card).distinct)
  def addCards(cards: List[Card]): CardDeck = CardDeck((deck ++ cards).distinct)

  def removeCard(position: Int): CardDeck = {
    if(position >= deck.size) throw new IndexOutOfBoundsException
    CardDeck(deck.take(position) ++ deck.drop(position + 1))
  }

  def removeCard(card: Card): CardDeck = CardDeck(deck.filterNot(cardFromDeck => cardFromDeck.equals(card)))

  def pop(): (CardDeck, Card) =  (CardDeck(deck.init), deck.last)
}

var carddeck1 = new CardDeck()
carddeck1 = carddeck1.addCard(card1)
carddeck1 = carddeck1.addCard(card2)
println(carddeck1.size)


case class Field(var cardDeck: CardDeck) {
  def this() {
    this(new CardDeck());
  }
  val size: Int = cardDeck.size

  def addCard(card: Card): Field =  Field(cardDeck.addCard(card))
}

var field = new Field()
field = field.addCard(card1)
println(field.size)