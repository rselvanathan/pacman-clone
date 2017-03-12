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

  override def render(batch: Batch) = {
    batch.begin()
    sprite.setPosition(body.getPosition.x, body.getPosition.y)
    sprite.draw(batch)
    batch.end()
  }

  override def update(delta: Float) = {}

  override def onStatePressed(state: State): Unit = {
    if(state.stateId == GameStates.MOVE_UP.stateId) body.setLinearVelocity(new Vector2(0.0f, 5.0f))
    if(state.stateId == GameStates.MOVE_DOWN.stateId) body.setLinearVelocity(new Vector2(0.0f, -5.0f))
    if(state.stateId == GameStates.MOVE_LEFT.stateId) body.setLinearVelocity(new Vector2(-5.0f, 0.0f))
    if(state.stateId == GameStates.MOVE_RIGHT.stateId) body.setLinearVelocity(new Vector2(5.0f, 0.0f))
  }

  override def onStateReleased(state: State): Unit = {
    body.setLinearVelocity(new Vector2(0.0f, 0.0f))
  }
}

