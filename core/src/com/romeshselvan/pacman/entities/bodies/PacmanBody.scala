package com.romeshselvan.pacman.entities.bodies

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d._
import com.romeshselvan.pacman.engine.entities.EntityBody
import com.romeshselvan.pacman.engine.input.State
import com.romeshselvan.pacman.engine.input.listeners.InputStateListener
import com.romeshselvan.pacman.inputContexts.GameStates

/**
  * @author Romesh Selvan
  */
class PacmanBody(body: Body) extends EntityBody(body, "player") with InputStateListener {

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


  override def onCollision(otherBody: Body): Unit = {
    if(otherBody.getUserData.asInstanceOf[EntityBody].hashCode() == "wall".hashCode) {
      println("Collision")
    }
  }

  override def onCollisionEnd(otherBody: Body): Unit = {
    if(otherBody.getUserData.asInstanceOf[EntityBody].hashCode() == "wall".hashCode) {
      println("Collision Done")
    }
  }
}
