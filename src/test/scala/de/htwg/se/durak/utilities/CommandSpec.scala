package de.htwg.se.durak.utilities

import org.scalatest._
import org.junit.runner.{RunWith, Runner}
import org.scalatest.matchers.should.Matchers


class incrCommand extends Command {
  var state:Int =0
  override def doStep: Unit = state+=1
  override def undoStep: Unit = state-=1
}

@RunWith(classOf[Runner])
class CommandSpec extends WordSpec with Matchers {
  "A Command" should {
    "have a do step" in {
      val command = new incrCommand
      command.state should be(0)
      command.doStep
      command.state should be(1)
      command.doStep
      command.state should be(2)

    }
    "have an undo step" in {
      val command = new incrCommand
      command.state should be(0)
      command.doStep
      command.state should be(1)
      command.undoStep
      command.state should be(0)
    }
  }
}
