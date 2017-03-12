package com.romeshselvan.pacman

import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World
import com.romeshselvan.pacman.engine.eventManager.EventManager
import com.romeshselvan.pacman.entities.Entity
import com.romeshselvan.pacman.producers.GameObjectProducer

import scala.collection.mutable.ListBuffer

/**
  * @author Romesh Selvan
  */
class GameWorld(gameObjectProducer: GameObjectProducer) {

  private val entityList : ListBuffer[Entity] = new ListBuffer
  private val world = new World(new Vector2(0.0f, 0.0f), false)

  def init = {
    val pacman = gameObjectProducer.makePacman(world)
    entityList += pacman
  }

  def update(delta:Float) = {
    entityList.foreach(_.update(delta))
    world.step(1f/60f, 6, 2)
  }

  def render(batch : Batch) = entityList.foreach(_.render(batch))

  def dispose = entityList.foreach(_.dispose)
}
