package com.romeshselvan.pacman.engine.input

/**
  * @author Romesh Selvan
  */
case class InputContext(contextName : String,
                   keyToActionMap : Map[Int, Action],
                   keyToStateMap : Map[Int, State]) {

  def getAction(keys: Int) : Option[Action] = keyToActionMap.get(keys)

  def getState(keys: Int) : Option[State] = keyToStateMap.get(keys)
}
