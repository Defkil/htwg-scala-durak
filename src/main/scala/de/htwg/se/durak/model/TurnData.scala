package de.htwg.se.durak.model

case class TurnData(players: List[Player], playerDecks: List[CardDeck]
                    , playerId: Int, field: Field, msg: String, mainDeck:CardDeck) {

}
