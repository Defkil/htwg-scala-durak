package de.htwg.se.durak.utilities

/**
 * holding all commands done in a stack to make undo possible
 */
class UndoManager {
  private var undoStack : List[Command] = Nil

  /**
   * called at every user action
   * @param command is what to do
   */
  def doStep(command : Command): Unit = {
    undoStack = command :: undoStack
    command.doStep
  }

  /**
   * reverse the last step
   */
  def undoStep: Unit = undoStack match {
    case Nil =>
    case head::stack => {
      head.undoStep
      undoStack = stack
    }
  }
}
