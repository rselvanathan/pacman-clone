package com.romeshselvan.pacman

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.{ApplicationAdapter, Gdx}
import com.romeshselvan.pacman.producers.GameObjectProduce
import com.romeshselvan.pacman.screens.{GameScreen, ScreenManager}

/**
  * @author Romesh Selvan
  */
class Main extends ApplicationAdapter {

  var screenManager : ScreenManager = _

  override def create() {
    val spriteBatch = new SpriteBatch
    screenManager = new ScreenManager(new GameScreen(spriteBatch, new GameWorld(GameObjectProduce)))
  }

  override def render() {
    screenManager.render(Gdx.graphics.getDeltaTime)
  }

  override def dispose() {
    screenManager.dispose
  }
}
