package com.romeshselvan.pacman.inputContexts

import com.romeshselvan.pacman.engine.input.Action

/**
  * @author Romesh Selvan
  */
object MenuActions {

 abstract class MenuAction(id : String) extends Action(id)

  object MENU_UP extends MenuAction("MENU_UP")
  object MENU_DOWN extends MenuAction("MENU_DOWN")
  object SELECT extends MenuAction("SELECT")
}
