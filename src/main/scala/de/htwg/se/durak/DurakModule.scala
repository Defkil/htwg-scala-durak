package de.htwg.se.durak

import de.htwg.se.durak.controller.controllerComponent
import de.htwg.se.durak.controller.gameLogicComponent
import de.htwg.se.durak.model.roundComponent
import de.htwg.se.durak.model.gameElementsComponent

import net.codingwell.scalaguice.ScalaModule
import com.google.inject.AbstractModule

class DurakModule extends AbstractModule with ScalaModule {
  def configure(): Unit = {
    bind[controllerComponent.ControllerInterface].to[controllerComponent.controllerBaseImpl.Controller]
    bind[gameLogicComponent.GameLogicInterface].to[gameLogicComponent.gameLogicBaseImpl.GameLogic]
    bind[roundComponent.RoundInterface].to[roundComponent.roundBaseImpl.Round]
    bind[gameElementsComponent.GameElementsInterface].to[gameElementsComponent.gameElementsBaseImpl.GameElements]
  }
}
