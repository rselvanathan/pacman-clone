package com.romeshselvan.pacman.entities.bodies

import com.badlogic.gdx.physics.box2d.Body

/**
  * @author Romesh Selvan
  */
class WallBody(body : Body) extends EntityBody {
  body.setUserData("wall")

  override def update(delta: Float): Unit = {}
}
