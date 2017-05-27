package com.romeshselvan.pacman.entities.bodies

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d._
import com.romeshselvan.pacman.engine.entities.EntityBody
import com.romeshselvan.pacman.engine.input.State
import com.romeshselvan.pacman.engine.input.listeners.InputStateListener
import com.romeshselvan.pacman.entities.EntityTypes
import com.romeshselvan.pacman.inputContexts.GameStates

/**
  * @author Romesh Selvan
  */
class PacmanBody(body: Body, camera: OrthographicCamera) extends EntityBody(body, 120.0f,EntityTypes.PLAYER) with InputStateListener {

  private val additiveVelocity : Vector2 = new Vector2(0, 0)

  override def update(delta: Float): Unit = {
    setVelocity()
    camera.position.set(body.getPosition.x, body.getPosition.y, 0)
  }

  override def onStatePressed(state: State): Unit = {
    state match {
      case GameStates.MOVE_UP => additiveVelocity.y += maxSpeed
      case GameStates.MOVE_DOWN => additiveVelocity.y += maxSpeed * -1.0f
      case GameStates.MOVE_LEFT => additiveVelocity.x += maxSpeed * -1.0f
      case GameStates.MOVE_RIGHT => additiveVelocity.x += maxSpeed
    }
  }

  override def onStateReleased(state: State): Unit = {
    state match {
      case GameStates.MOVE_UP => additiveVelocity.y += maxSpeed * -1.0f
      case GameStates.MOVE_DOWN => additiveVelocity.y += maxSpeed
      case GameStates.MOVE_LEFT => additiveVelocity.x += maxSpeed
      case GameStates.MOVE_RIGHT => additiveVelocity.x += maxSpeed * -1.0f
    }
  }

  private def setVelocity() = body.setLinearVelocity(additiveVelocity)


  override def onCollision(otherBody: Body): Unit = {
    if(otherBody.getUserData.asInstanceOf[EntityBody].bodyType == EntityTypes.WALL) {
      println("Collision with Wall")
    }
  }

  override def onCollisionEnd(otherBody: Body): Unit = {
    if(otherBody.getUserData.asInstanceOf[EntityBody].bodyType == EntityTypes.WALL) {
      println("Collision with Wall Done")
    }
  }
}
