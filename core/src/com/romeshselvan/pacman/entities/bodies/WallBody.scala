package com.romeshselvan.pacman.entities.bodies

import com.badlogic.gdx.physics.box2d.Body
import com.romeshselvan.pacman.engine.entities.EntityBody

/**
  * @author Romesh Selvan
  */
class WallBody(body : Body) extends EntityBody(body, "wall") {
  override def update(delta: Float): Unit = {}

  override def onCollision(otherBody: Body): Unit = {
    if(otherBody.getUserData.asInstanceOf[EntityBody].hashCode() == "player".hashCode) {
      println("Collision")
    }
  }

  override def onCollisionEnd(otherBody: Body): Unit = {
    if(otherBody.getUserData.asInstanceOf[EntityBody].hashCode() == "player".hashCode) {
      println("Collision End")
    }
  }
}
