package com.romeshselvan.pacman.engine.input

import org.json4s.JsonAST.JArray
import org.json4s.native.JsonMethods._
import org.json4s.{JInt, JString}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

/**
  * @author Romesh Selvan
  */
trait InputContextLoader {
  def loadInputContexts(rawJson : String) : Seq[InputContext]
}

object InputContextLoader extends InputContextLoader {

  def loadInputContexts(rawJson : String) : Seq[InputContext] = {
    val json = parse(rawJson)
    val contextJson : JArray = (json \ "contexts").asInstanceOf[JArray]
    val tempListBuffer = new ListBuffer[InputContext]
    contextJson.arr.foreach(jValue =>{
      val contextName = (jValue \ "contextName").asInstanceOf[JString].s
      val tempMapAction = new mutable.HashMap[Int, Action]()
      val tempMapState = new mutable.HashMap[Int, State]()
      (jValue \ "actions").asInstanceOf[JArray].arr.map(jValue => {
        val key = (jValue \ "key").asInstanceOf[JInt].num.intValue()
        val action = (jValue \ "action").asInstanceOf[JString].s
        tempMapAction += (key -> Action(action))
      })
      (jValue \ "states").asInstanceOf[JArray].arr.map(jValue => {
        val key = (jValue \ "key").asInstanceOf[JInt].num.intValue()
        val state = (jValue \ "state").asInstanceOf[JString].s
        tempMapState += (key -> State(state))
      })
      tempListBuffer += InputContext(contextName, tempMapAction.toMap, tempMapState.toMap)
    })
    tempListBuffer.toList
  }
}
