package com.romeshselvan.pacman.engine.screens

import com.badlogic.gdx.Screen

/**
  * @author Romesh Selvan
  */
class ScreenManager(private var activeScreen : Screen) {

  def changeScreen(newScreen : Screen) = {
    activeScreen.dispose()
    activeScreen = newScreen
    this
  }

  def render(delta : Float) = {
    activeScreen.render(delta)
  }

  def dispose = {
    activeScreen.dispose()
  }

  def getActiveScreen = activeScreen
}
