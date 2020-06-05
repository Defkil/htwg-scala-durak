package de.htwg.se.durak.controller

import de.htwg.se.durak.model.RoundData

trait Observer {
  def update: Unit
}

class GameObservable {
  var subscribers: Vector[Observer] = Vector()
  def add(s: Observer) = subscribers = subscribers :+ s
  def remove(s: Observer) = subscribers = subscribers.filterNot(o=>o==s)
  def notifyObservers() = subscribers.foreach(o=>o.update)
}
