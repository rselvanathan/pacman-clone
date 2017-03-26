package com.romeshselvan.pacman.engine.entities

/**
  * @author Romesh Selvan
  */
abstract class EntityType(val id : Int) {
  override def equals(obj: scala.Any): Boolean = {
    obj match {
      case entityType: EntityType => entityType.id == id
      case _ => false
    }
  }
}
