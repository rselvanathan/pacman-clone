package com.romeshselvan.pacman.engine.input.events

import com.romeshselvan.pacman.engine.eventManager.Event
import com.romeshselvan.pacman.engine.input.listeners.ContextChangeListener

/**
  * @author Romesh Selvan
  */
class ContextChangedEvent(contextName : String) extends Event[ContextChangeListener] {

  override def notify(listener: ContextChangeListener): Unit = listener.onContextChanged(contextName)

}
