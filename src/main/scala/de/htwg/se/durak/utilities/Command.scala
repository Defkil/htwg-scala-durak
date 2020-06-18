package de.htwg.se.durak.utilities

trait Command {
  def doStep : Unit
  def undoStep : Unit
  def redoStep : Unit
}
