package com.romeshselvan.pacman.textures

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.Array

/**
  * @author Romesh Selvan
  */
object CharacterTextures {
  private val dimension = 64
  val downFacingSet : Array[TextureRegion] = new Array[TextureRegion]((0 to 3).map( int => new TextureRegion(AssetManager.characterSheet, dimension*int, 0, dimension, dimension)).toArray)
  val upFacingSet : Array[TextureRegion] = new Array[TextureRegion]((0 to 3).map( int => new TextureRegion(AssetManager.characterSheet, dimension*int, dimension*3, dimension, dimension)).toArray)
  val rightFacingSet : Array[TextureRegion] = new Array[TextureRegion]((0 to 3).map( int => new TextureRegion(AssetManager.characterSheet, dimension*int, dimension*2, dimension, dimension)).toArray)
  val leftFacingSet : Array[TextureRegion] = new Array[TextureRegion]((0 to 3).map( int => new TextureRegion(AssetManager.characterSheet, dimension*int, dimension, dimension, dimension)).toArray)

  def dispose(): Unit = {
    downFacingSet.items.foreach(_.getTexture.dispose())
    upFacingSet.items.foreach(_.getTexture.dispose())
    rightFacingSet.items.foreach(_.getTexture.dispose())
    leftFacingSet.items.foreach(_.getTexture.dispose())
  }
}
