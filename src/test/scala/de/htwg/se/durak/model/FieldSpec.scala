import de.htwg.se.durak.model.{CardStack,Field, Card}

import org.scalatest._
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

class FieldSpec extends WordSpec with Matchers {
  "A field" when {
    "new" should {
    val stack = new CardStack()
    val field = new Field(stack)
      "making to attacks and receive each new CardStack as s1 and s2" in {
        val s1 = field.addAttack(new Card(11, 4))
        val s2 = field.addAttack(new Card(8, 2))
        "checking s1 for size 1 and the first inserted card" in {
          s1.size should be(1)
          s1(0).tuiString should be("Bube, Herz")
        }
        "checking s2 for size to and the two inserted cards" in {
          s2.size should be(2)
          s2(0).tuiString should be("Bube, Herz")
          s2(1).tuiString should be("9, Karo")
        }
      }
    }
  }
}