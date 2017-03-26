package com.romeshselvan.pacman.producers

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.physics.box2d.{BodyDef, FixtureDef, PolygonShape, World}
import com.romeshselvan.pacman.engine.eventManager.EventManager
import com.romeshselvan.pacman.engine.input.events.{StatePressedEvent, StateReleasedEvent}
import com.romeshselvan.pacman.engine.input.listeners.InputStateListener
import com.romeshselvan.pacman.entities.{PacmanEntity, WallEntity}
import com.romeshselvan.pacman.entities.bodies.{PacmanBody, WallBody}
import com.romeshselvan.pacman.entities.sprites.PacmanSprite
import com.romeshselvan.pacman.textures.CharacterTextures

/**
  * @author Romesh Selvan
  */

trait GameObjectProducer {
  def makePacman(world: World, camera : OrthographicCamera) : PacmanEntity
  def makeWall(world: World) : WallEntity
}

object GameObjectProducer extends GameObjectProducer{

  def makePacman(world: World, camera : OrthographicCamera): PacmanEntity = {
    val bodyDef = new BodyDef
    bodyDef.`type` = BodyDef.BodyType.DynamicBody
    bodyDef.position.set(0, 0)
    val body = world.createBody(bodyDef)

    val sprite = new Sprite(CharacterTextures.upFacingSet.items(0))

    val polygonShape = new PolygonShape()
    polygonShape.setAsBox(sprite.getWidth/2, sprite.getHeight/2)

    val fixtureDef = new FixtureDef
    fixtureDef.shape = polygonShape
    fixtureDef.density = 1.0f
    fixtureDef.restitution = 0.1f

    body.createFixture(fixtureDef)
    polygonShape.dispose()

    val pacmanBody = new PacmanBody(body, camera)
    val pacmanSprite = new PacmanSprite(sprite)
    EventManager.addListener[InputStateListener](pacmanBody, classOf[StatePressedEvent])
    EventManager.addListener[InputStateListener](pacmanBody, classOf[StateReleasedEvent])
    EventManager.addListener[InputStateListener](pacmanSprite, classOf[StatePressedEvent])
    EventManager.addListener[InputStateListener](pacmanSprite, classOf[StateReleasedEvent])

    new PacmanEntity(pacmanBody, pacmanSprite)
  }

  def makeWall(world: World): WallEntity = {
    val bodyDef = new BodyDef
    bodyDef.`type` = BodyDef.BodyType.StaticBody
    bodyDef.position.set(0, 100)
    val body = world.createBody(bodyDef)

    val shape = new PolygonShape
    shape.setAsBox(100, 10)

    val fixtureDef = new FixtureDef
    fixtureDef.shape = shape
    fixtureDef.density = 1.0f

    body.createFixture(fixtureDef)
    shape.dispose()

    new WallEntity(new WallBody(body), null)
  }
}
