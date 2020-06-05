package de.htwg.se.durak.controller

import de.htwg.se.durak.model.RoundData

case class ObserverData() {
  val observable = new GameObservable
  var data: RoundData = _
}
