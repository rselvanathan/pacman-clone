package com.romeshselvan.pacman.entities.sprites

import com.badlogic.gdx.graphics.g2d.Batch

/**
  * @author Romesh Selvan
  */
trait SpriteObject {
  def render(batch: Batch, xPositon : Float, yPosition : Float): Unit
  def dispose(): Unit
}
