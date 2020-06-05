package de.htwg.se.durak.controller

import de.htwg.se.durak.model.RoundData
import de.htwg.se.durak.view.Tui

case class ObserverIn() {
  val gm = new GameObservable

  var spacerSize: Int = 10
  var data: RoundData = _
  def addObservable(observer: Observer): Unit = {
    gm.add(observer)
  }
}
