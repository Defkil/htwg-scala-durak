package de.htwg.se.durak.model.gameElementsComponent.gameElementsBaseImpl

import de.htwg.se.durak.model.gameElementsComponent.{CardDeckInterface, CardInterface, FieldInterface, GameElementsInterface}

case class GameElements() extends GameElementsInterface{
  def createCard(rank: Int, symbol: Int): CardInterface = Card(rank, symbol)
  def createCardDeck(): CardDeckInterface = new CardDeck()
  def createCardDeck(deck: List[CardInterface]): CardDeckInterface = CardDeck(deck)
  def createField(): FieldInterface = new Field()
  def createField(cardDeck: CardDeckInterface): FieldInterface = Field(cardDeck)
}
