package com.romeshselvan.pacman.engine.input.events

import com.romeshselvan.pacman.engine.input.State

/**
  * @author Romesh Selvan
  */
trait InputStateListener {
  def onStatePressed(state : State)
  def onStateReleased(state : State)
}
