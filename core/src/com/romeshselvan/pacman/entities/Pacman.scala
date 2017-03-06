package com.romeshselvan.pacman.entities

import com.badlogic.gdx.graphics.g2d.{Batch, Sprite}
import com.badlogic.gdx.physics.box2d.Body

/**
  * @author Romesh Selvan
  */
class Pacman(body : Body, sprite : Sprite) extends Entity(body , sprite) {

  override def render(batch: Batch) = {
    batch.begin()
    sprite.draw(batch)
    batch.end()
  }

  override def update(delta: Float) = {}// do Nothing
}
