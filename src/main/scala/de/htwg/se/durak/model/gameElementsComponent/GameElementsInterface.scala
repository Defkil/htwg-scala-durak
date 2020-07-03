package de.htwg.se.durak.model.gameElementsComponent

trait GameElementsInterface {
  def createCard(rank: Int, symbol: Int): CardInterface
  def createCardDeck(): CardDeckInterface
  def createCardDeck(deck: List[CardInterface]): CardDeckInterface
  def createField(): FieldInterface
  def createField(cardDeck: CardDeckInterface): FieldInterface
}
