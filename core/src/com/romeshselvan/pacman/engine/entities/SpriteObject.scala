package com.romeshselvan.pacman.engine.entities

import com.badlogic.gdx.graphics.g2d.Batch

/**
  * @author Romesh Selvan
  */
trait SpriteObject {
  def render(delta: Float, batch: Batch, xPositon : Float, yPosition : Float): Unit
  def dispose(): Unit
}
