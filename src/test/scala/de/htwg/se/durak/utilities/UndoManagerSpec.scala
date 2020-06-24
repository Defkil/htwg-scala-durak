package de.htwg.se.durak.utilities

import org.scalatest._

class UndoManagerSpec extends WordSpec with Matchers {
  "An UndoManager" should {
    val undoManager = new UndoManager

    "have a do and undo" in {
      val command = new incrCommand
      command.state should be(0)
      undoManager.doStep(command)
      command.state should be(1)
      undoManager.undoStep
      command.state should be(0)
    }

    "handle multiple undo steps correctly" in {
      val command = new incrCommand
      command.state should be(0)
      undoManager.doStep(command)
      command.state should be(1)
      undoManager.doStep(command)
      command.state should be(2)
      undoManager.undoStep
      command.state should be(1)
      undoManager.undoStep
      command.state should be(0)
    }

    "test two undo undo" in {
      val undoManager = new UndoManager
      undoManager.undoStep
      undoManager.undoStep
      val command = new incrCommand
      command.state should be(0)
      undoManager.doStep(command)
    }
  }
}
