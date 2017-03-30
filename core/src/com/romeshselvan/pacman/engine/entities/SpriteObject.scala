package com.romeshselvan.pacman.engine.entities

import com.badlogic.gdx.graphics.g2d.{Batch, Sprite}

/**
  * @author Romesh Selvan
  */
abstract class SpriteObject(val sprite : Sprite) {
  def render(delta: Float, batch: Batch, xPositon : Float, yPosition : Float): Unit

  def dispose(): Unit = sprite.getTexture.dispose()
}
