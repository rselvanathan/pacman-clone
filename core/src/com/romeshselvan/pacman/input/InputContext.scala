package com.romeshselvan.pacman.input

import com.badlogic.gdx.Input.Keys

/**
  * @author Romesh Selvan
  */
class InputContext(contextName : String,
                   keyToActionMap : Map[Keys, Action],
                   keyToStateMap : Map[Keys, State]) {

  def getAction(keys: Keys) : Option[Action] = keyToActionMap.get(keys)

  def getState(keys: Keys) : Option[State] = keyToStateMap.get(keys)
}
