package de.htwg.se.durak.controller

import de.htwg.se.durak.model.CardStack

/*
 * Stack types=
 * 0 = Player stack
 * 1 = playing stack
 * 2 = outside stack
 */
case class CardStackFactory() {
  def getInstance(cardStackType: Int): CardStack = {
    cardStackType match {
      case 0 => new CardStack()
      case 1 => new CardStack() // todo shake cards
      case 2 => new CardStack()
    }
  }
}
