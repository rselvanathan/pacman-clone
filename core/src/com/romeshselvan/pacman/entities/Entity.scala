package com.romeshselvan.pacman.entities

import com.badlogic.gdx.graphics.g2d.Batch
import com.romeshselvan.pacman.entities.bodies.EntityBody
import com.romeshselvan.pacman.entities.sprites.SpriteObject

/**
  * @author Romesh Selvan
  */
abstract class Entity(val body: EntityBody,
                      val sprite: SpriteObject,
                      val spriteMath: SpriteMath) {

  def dispose = sprite.dispose()

  def render(delta: Float, batch : Batch)

  def update(delta : Float)
}
