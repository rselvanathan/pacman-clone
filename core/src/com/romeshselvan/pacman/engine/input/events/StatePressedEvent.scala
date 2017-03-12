package com.romeshselvan.pacman.engine.input.events

import com.romeshselvan.pacman.engine.eventManager.Event
import com.romeshselvan.pacman.engine.input.State
import com.romeshselvan.pacman.engine.input.listeners.InputStateListener

/**
  * @author Romesh Selvan
  */
class StatePressedEvent(state : State) extends Event[InputStateListener] {

  override def notify(listener: InputStateListener): Unit = listener.onStatePressed(state)
}
