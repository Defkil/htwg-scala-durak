package de.htwg.se.durak.utilities

/**
 * Observer trait
 */
trait Observer {
  /**
   * update observer
   */
  def update: Unit
}

/**
 * Class for observing something
 */
class Observable {
  /**
   * List of all subscriber
   */
  var subscribers: Vector[Observer] = Vector()

  /**
   * Add a subscriber
   * @param s subscriber which should be added
   */
  def add(s: Observer): Unit = subscribers = subscribers :+ s

  /**
   * Remove a subscriber
   *
   * @param s subscriber that should be removed
   */
  def remove(s: Observer): Unit = subscribers = subscribers.filterNot(o => o == s)

  /**
   * Notify all subscriber
   */
  def notifyObservers: Unit = subscribers.foreach(o => o.update)
}