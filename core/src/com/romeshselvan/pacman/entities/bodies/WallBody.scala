package com.romeshselvan.pacman.entities.bodies

import com.badlogic.gdx.physics.box2d.Body
import com.romeshselvan.pacman.engine.entities.EntityBody
import com.romeshselvan.pacman.entities.EntityTypes

/**
  * @author Romesh Selvan
  */
class WallBody(body : Body) extends EntityBody(body, 0, EntityTypes.WALL) {

  override def update(delta: Float): Unit = {}

  override def onCollision(otherBody: Body): Unit = {
    if(otherBody.getUserData.asInstanceOf[EntityBody].bodyType == EntityTypes.PLAYER) {
      println("Collision With Player")
    }
  }

  override def onCollisionEnd(otherBody: Body): Unit = {
    if(otherBody.getUserData.asInstanceOf[EntityBody].bodyType == EntityTypes.PLAYER) {
      println("Collision with Player End")
    }
  }
}
