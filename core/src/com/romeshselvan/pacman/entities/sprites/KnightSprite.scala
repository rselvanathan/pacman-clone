package com.romeshselvan.pacman.entities.sprites

import com.badlogic.gdx.graphics.g2d.{Batch, Sprite}
import com.romeshselvan.pacman.engine.entities.SpriteObject

/**
  * @author Romesh Selvan
  */
class KnightSprite(sprite: Sprite) extends SpriteObject(sprite) {

  override def render(delta: Float, batch: Batch, xPositon: Float, yPosition: Float): Unit = {
    batch.begin()
    sprite.setPosition(xPositon, yPosition)
    sprite.draw(batch)
    batch.end()
  }
}
