package com.romeshselvan.pacman.entities.bodies

import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.romeshselvan.pacman.engine.input.State
import com.romeshselvan.pacman.engine.input.listeners.InputStateListener
import com.romeshselvan.pacman.inputContexts.GameStates

/**
  * @author Romesh Selvan
  */
class PacmanBody(val body: Body) extends EntityBody with InputStateListener{
  private val additiveVelocity : Vector2 = new Vector2(0, 0)

  override def update(delta: Float): Unit = {}

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
    setVelocity()
  }

  private def setVelocity() = body.setLinearVelocity(additiveVelocity)

}
