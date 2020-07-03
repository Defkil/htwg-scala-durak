package de.htwg.se.durak.model.gameElementsComponent

import de.htwg.se.durak.model.gameElementsComponent.gameElementsBaseImpl.CardDeck


trait CardDeckInterface {
  var deck: List[CardInterface]
  def size: Int
  def addCard(card: CardInterface): CardDeckInterface
  def addCards(cards: List[CardInterface]): CardDeckInterface
  def removeCard(position: Int): CardDeckInterface
  def removeCard(card: CardInterface): CardDeckInterface
  def pop(): (CardDeckInterface, CardInterface)
}
