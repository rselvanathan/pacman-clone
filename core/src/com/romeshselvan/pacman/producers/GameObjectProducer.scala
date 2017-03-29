package com.romeshselvan.pacman.producers

import box2dLight.PointLight
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.physics.box2d.{BodyDef, FixtureDef, PolygonShape, World}
import com.romeshselvan.pacman.engine.eventManager.EventManager
import com.romeshselvan.pacman.engine.input.events.{StatePressedEvent, StateReleasedEvent}
import com.romeshselvan.pacman.engine.input.listeners.InputStateListener
import com.romeshselvan.pacman.entities.bodies.{PacmanBody, WallBody}
import com.romeshselvan.pacman.entities.sprites.PacmanSprite
import com.romeshselvan.pacman.entities.{PacmanEntity, WallEntity}
import com.romeshselvan.pacman.textures.CharacterTextures

/**
  * @author Romesh Selvan
  */

trait GameObjectProducer {
  def makePacman(world: World, xPos: Float, yPos: Float, camera : OrthographicCamera, light : PointLight) : PacmanEntity
  def makeWall(world: World, xPos: Float, yPos: Float, width: Float, height: Float) : WallEntity

  def loadWalls(world: World, tiledMap: TiledMap) : Unit
}

object GameObjectProducer extends GameObjectProducer{

  def makePacman(world: World, xPos: Float, yPos: Float, camera : OrthographicCamera, light : PointLight): PacmanEntity = {
    val bodyDef = new BodyDef
    bodyDef.`type` = BodyDef.BodyType.DynamicBody
    bodyDef.position.set(xPos, yPos)
    bodyDef.fixedRotation = true
    val body = world.createBody(bodyDef)

    val sprite = new Sprite(CharacterTextures.upFacingSet.items(0))

    val polygonShape = new PolygonShape()
    polygonShape.setAsBox(sprite.getWidth/3, sprite.getHeight/2)

    val fixtureDef = new FixtureDef
    fixtureDef.shape = polygonShape
    fixtureDef.density = 1.0f
    fixtureDef.restitution = 0.0f

    body.createFixture(fixtureDef)
    polygonShape.dispose()

    val pacmanBody = new PacmanBody(body, camera, light)
    val pacmanSprite = new PacmanSprite(sprite)
    EventManager.addListener[InputStateListener](pacmanBody, classOf[StatePressedEvent])
    EventManager.addListener[InputStateListener](pacmanBody, classOf[StateReleasedEvent])
    EventManager.addListener[InputStateListener](pacmanSprite, classOf[StatePressedEvent])
    EventManager.addListener[InputStateListener](pacmanSprite, classOf[StateReleasedEvent])

    new PacmanEntity(pacmanBody, pacmanSprite)
  }

  def makeWall(world: World, xPos: Float, yPos: Float, width: Float, height: Float): WallEntity = {
    val bodyDef = new BodyDef
    bodyDef.`type` = BodyDef.BodyType.StaticBody
    bodyDef.position.set(xPos, yPos)
    val body = world.createBody(bodyDef)

    val shape = new PolygonShape
    shape.setAsBox(width, height)

    val fixtureDef = new FixtureDef
    fixtureDef.shape = shape
    fixtureDef.density = 1.0f

    body.createFixture(fixtureDef)
    shape.dispose()

    new WallEntity(new WallBody(body), null)
  }

  override def loadWalls(world: World, tiledMap: TiledMap): Unit = {
    tiledMap.getLayers.get("CollisionObjects").getObjects.forEach(ob => {
      if(ob.getProperties.get("type", classOf[String]) == "wall")
      {
        val x = ob.getProperties.get("x", classOf[Float])
        val y = ob.getProperties.get("y", classOf[Float])
        val width = ob.getProperties.get("width", classOf[Float]) / 2
        val height = ob.getProperties.get("height", classOf[Float]) / 2
        makeWall(world, x + width, y + height, width, height)
      }
    })
  }
}
