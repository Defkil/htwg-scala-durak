package de.htwg.se.durak.controller

import de.htwg.se.durak.model.RoundData
import de.htwg.se.durak.view.Tui

case class Router() {
  val gm = new GameObservable
  var siteID: Int = -1
  var params: Array[String] = null


  var data: RoundData = _
  def addObservable(observer: Observer): Unit = {
    gm.add(observer)
  }
}
