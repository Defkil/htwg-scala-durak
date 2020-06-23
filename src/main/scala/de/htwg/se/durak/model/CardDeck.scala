package de.htwg.se.durak.model

/**
 * CardDeck for handling multiple Cards in a List
 * @constructor CardDeck with existing List
 * @param deck List of Cards for the new CardDeck
 */
case class CardDeck(var deck: List[Card]){

  /**
   * empty CardDeck
   */
  def this() {
    this(List())
  }

  /**
   *
   * @return size of CardDeck
   */
  def size: Int = deck.size

  /**
   * add a Card to the CardDeck
   * @example var deck = new CardDeck().addCard(Card(9, 3))
   * @param card Card which should be add
   * @return new CardDeck Object
   */
  def addCard(card: Card): CardDeck = {
    CardDeck((deck :+ card).distinct)
  }

  def addCards(cards: List[Card]): CardDeck = {
    CardDeck((deck ++ cards).distinct)
  }

  def removeCard(position: Int): CardDeck = {
    if(position >= deck.size) throw new IndexOutOfBoundsException
    CardDeck(deck.take(position) ++ deck.drop(position + 1))
  }

  def removeCard(card: Card): CardDeck = {
    CardDeck(deck.filterNot(cardFromDeck => cardFromDeck.equals(card)))
  }

  def pop(): (CardDeck, Card) = {
    (CardDeck(deck.init), deck.last)
  }

  def sort(): CardDeck = {
    this
  }

  def sort(strategy: Int): CardDeck = {
    this
  }
}

/*def generateStack(): ListBuffer[Card] = {
    val y = 0
    val x = 0
    if(size == 48){
      for( x <- 2 until 14)
        for( y <- 1 to 4)
          cards += (new Card(x, y))
    }else if(size == 36){
      for( x <- 5 until 14)
        for( y <- 1 to 4)
          cards += (new Card(x, y))
    }
    cards = util.Random.shuffle(cards)
    cards
  }*/