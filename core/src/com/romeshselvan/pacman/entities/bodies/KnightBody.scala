package com.romeshselvan.pacman.entities.bodies

import box2dLight.ConeLight
import com.badlogic.gdx.physics.box2d.Body
import com.romeshselvan.pacman.engine.entities.EntityBody
import com.romeshselvan.pacman.entities.EntityTypes

/**
  * @author Romesh Selvan
  */
class KnightBody(body : Body, coneLight : ConeLight) extends EntityBody(body, EntityTypes.KNIGHT) {
  override def update(delta: Float): Unit = {
    coneLight.setPosition(body.getPosition)
  }

  override def onCollision(otherBody: Body): Unit = {}

  override def onCollisionEnd(otherBody: Body): Unit = {}

}