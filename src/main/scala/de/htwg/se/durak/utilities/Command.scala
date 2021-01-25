package de.htwg.se.durak.utilities

/**
 * Command trait
 */
trait Command {
  /**
   * do Step
   */
  def doStep : Unit

  /**
   * undo Step
   */
  def undoStep : Unit

  /**
   * redo Step
   */
  def redoStep : Unit
}
