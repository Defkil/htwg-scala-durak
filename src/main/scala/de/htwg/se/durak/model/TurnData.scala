package de.htwg.se.durak.model

case class TurnData(players: List[Player], playerDecks: List[CardDeck]
                    , currentPlayer: Int, defendPlayer: Int, field: Field
                    , mainDeck:CardDeck, trump: Int) {

}
