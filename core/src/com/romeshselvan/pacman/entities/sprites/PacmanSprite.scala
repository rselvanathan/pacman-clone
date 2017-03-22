package com.romeshselvan.pacman.entities.sprites

import com.badlogic.gdx.graphics.g2d.{Animation, Batch, Sprite, TextureRegion}
import com.romeshselvan.pacman.engine.entities.SpriteObject
import com.romeshselvan.pacman.engine.input.State
import com.romeshselvan.pacman.engine.input.listeners.InputStateListener
import com.romeshselvan.pacman.inputContexts.GameStates
import com.romeshselvan.pacman.textures.CharacterTextures

import collection.JavaConverters._

/**
  * @author Romesh Selvan
  */
class PacmanSprite(val sprite : Sprite) extends SpriteObject with InputStateListener {

  private var animateFrameTime = 0.2f

  private var downAnimate = false
  private var upAnimate = false
  private var leftAnimate = false
  private var rightAnimate = false

  private val downAnimation : Animation[TextureRegion] = new Animation[TextureRegion](0.25f, CharacterTextures.downFacingSet)
  private val leftAnimation : Animation[TextureRegion] = new Animation[TextureRegion](0.25f, CharacterTextures.leftFacingSet)
  private val rightAnimation : Animation[TextureRegion] = new Animation[TextureRegion](0.25f, CharacterTextures.rightFacingSet)
  private val upAnimation : Animation[TextureRegion] = new Animation[TextureRegion](0.25f, CharacterTextures.upFacingSet)

  override def render(delta: Float, batch: Batch, xPosition : Float, yPosition : Float): Unit = {
    batch.begin()
    if(downAnimate || upAnimate || rightAnimate || leftAnimate) {
      animateFrameTime += delta
    }
    if(downAnimate) sprite.setRegion(downAnimation.getKeyFrame(animateFrameTime, downAnimate))
    if(leftAnimate) sprite.setRegion(leftAnimation.getKeyFrame(animateFrameTime, leftAnimate))
    if(rightAnimate) sprite.setRegion(rightAnimation.getKeyFrame(animateFrameTime, rightAnimate))
    if(upAnimate) sprite.setRegion(upAnimation.getKeyFrame(animateFrameTime, upAnimate))
    sprite.setPosition(xPosition, yPosition)
    sprite.draw(batch)
    batch.end()
  }

  override def dispose(): Unit = {
    sprite.getTexture.dispose()
  }

  override def onStatePressed(state: State): Unit = {
      state match {
        case GameStates.MOVE_UP => upAnimate = true
        case GameStates.MOVE_DOWN => downAnimate = true
        case GameStates.MOVE_LEFT => leftAnimate = true
        case GameStates.MOVE_RIGHT => rightAnimate = true
      }
  }

  override def onStateReleased(state: State): Unit = {
    state match {
      case GameStates.MOVE_UP =>
        sprite.setRegion(CharacterTextures.upFacingSet.items(0))
        animateFrameTime = 0.2f
        upAnimate = false
      case GameStates.MOVE_DOWN =>
        sprite.setRegion(CharacterTextures.downFacingSet.items(0))
        animateFrameTime = 0.2f
        downAnimate = false
      case GameStates.MOVE_LEFT =>
        sprite.setRegion(CharacterTextures.leftFacingSet.items(0))
        animateFrameTime = 0.2f
        leftAnimate = false
      case GameStates.MOVE_RIGHT =>
        sprite.setRegion(CharacterTextures.rightFacingSet.items(0))
        animateFrameTime = 0.2f
        rightAnimate = false
    }
  }
}
