package com.romeshselvan.pacman.producers

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.physics.box2d.{BodyDef, FixtureDef, PolygonShape, World}
import com.romeshselvan.pacman.engine.eventManager.EventManager
import com.romeshselvan.pacman.engine.input.events.{StatePressedEvent, StateReleasedEvent}
import com.romeshselvan.pacman.engine.input.listeners.InputStateListener
import com.romeshselvan.pacman.entities.Pacman

/**
  * @author Romesh Selvan
  */

trait GameObjectProducer {
  def makePacman(world: World) : Pacman
}

object GameObjectProduce extends GameObjectProducer{

  def makePacman(world: World): Pacman = {
    val bodyDef = new BodyDef
    bodyDef.`type` = BodyDef.BodyType.KinematicBody
    val sprite = new Sprite(new Texture("badlogic.jpg"))
    bodyDef.position.set(0-sprite.getWidth*2, 0-sprite.getHeight)
    val body = world.createBody(bodyDef)

    val polygonShape = new PolygonShape()
    polygonShape.setAsBox(sprite.getWidth/2, sprite.getHeight/2)

    val fixtureDef = new FixtureDef
    fixtureDef.shape = polygonShape
    fixtureDef.density = 0.0f
    fixtureDef.restitution = 0.1f

    body.createFixture(fixtureDef)
    polygonShape.dispose()

    val pacman = new Pacman(body, sprite)
    EventManager.addListener[InputStateListener](pacman, classOf[StatePressedEvent])
    EventManager.addListener[InputStateListener](pacman, classOf[StateReleasedEvent])
    pacman
  }
}
