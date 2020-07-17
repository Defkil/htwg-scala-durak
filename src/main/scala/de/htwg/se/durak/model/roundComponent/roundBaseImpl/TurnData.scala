package de.htwg.se.durak.model.roundComponent.roundBaseImpl

import de.htwg.se.durak.model.gameElementsComponent.gameElementsBaseImpl.Card
import de.htwg.se.durak.model.gameElementsComponent.{CardDeckInterface, FieldInterface}
import de.htwg.se.durak.model.playerComponent.Player
import de.htwg.se.durak.model.roundComponent.TurnDataInterface

case class TurnData (
  players: List[Player], playerDecks: List[CardDeckInterface]
  , currentPlayer: Int, defendPlayer: Int, field: FieldInterface
  , mainDeck: CardDeckInterface, outDeck: CardDeckInterface
  , trump: Int, turnType: Int
) extends TurnDataInterface{
  def addCard(card: Card): TurnData = TurnData(
    players, playerDecks, currentPlayer, defendPlayer
    , field.addCard(card), mainDeck, outDeck, trump, turnType
  )
}

/*
 turnType = 0  Default attack/defense
 turnType = 1  Attacker skipped
 turnType = 2  Defender takes
 */