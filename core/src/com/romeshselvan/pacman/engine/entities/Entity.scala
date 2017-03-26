package com.romeshselvan.pacman.engine.entities

import com.badlogic.gdx.graphics.g2d.Batch

/**
  * @author Romesh Selvan
  */
abstract class Entity(val body: EntityBody,
                      val sprite: SpriteObject,
                      val spriteMath: SpriteMath) {

  def dispose = if(sprite != null) sprite.dispose()

  def render(delta: Float, batch : Batch)

  def update(delta : Float)
}
