package de.htwg.se.durak.model.fileIoComponent

import de.htwg.se.durak.model.roundComponent.GameDataInterface

/**
 * Interface for storing and loading files
 */
trait FileIOInterface {
  /**
   * Load saved file
   *
   * @return List of GameData
   */
  def load: List[GameDataInterface]

  /**
   * Save GameData to a file
   *
   * @param gameDataList GameData that should be saved
   */
  def save(gameDataList: List[GameDataInterface]): Unit
}