package com.romeshselvan.pacman.entities

import com.badlogic.gdx.graphics.g2d.Batch
import com.romeshselvan.pacman.entities.bodies.PacmanBody
import com.romeshselvan.pacman.entities.sprites.PacmanSprite

/**
  * @author Romesh Selvan
  */
class PacmanEntity(body : PacmanBody, sprite : PacmanSprite) extends Entity(body , sprite, SpriteMath) {

  override def render(batch: Batch): Unit = {
    sprite.render(batch, spriteMath.getSpritePositionX(body.body, sprite.sprite), spriteMath.getSpritePositionY(body.body, sprite.sprite))
  }

  override def update(delta: Float): Unit = {
    body.update(delta)
  }
}

