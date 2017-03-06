package com.romeshselvan.pacman.producers

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.physics.box2d.{BodyDef, World}
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
    bodyDef.position.set(0, 0)
    val sprite = new Sprite(new Texture("badlogic.jpg"))
    val body = world.createBody(bodyDef)
    new Pacman(body, sprite)
  }
}
