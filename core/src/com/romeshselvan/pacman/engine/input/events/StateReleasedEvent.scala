package com.romeshselvan.pacman.engine.input.events

import com.romeshselvan.pacman.engine.eventManager.Event
import com.romeshselvan.pacman.engine.input.State

/**
  * @author Romesh Selvan
  */
class StateReleasedEvent(state : State) extends Event[InputStateListener] {

  override def notify(listener: InputStateListener): Unit = listener.onStateReleased(state)
}