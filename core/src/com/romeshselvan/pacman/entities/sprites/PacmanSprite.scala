package com.romeshselvan.pacman.entities.sprites

import com.badlogic.gdx.graphics.g2d.{Batch, Sprite}
import com.romeshselvan.pacman.engine.input.State
import com.romeshselvan.pacman.engine.input.listeners.InputStateListener
import com.romeshselvan.pacman.inputContexts.GameStates
import com.romeshselvan.pacman.textures.CharacterTextures

/**
  * @author Romesh Selvan
  */
class PacmanSprite(val sprite : Sprite) extends SpriteObject with InputStateListener {

  override def render(batch: Batch, xPosition : Float, yPosition : Float): Unit = {
    batch.begin()
    sprite.setPosition(xPosition, yPosition)
    sprite.draw(batch)
    batch.end()
  }

  override def dispose(): Unit = {
    sprite.getTexture.dispose()
  }

  override def onStatePressed(state: State): Unit = {
    state match {
      case GameStates.MOVE_UP => sprite.setRegion(CharacterTextures.upFacing)
      case GameStates.MOVE_DOWN => sprite.setRegion(CharacterTextures.downfacing)
      case GameStates.MOVE_LEFT => sprite.setRegion(CharacterTextures.leftFacing)
      case GameStates.MOVE_RIGHT => sprite.setRegion(CharacterTextures.rightFacing)
    }
  }

  override def onStateReleased(state: State): Unit = {}
}
