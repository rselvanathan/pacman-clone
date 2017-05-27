package com.romeshselvan.pacman

import box2dLight.{PointLight, RayHandler}
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.{Color, OrthographicCamera}
import com.badlogic.gdx.maps.MapObjects
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.maps.tiled.{TiledMap, TmxMapLoader}
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

  private var tileMap: TiledMap = _
  private var tiledMapRenderer : OrthogonalTiledMapRenderer = _

  private val debugRenderer = new Box2DDebugRenderer()

  private val rayHandler : RayHandler = new RayHandler(world)

  def init(): Unit = {
    world.setContactListener(CollisionHandler)
    tileMap = new TmxMapLoader().load("level1.tmx")
    tiledMapRenderer = new OrthogonalTiledMapRenderer(tileMap)

    gameObjectProducer.loadWalls(world, tileMap)

    val objectPosition : MapObjects = tileMap.getLayers.get("ObjectPositions").getObjects
    val playerStart = objectPosition.get("start")

    val player = gameObjectProducer.makePacman(world,
      playerStart.getProperties.get("x", classOf[Float]),
      playerStart.getProperties.get("y", classOf[Float]),
      camera)
    entityList += player

    objectPosition.forEach(obj => {
      obj.getProperties.get("type", classOf[String]) match {
        case "knight" =>
          entityList += gameObjectProducer.makeKnight(world,
            obj.getProperties.get("x", classOf[Float]),
            obj.getProperties.get("y", classOf[Float]),
          rayHandler, player.body)
        case "wallLight" =>
          val wallLight = new PointLight(rayHandler, 200, Color.YELLOW, 350, obj.getProperties.get("x", classOf[Float]), obj.getProperties.get("y", classOf[Float]))
          wallLight.setSoftnessLength(100)
        case _ =>
      }
    })

    rayHandler.setAmbientLight(0, 0, 0, 0.4f)
  }

  def update(delta:Float): Unit = {
    world.step(1f/60f, 6, 2)
    entityList.foreach(_.update(delta))
    camera.update()
  }

  def render(delta: Float, batch : Batch): Unit = {
    tiledMapRenderer.setView(camera)
    tiledMapRenderer.render()

    batch.setProjectionMatrix(camera.combined)

    entityList.foreach(_.render(delta, batch))

    rayHandler.setCombinedMatrix(camera)
    rayHandler.updateAndRender()
    debugRenderer.render(world, camera.combined)
  }

  def dispose(): Unit = {
    entityList.foreach(_.dispose)
    tileMap.dispose()
    tiledMapRenderer.dispose()
    rayHandler.dispose()
  }
}
