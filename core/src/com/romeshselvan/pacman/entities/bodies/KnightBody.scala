package com.romeshselvan.pacman.entities.bodies

import box2dLight.ConeLight
import com.badlogic.gdx.physics.box2d.Body
import com.romeshselvan.pacman.engine.ai.AISteering
import com.romeshselvan.pacman.engine.entities.EntityBody
import com.romeshselvan.pacman.entities.EntityTypes

/**
  * @author Romesh Selvan
  */
class KnightBody(body : Body, target : EntityBody, coneLight : ConeLight) extends EntityBody(body, 50, EntityTypes.KNIGHT) {

  val steering = new AISteering(this)

  override def update(delta: Float): Unit = {
    val arrive = steering.arrive(target, 100.0f, 5.0f)
    body.setLinearVelocity(arrive)
    println(arrive)
    coneLight.setPosition(body.getPosition)
  }

  override def onCollision(otherBody: Body): Unit = {}

  override def onCollisionEnd(otherBody: Body): Unit = {}

}