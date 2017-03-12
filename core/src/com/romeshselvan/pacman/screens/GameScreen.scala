package com.romeshselvan.pacman.screens

import com.badlogic.gdx.graphics.GL20
import com.badlogic.gdx.{Gdx, Screen}
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.romeshselvan.pacman.GameWorld
import com.romeshselvan.pacman.engine.eventManager.EventManager
import com.romeshselvan.pacman.engine.input.events.ContextChangedEvent
import com.romeshselvan.pacman.engine.input.listeners.ContextChangeListener

/**
  * @author Romesh Selvan
  */
class GameScreen(spriteBatch: SpriteBatch,
                 gameWorld: GameWorld,
                 eventManager: EventManager) extends Screen {

  eventManager.notifyListeners[ContextChangeListener](new ContextChangedEvent("game"))
  gameWorld.init

  override def dispose(): Unit = {
    gameWorld.dispose
    spriteBatch.dispose()
  }

  override def render(delta: Float) = {
    Gdx.gl.glClearColor(1, 0, 0, 1)
    Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT)
    gameWorld.update(delta)
    gameWorld.render(spriteBatch)
  }

  override def pause(): Unit = ???

  override def hide(): Unit = ???

  override def resize(width: Int, height: Int): Unit = ???

  override def show(): Unit = ???

  override def resume(): Unit = ???
}
