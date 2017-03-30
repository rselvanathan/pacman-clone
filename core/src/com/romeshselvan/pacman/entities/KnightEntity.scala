package com.romeshselvan.pacman.entities

import com.badlogic.gdx.graphics.g2d.Batch
import com.romeshselvan.pacman.engine.entities.{Entity, EntityBody, SpriteMath, SpriteObject}

/**
  * @author Romesh Selvan
  */
class KnightEntity(entityBody: EntityBody, spriteObject: SpriteObject) extends Entity(entityBody, spriteObject, SpriteMath) {

  override def render(delta: Float, batch: Batch): Unit =
    spriteObject.render(delta, batch, SpriteMath.getSpritePositionX(entityBody.body, spriteObject.sprite), SpriteMath.getSpritePositionY(entityBody.body, spriteObject.sprite))

  override def update(delta: Float): Unit = entityBody.update(delta)
}
