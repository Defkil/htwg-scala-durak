package de.htwg.se.durak.model.playerComponent

/**
 * storing all the names of gamers
 * @param name ist one of the gamers
 */
case class Player(name: String) {
   override def toString:String = name
}
