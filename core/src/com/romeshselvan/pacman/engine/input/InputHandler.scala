package com.romeshselvan.pacman.engine.input

import com.badlogic.gdx.InputProcessor
import com.romeshselvan.pacman.engine.eventManager.EventManager
import com.romeshselvan.pacman.engine.input.events.{InputStateListener, StatePressedEvent, StateReleasedEvent}

/**
  * @author Romesh Selvan
  */
class InputHandler(contextManager: InputContextManager, eventManager: EventManager) extends InputProcessor {

  override def keyTyped(character: Char): Boolean = true

  override def mouseMoved(screenX: Int, screenY: Int): Boolean = true

  override def keyDown(keycode: Int): Boolean = {
    val state = contextManager.getState(keycode)
    if(state.isDefined) eventManager.notifyListeners[InputStateListener](new StatePressedEvent(state.get))
    true
  }

  override def touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = true

  override def keyUp(keycode: Int): Boolean = {
    val state = contextManager.getState(keycode)
    if(state.isDefined) eventManager.notifyListeners[InputStateListener](new StateReleasedEvent(state.get))
    true
  }

  override def scrolled(amount: Int): Boolean = true

  override def touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = true

  override def touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean = true
}
