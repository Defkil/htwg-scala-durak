package de.htwg.se.durak.model

import de.htwg.se.durak.model.gameElementsComponent.CardDeckInterface
import de.htwg.se.durak.model.gameElementsComponent.gameElementsBaseImpl.{Card, CardDeck, Field}
import de.htwg.se.durak.model.playerComponent.Player

case class TurnData(players: List[Player], playerDecks: List[CardDeckInterface]
                    , currentPlayer: Int, defendPlayer: Int, field: Field
                    , mainDeck:CardDeckInterface, trump: Int) {

  def addCard(card: Card): TurnData = TurnData(
    players, playerDecks, currentPlayer,
    defendPlayer, field.addCard(card), mainDeck, trump
  )
}
