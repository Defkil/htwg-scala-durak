package de.htwg.se.durak.model.fileIoComponent

import de.htwg.se.durak.model.roundComponent.GameDataInterface

trait FileIOInterface {
  def load: List[GameDataInterface]
  def save(gameDataList: List[GameDataInterface]): Unit
}