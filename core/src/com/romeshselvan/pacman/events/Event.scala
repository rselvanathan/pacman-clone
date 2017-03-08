package com.romeshselvan.pacman.events

/**
  * @author Romesh Selvan
  */
trait Event[T] {
  def notify(listener : T)
}
