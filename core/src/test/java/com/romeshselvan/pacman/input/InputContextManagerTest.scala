package com.romeshselvan.pacman.input

import org.scalatest.{FlatSpec, Matchers}

/**
  * @author Romesh Selvan
  */
class InputContextManagerTest extends FlatSpec with Matchers {

  private val GAME = "game"
  private val MENU = "menu"

  private val menuContext = defaultContext(MENU)
  private val gameContext = defaultContext(GAME)
  private var contextManager : InputContextManager = _

  "Manager" should "change context successfully when it finds the context name in the list" in {
    contextManager = new InputContextManager(List(gameContext, menuContext))
    contextManager.getActiveContext should be (menuContext)
    contextManager.changeContext(GAME)
    contextManager.getActiveContext should be (gameContext)
  }

  "Manager" should "throw IllegalArgument exception with expected message when context is not found" in {
    contextManager = new InputContextManager(List(gameContext, menuContext))
    contextManager.getActiveContext should be (menuContext)
    assertThrows[IllegalArgumentException] {
      contextManager.changeContext("RANDOM")
    }
  }

  def defaultContext(name : String) = InputContext(name, Map.empty, Map.empty)
}
