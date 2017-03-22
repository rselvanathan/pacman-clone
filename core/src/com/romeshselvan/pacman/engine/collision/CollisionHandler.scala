package com.romeshselvan.pacman.engine.collision

import com.badlogic.gdx.physics.box2d.{Contact, ContactImpulse, ContactListener, Manifold}
import com.romeshselvan.pacman.engine.entities.EntityBody

/**
  * @author Romesh Selvan
  */
object CollisionHandler extends ContactListener {

  override def preSolve(contact: Contact, oldManifold: Manifold): Unit = {}

  override def postSolve(contact: Contact, impulse: ContactImpulse): Unit = {}

  override def beginContact(contact: Contact): Unit = {
    val bodyA = contact.getFixtureA.getBody
    val bodyB = contact.getFixtureB.getBody
    val entityBodyA = bodyA.getUserData.asInstanceOf[EntityBody]
    val entityBodyB = bodyB.getUserData.asInstanceOf[EntityBody]
    entityBodyA.onCollision(bodyB)
    entityBodyB.onCollision(bodyA)
  }

  override def endContact(contact: Contact): Unit = {
    val bodyA = contact.getFixtureA.getBody
    val bodyB = contact.getFixtureB.getBody
    val entityBodyA = bodyA.getUserData.asInstanceOf[EntityBody]
    val entityBodyB = bodyB.getUserData.asInstanceOf[EntityBody]
    entityBodyA.onCollisionEnd(bodyB)
    entityBodyB.onCollisionEnd(bodyA)
  }
}
