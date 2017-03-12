package com.romeshselvan.pacman.engine.input.listeners

import com.romeshselvan.pacman.engine.input.State

/**
  * @author Romesh Selvan
  */
trait InputStateListener {
  def onStatePressed(state : State)
  def onStateReleased(state : State)
}
