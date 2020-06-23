package de.htwg.se.durak.utilities

import org.scalatest._
import org.junit.runner.{RunWith, Runner}
import org.scalatest.matchers.should.Matchers

@RunWith(classOf[Runner])
class ObservableSpec extends WordSpec with Matchers {
  "An Observable" should {
    val observable = new Observable
    val observer = new Observer {
      var updated: Boolean = false
      def isUpdated: Boolean = updated
      override def update: Unit = {updated = true;}
    }
    "add an Observer" in {
      observable.add(observer)
      observable.subscribers should contain (observer)
    }
    "notify an Observer" in {
      observer.isUpdated should be(false)
      observable.notifyObservers
      observer.isUpdated should be(true)
    }
    "remove an Observer" in {
      observable.remove(observer)
      observable.subscribers should not contain (observer)
    }

  }

}