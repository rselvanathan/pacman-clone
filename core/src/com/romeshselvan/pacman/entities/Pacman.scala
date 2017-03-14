package com.romeshselvan.pacman.entities

import com.badlogic.gdx.graphics.g2d.{Batch, Sprite}
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.romeshselvan.pacman.engine.input.State
import com.romeshselvan.pacman.engine.input.listeners.InputStateListener
import com.romeshselvan.pacman.inputContexts.GameStates

/**
  * @author Romesh Selvan
  */
class Pacman(body : Body, sprite : Sprite) extends Entity(body , sprite) with InputStateListener {

  private val additiveVelocity : Vector2 = new Vector2(0, 0)

  override def render(batch: Batch): Unit = {
    batch.begin()
    sprite.setPosition(body.getPosition.x+sprite.getWidth*2, body.getPosition.y+sprite.getHeight)
    sprite.draw(batch)
    batch.end()
  }

  override def update(delta: Float): Unit = {
  }

  override def onStatePressed(state: State): Unit = {
    state match {
      case GameStates.MOVE_UP => additiveVelocity.y += 5.0f
      case GameStates.MOVE_DOWN => additiveVelocity.y += -5.0f
      case GameStates.MOVE_LEFT => additiveVelocity.x += -5.0f
      case GameStates.MOVE_RIGHT => additiveVelocity.x += 5.0f
    }
    setVelocity()
  }

  override def onStateReleased(state: State): Unit = {
    state match {
      case GameStates.MOVE_UP => additiveVelocity.y += -5.0f
      case GameStates.MOVE_DOWN => additiveVelocity.y += 5.0f
      case GameStates.MOVE_LEFT => additiveVelocity.x += 5.0f
      case GameStates.MOVE_RIGHT => additiveVelocity.x += -5.0f
    }
    setVelocity()
  }

  private def setVelocity() = body.setLinearVelocity(additiveVelocity)
}

