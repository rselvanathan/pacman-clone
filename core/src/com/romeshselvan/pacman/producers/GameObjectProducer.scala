package com.romeshselvan.pacman.producers

import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.physics.box2d.{BodyDef, FixtureDef, PolygonShape, World}
import com.romeshselvan.pacman.engine.eventManager.EventManager
import com.romeshselvan.pacman.engine.input.events.{StatePressedEvent, StateReleasedEvent}
import com.romeshselvan.pacman.engine.input.listeners.InputStateListener
import com.romeshselvan.pacman.entities.PacmanEntity
import com.romeshselvan.pacman.entities.bodies.PacmanBody
import com.romeshselvan.pacman.entities.sprites.PacmanSprite
import com.romeshselvan.pacman.textures.CharacterTextures

/**
  * @author Romesh Selvan
  */

trait GameObjectProducer {
  def makePacman(world: World) : PacmanEntity
}

object GameObjectProduce extends GameObjectProducer{

  def makePacman(world: World): PacmanEntity = {
    val bodyDef = new BodyDef
    bodyDef.`type` = BodyDef.BodyType.KinematicBody
    bodyDef.position.set(0, 0)
    val body = world.createBody(bodyDef)

    val sprite = new Sprite(CharacterTextures.upFacing)

    val polygonShape = new PolygonShape()
    polygonShape.setAsBox(sprite.getWidth/2, sprite.getHeight/2)

    val fixtureDef = new FixtureDef
    fixtureDef.shape = polygonShape
    fixtureDef.density = 0.0f
    fixtureDef.restitution = 0.1f

    body.createFixture(fixtureDef)
    polygonShape.dispose()

    val pacmanBody = new PacmanBody(body)
    val pacmanSprite = new PacmanSprite(sprite)
    EventManager.addListener[InputStateListener](pacmanBody, classOf[StatePressedEvent])
    EventManager.addListener[InputStateListener](pacmanBody, classOf[StateReleasedEvent])
    EventManager.addListener[InputStateListener](pacmanSprite, classOf[StatePressedEvent])
    EventManager.addListener[InputStateListener](pacmanSprite, classOf[StateReleasedEvent])

    new PacmanEntity(pacmanBody, pacmanSprite)
  }
}
