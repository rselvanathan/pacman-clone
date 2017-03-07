package com.romeshselvan.pacman.input

import org.json4s.native.JsonMethods._
import com.badlogic.gdx.Gdx
import org.json4s.JsonAST.JArray

/**
  * @author Romesh Selvan
  */
class InputContextLoader {

  def loadInputContexts(fileName : String) : List[InputContext] = {
    val rawJsonString = Gdx.files.internal(fileName).read().toString
    val json = parse(rawJsonString)
    val contextJson : JArray = (json \ "contexts").asInstanceOf[JArray];
    contextJson.arr.map(_.asInstanceOf)
    null
  }
}
