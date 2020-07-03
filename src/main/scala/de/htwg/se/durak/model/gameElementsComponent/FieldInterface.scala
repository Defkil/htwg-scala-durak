package de.htwg.se.durak.model.gameElementsComponent

trait FieldInterface {
  var cardDeck: CardDeckInterface
  val size: Int
  val deck: List[CardInterface] = cardDeck.deck
  def addCard(card: CardInterface): FieldInterface
}
