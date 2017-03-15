package com.romeshselvan.pacman.textures

import com.badlogic.gdx.graphics.g2d.TextureRegion

/**
  * @author Romesh Selvan
  */
object CharacterTextures {
  private val dimension = 64
  val downfacing = new TextureRegion(AssetManager.characterSheet,   0,           0, dimension, dimension)
  val upFacing = new TextureRegion(AssetManager.characterSheet,     0, dimension*3, dimension, dimension)
  val rightFacing = new TextureRegion(AssetManager.characterSheet,  0, dimension*2, dimension, dimension)
  val leftFacing = new TextureRegion(AssetManager.characterSheet,   0,   dimension, dimension, dimension)

  def dispose(): Unit = {
    upFacing.getTexture.dispose()
  }
}
