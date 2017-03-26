package com.romeshselvan.pacman

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d._
import com.romeshselvan.pacman.engine.collision.CollisionHandler
import com.romeshselvan.pacman.engine.entities.Entity
import com.romeshselvan.pacman.producers.GameObjectProducer

import scala.collection.mutable.ListBuffer

/**
  * @author Romesh Selvan
  */
class GameWorld(gameObjectProducer: GameObjectProducer) {

  private val entityList : ListBuffer[Entity] = new ListBuffer
  private val world = new World(new Vector2(0.0f, 0.0f), false)
  private val camera = new OrthographicCamera(Gdx.graphics.getWidth, Gdx.graphics.getHeight)

  private val debugRenderer = new Box2DDebugRenderer()

  def init = {
    entityList += gameObjectProducer.makePacman(world, camera)
    entityList += gameObjectProducer.makeWall(world)

    world.setContactListener(CollisionHandler)
  }

  def update(delta:Float) = {
    world.step(1f/60f, 6, 2)
    entityList.foreach(_.update(delta))
    camera.update()
  }

  def render(delta: Float, batch : Batch) = {
    batch.setProjectionMatrix(camera.combined)
    entityList.foreach(_.render(delta, batch))
    debugRenderer.render(world, camera.combined)
  }

  def dispose = entityList.foreach(_.dispose)
}
