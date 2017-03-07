package com.romeshselvan.pacman.inputContexts

import com.romeshselvan.pacman.input.Action

/**
  * @author Romesh Selvan
  */
object MenuActions {

 abstract class MenuAction(id : String) extends Action(id)

  object MENU_UP extends MenuAction("MENU_UP")
  object MENU_DOWN extends MenuAction("MENU_DOWN")
  object MENU_RIGHT extends MenuAction("MENU_RIGHT")
  object MENU_LEFT extends MenuAction("MENU_LEFT")
  object SELECT extends MenuAction("SELECT")
}
