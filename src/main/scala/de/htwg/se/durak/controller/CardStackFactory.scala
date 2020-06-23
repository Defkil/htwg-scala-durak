package de.htwg.se.durak.controller

import de.htwg.se.durak.model.CardDeck

/*
 * Stack types=
 * 0 = Player stack
 * 1 = playing stack
 * 2 = outside stack
 */
case class CardStackFactory() {
  //todo scala style apply (Object)
  def getInstance(cardStackType: Int): CardDeck = {
    cardStackType match {
      case 0 => new CardDeck()
      case 1 => new CardDeck() // todo shake cards
      case 2 => new CardDeck()
    }
  }
}
