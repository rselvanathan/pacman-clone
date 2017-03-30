package com.romeshselvan.pacman.entities

import com.romeshselvan.pacman.engine.entities.EntityType

/**
  * @author Romesh Selvan
  */
object EntityTypes {

  private var counter : Int = 0.toShort
  private def getId: Int = {
    counter += 1
    counter
  }

  object PLAYER extends EntityType(getId)
  object WALL extends EntityType(getId)
  object KNIGHT extends EntityType(getId)
}
