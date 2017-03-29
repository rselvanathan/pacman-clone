package com.romeshselvan.pacman.textures

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Array

/**
  * @author Romesh Selvan
  */
object KnightTextures {
  private val width = 48
  private val height = 72

  val downFacingSet : Array[TextureRegion] = new Array[TextureRegion]((0 to 3).map( int => new TextureRegion(AssetManager.knightSheet, width*int, 0, width, height)).toArray)
  val upFacingSet : Array[TextureRegion] = new Array[TextureRegion]((0 to 3).map( int => new TextureRegion(AssetManager.knightSheet, width*int, height*3, width, height)).toArray)
  val rightFacingSet : Array[TextureRegion] = new Array[TextureRegion]((0 to 3).map( int => new TextureRegion(AssetManager.knightSheet, width*int, height*2, width, height)).toArray)
  val leftFacingSet : Array[TextureRegion] = new Array[TextureRegion]((0 to 3).map( int => new TextureRegion(AssetManager.knightSheet, width*int, height, width, height)).toArray)

  def dispose(): Unit = {
    downFacingSet.items.foreach(_.getTexture.dispose())
    upFacingSet.items.foreach(_.getTexture.dispose())
    rightFacingSet.items.foreach(_.getTexture.dispose())
    leftFacingSet.items.foreach(_.getTexture.dispose())
  }
}
