package de.htwg.se.durak.controller

import de.htwg.se.durak.model.Card
import de.htwg.se.durak.utilities.Input


case class Runtime(gameLogic: GameLogic, gameTable: GameTable, input: Input, trumpCard : Card) {

}
