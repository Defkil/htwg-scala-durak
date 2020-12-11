package de.htwg.se.durak.model.playerComponent

/**
 * Storing player information
 *
 * @param name player name
 */
case class Player(name: String) {
   /**
    * Return players name as string
    *
    * @return players name
    */
   override def toString:String = name
}
