package com.romeshselvan.pacman

import box2dLight.{PointLight, RayHandler}
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.{Color, OrthographicCamera}
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.maps.MapObject
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

//  RayHandler.useDiffuseLight(true)
  private val rayHandler : RayHandler = new RayHandler(world)
  private val playerPointLight : PointLight = new PointLight(rayHandler, 100)

  def init(): Unit = {
    tileMap = new TmxMapLoader().load("level1.tmx")
    tiledMapRenderer = new OrthogonalTiledMapRenderer(tileMap)

    gameObjectProducer.loadWalls(world, tileMap)

    val mapObject = tileMap.getLayers.get("ObjectPositions").getObjects.get("start")

    entityList += gameObjectProducer.makePacman(world,
      mapObject.getProperties.get("x", classOf[Float]),
      mapObject.getProperties.get("y", classOf[Float]),
      camera, playerPointLight)

    world.setContactListener(CollisionHandler)

    rayHandler.setAmbientLight(0, 0, 0, 0.2f)
    tileMap.getLayers.get("ObjectPositions").getObjects.forEach(mapObject => {
      if(mapObject.getProperties.get("type", classOf[String]) == "wallLight") {
        new PointLight(rayHandler, 100, Color.YELLOW, 500, mapObject.getProperties.get("x", classOf[Float]), mapObject.getProperties.get("y", classOf[Float]))
      }
    })
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
    //debugRenderer.render(world, camera.combined)
  }

  def dispose(): Unit = {
    entityList.foreach(_.dispose)
    tileMap.dispose()
    tiledMapRenderer.dispose()
    rayHandler.dispose()
  }
}
