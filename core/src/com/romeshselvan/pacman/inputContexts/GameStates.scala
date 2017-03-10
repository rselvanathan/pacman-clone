package com.romeshselvan.pacman.inputContexts

import com.romeshselvan.pacman.engine.input.State

/**
  * @author Romesh Selvan
  */
object GameStates {

  abstract class GameState(id : String) extends State(id)

  object MOVE_UP extends GameState("MOVE_UP")
  object MOVE_DOWN extends GameState("MOVE_DOWN")
  object MOVE_LEFT extends GameState("MOVE_LEFT")
  object MOVE_RIGHT extends GameState("MOVE_RIGHT")
}
