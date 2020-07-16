package de.htwg.se.durak.model.roundComponent

import de.htwg.se.durak.model.gameElementsComponent.{CardDeckInterface, FieldInterface}
import de.htwg.se.durak.model.playerComponent.Player

trait TurnDataInterface {
  val players: List[Player]
  val playerDecks: List[CardDeckInterface]
  val currentPlayer: Int
  val defendPlayer: Int
  val field: FieldInterface
  val mainDeck: CardDeckInterface
  val outDeck: CardDeckInterface
  val trump: Int
}
