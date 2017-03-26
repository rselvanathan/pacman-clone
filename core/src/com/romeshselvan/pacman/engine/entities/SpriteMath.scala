package com.romeshselvan.pacman.engine.entities

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.physics.box2d.Body

/**
  * @author Romesh Selvan
  */
trait SpriteMath {
  def getSpritePositionX(body: Body, sprite : Sprite) : Float
  def getSpritePositionY(body: Body, sprite : Sprite) : Float
}

object SpriteMath extends SpriteMath{
  override def getSpritePositionX(body: Body, sprite: Sprite): Float = body.getPosition.x-sprite.getWidth/2
  override def getSpritePositionY(body: Body, sprite: Sprite): Float = body.getPosition.y -sprite.getHeight/2
}
