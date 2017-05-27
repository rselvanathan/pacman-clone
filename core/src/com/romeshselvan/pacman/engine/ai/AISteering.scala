package com.romeshselvan.pacman.engine.ai

import com.badlogic.gdx.math.Vector2
import com.romeshselvan.pacman.engine.entities.EntityBody

/**
  * @author Romesh Selvan
  */
class AISteering(val aiBody : EntityBody) {

  def arrive(target : EntityBody, arriveDistance : Float, decelarationVal : Float) : Vector2 = {
    val directionVector = getDirectionVector(target.body.getPosition, aiBody.body.getPosition)
    val distance = directionVector.len()
    if(distance > arriveDistance) {
      var speed = distance/decelarationVal
      if(speed > aiBody.maxSpeed) speed = aiBody.maxSpeed
      return new Vector2(directionVector.x * speed/distance, directionVector.y * speed/distance)
    }
    new Vector2(0, 0)
  }

  private def getDirectionVector(targetVec : Vector2, fromVector : Vector2): Vector2 = {
    new Vector2(targetVec.x - fromVector.x, targetVec.y - fromVector.y)
  }
}
