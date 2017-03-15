package com.romeshselvan.pacman.textures

import com.badlogic.gdx.graphics.Texture

/**
  * @author Romesh Selvan
  */
object AssetManager {

  val characterSheet : Texture = new Texture("death_scythe.png")

  def dispose(): Unit = {
    characterSheet.dispose()
  }
}
