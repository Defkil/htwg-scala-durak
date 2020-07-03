package de.htwg.se.durak

import com.google.inject.AbstractModule
import com.google.inject.name.Names
import de.htwg.se.durak.controller.controllerComponent.ControllerInterface
import de.htwg.se.durak.controller.controllerComponent.controllerBaseImpl
import de.htwg.se.durak.controller.gameLogicComponent.gameLogicBaseImpl
import de.htwg.se.durak.controller.gameLogicComponent.GameLogicInterface
import de.htwg.se.durak.model.gameElementsComponent.GameElementsInterface
import de.htwg.se.durak.model.gameElementsComponent.gameElementsBaseImpl.GameElements
import net.codingwell.scalaguice.ScalaModule

class DurakModule extends AbstractModule with ScalaModule {
  def configure(): Unit = {
    bind[GameElementsInterface].to[GameElements]
    bind[ControllerInterface].to[controllerBaseImpl.Controller]
    bind[GameLogicInterface].to[gameLogicBaseImpl.GameLogic]
  }
}
