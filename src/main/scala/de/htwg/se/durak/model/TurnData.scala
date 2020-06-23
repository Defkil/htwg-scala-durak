package de.htwg.se.durak.model

case class TurnData(players: List[Player], playerStacks: List[CardDeck]
                    , playerId: Int, field: Field, msg: String, mainStack:CardDeck) {

}
