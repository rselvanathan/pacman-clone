package com.romeshselvan.pacman.entities

import com.badlogic.gdx.graphics.g2d.Batch
import com.romeshselvan.pacman.entities.bodies.EntityBody
import com.romeshselvan.pacman.entities.sprites.SpriteObject

/**
  * @author Romesh Selvan
  */
class WallEntity(entity: EntityBody, sprite : SpriteObject) extends Entity(entity, sprite, SpriteMath) {

  override def render(delta: Float, batch: Batch): Unit = {}

  override def update(delta: Float): Unit = body.update(delta)
}
