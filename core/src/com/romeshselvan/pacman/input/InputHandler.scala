package com.romeshselvan.pacman.input

import com.badlogic.gdx.InputProcessor

/**
  * @author Romesh Selvan
  */
class InputHandler(contextManager: InputContextManager) extends InputProcessor {



  override def keyTyped(character: Char): Boolean = ???

  override def mouseMoved(screenX: Int, screenY: Int): Boolean = ???

  override def keyDown(keycode: Int): Boolean = ???

  override def touchDown(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = ???

  override def keyUp(keycode: Int): Boolean = ???

  override def scrolled(amount: Int): Boolean = ???

  override def touchUp(screenX: Int, screenY: Int, pointer: Int, button: Int): Boolean = ???

  override def touchDragged(screenX: Int, screenY: Int, pointer: Int): Boolean = ???
}
