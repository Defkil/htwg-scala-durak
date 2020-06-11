package de.htwg.se.durak.model

case class TurnData(players: List[Player], playerStacks: List[CardStack]
                    , playerId: Int, field: Field, msg: String, mainStack:CardStack) {

}
