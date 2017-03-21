package com.romeshselvan.pacman.entities.bodies

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d._
import com.romeshselvan.pacman.engine.input.State
import com.romeshselvan.pacman.engine.input.listeners.InputStateListener
import com.romeshselvan.pacman.inputContexts.GameStates

/**
  * @author Romesh Selvan
  */
class PacmanBody(val body: Body) extends EntityBody with InputStateListener with ContactListener {
  body.setUserData("player")

  private val additiveVelocity : Vector2 = new Vector2(0, 0)

  override def update(delta: Float): Unit = {
    setVelocity()
  }

  override def onStatePressed(state: State): Unit = {
    state match {
      case GameStates.MOVE_UP => additiveVelocity.y += 20.0f
      case GameStates.MOVE_DOWN => additiveVelocity.y += -20.0f
      case GameStates.MOVE_LEFT => additiveVelocity.x += -20.0f
      case GameStates.MOVE_RIGHT => additiveVelocity.x += 20.0f
    }
    setVelocity()
  }

  override def onStateReleased(state: State): Unit = {
      state match {
        case GameStates.MOVE_UP => additiveVelocity.y += -20.0f
        case GameStates.MOVE_DOWN => additiveVelocity.y += 20.0f
        case GameStates.MOVE_LEFT => additiveVelocity.x += 20.0f
        case GameStates.MOVE_RIGHT => additiveVelocity.x += -20.0f
      }

  }

  private def setVelocity() = body.setLinearVelocity(additiveVelocity)

  override def postSolve(contact: Contact, impulse: ContactImpulse): Unit = {}

  override def endContact(contact: Contact): Unit = {
    if(contact.getFixtureB.getBody.getUserData.asInstanceOf[String] == "wall") {
      println("Collision Done")
    }
  }

  override def beginContact(contact: Contact): Unit = {
    if(contact.getFixtureB.getBody.getUserData.asInstanceOf[String] == "wall") {
      println("Collision")
    }
  }

  override def preSolve(contact: Contact, oldManifold: Manifold): Unit = {}
}
