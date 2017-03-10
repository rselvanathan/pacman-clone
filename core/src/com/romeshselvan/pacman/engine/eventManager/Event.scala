package com.romeshselvan.pacman.engine.eventManager

/**
  * @author Romesh Selvan
  */
trait Event[T] {
  def notify(listener : T)
}
