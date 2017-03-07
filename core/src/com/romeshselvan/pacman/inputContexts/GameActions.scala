package com.romeshselvan.pacman.inputContexts

import com.romeshselvan.pacman.input.Action

/**
  * @author Romesh Selvan
  */
object GameActions {

  abstract class GameAction(id : String) extends Action(id)

  object PAUSE extends GameAction("PAUSE")
}