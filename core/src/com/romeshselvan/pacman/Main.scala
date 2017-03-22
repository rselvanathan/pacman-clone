package com.romeshselvan.pacman

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.{ApplicationAdapter, Gdx}
import com.romeshselvan.pacman.engine.eventManager.EventManager
import com.romeshselvan.pacman.engine.input.{InputContextLoader, InputContextManager, InputHandler}
import com.romeshselvan.pacman.engine.screens.ScreenManager
import com.romeshselvan.pacman.producers.GameObjectProducer
import com.romeshselvan.pacman.screens.GameScreen

import scala.io.Source

/**
  * @author Romesh Selvan
  */
class Main extends ApplicationAdapter {

  private var screenManager : ScreenManager = _

  private var inputHandler : InputHandler = _

  override def create() {
    val jsonString = Source.fromInputStream(Gdx.files.internal("input.json").read()).mkString
    val inputContextManager = new InputContextManager(InputContextLoader.loadInputContexts(jsonString))
    inputHandler = new InputHandler(inputContextManager, EventManager)
    Gdx.input.setInputProcessor(inputHandler)

    val spriteBatch = new SpriteBatch
    screenManager = new ScreenManager(new GameScreen(spriteBatch, new GameWorld(GameObjectProducer), EventManager))
  }

  override def render() {
    screenManager.render(Gdx.graphics.getDeltaTime)
  }

  override def dispose() {
    screenManager.dispose
    EventManager.clearListenerMap()
  }
}
