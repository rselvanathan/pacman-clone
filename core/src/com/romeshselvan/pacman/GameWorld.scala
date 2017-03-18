package com.romeshselvan.pacman

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.{Box2DDebugRenderer, World}
import com.romeshselvan.pacman.entities.Entity
import com.romeshselvan.pacman.producers.GameObjectProducer

import scala.collection.mutable.ListBuffer

/**
  * @author Romesh Selvan
  */
class GameWorld(gameObjectProducer: GameObjectProducer) {

  private val entityList : ListBuffer[Entity] = new ListBuffer
  private val world = new World(new Vector2(0.0f, 0.0f), false)

  private val debugRenderer = new Box2DDebugRenderer()
  private val debugCamera = new OrthographicCamera(Gdx.graphics.getWidth, Gdx.graphics.getHeight)

  def init = {
    val pacman = gameObjectProducer.makePacman(world)
    entityList += pacman
  }

  def update(delta:Float) = {
    entityList.foreach(_.update(delta))
    world.step(1f/60f, 6, 2)
  }

  def render(delta: Float, batch : Batch) = {
    entityList.foreach(_.render(delta, batch))
    debugRenderer.render(world, debugCamera.combined)
  }

  def dispose = entityList.foreach(_.dispose)
}
