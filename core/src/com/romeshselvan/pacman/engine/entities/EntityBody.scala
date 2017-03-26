package com.romeshselvan.pacman.engine.entities

import com.badlogic.gdx.physics.box2d.Body

/**
  * @author Romesh Selvan
  */
abstract class EntityBody(val body : Body, val bodyType : EntityType) {

  body.setUserData(this)

  def update(delta : Float)

  def onCollision(otherBody : Body)

  def onCollisionEnd(otherBody : Body)

  override def hashCode(): Int = bodyType.hashCode
}
