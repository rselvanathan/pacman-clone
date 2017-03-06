package com.romeshselvan.pacman.entities

import com.badlogic.gdx.graphics.g2d.{Batch, Sprite}
import com.badlogic.gdx.physics.box2d.Body

/**
  * @author Romesh Selvan
  */
abstract class Entity(val body: Body,
                      val sprite: Sprite) {

  def dispose = sprite.getTexture.dispose()

  def render(batch : Batch)

  def update(delta : Float)
}
