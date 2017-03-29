package com.romeshselvan.pacman.entities.bodies

import box2dLight.PointLight
import com.badlogic.gdx.graphics.{Color, OrthographicCamera}
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
class PacmanBody(body: Body, camera: OrthographicCamera, light: PointLight) extends EntityBody(body, EntityTypes.PLAYER) with InputStateListener {

  private val additiveVelocity : Vector2 = new Vector2(0, 0)
  private val bodySpeed = 120.0f

  light.setDistance(100)
  light.setColor(Color.DARK_GRAY)
  light.setActive(true)
  light.setSoft(true)
  light.attachToBody(body)

  override def update(delta: Float): Unit = {
    setVelocity()
    //light.setPosition(body.getPosition.x, body.getPosition.y)
    camera.position.set(body.getPosition.x, body.getPosition.y, 0)
  }

  override def onStatePressed(state: State): Unit = {
    state match {
      case GameStates.MOVE_UP => additiveVelocity.y += bodySpeed
      case GameStates.MOVE_DOWN => additiveVelocity.y += bodySpeed * -1.0f
      case GameStates.MOVE_LEFT => additiveVelocity.x += bodySpeed * -1.0f
      case GameStates.MOVE_RIGHT => additiveVelocity.x += bodySpeed
    }
  }

  override def onStateReleased(state: State): Unit = {
    state match {
      case GameStates.MOVE_UP => additiveVelocity.y += bodySpeed * -1.0f
//        light.setActive(true)
      case GameStates.MOVE_DOWN => additiveVelocity.y += bodySpeed
//        light.setActive(false)
      case GameStates.MOVE_LEFT => additiveVelocity.x += bodySpeed
      case GameStates.MOVE_RIGHT => additiveVelocity.x += bodySpeed * -1.0f
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
