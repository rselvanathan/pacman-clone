package com.romeshselvan.pacman.input

/**
  * @author Romesh Selvan
  */
class InputContextManager(inputContext: Seq[InputContext]) {

  private var activeContext : InputContext = inputContext.last

  def changeContext(contextName : String) : Unit = {
    val maybeContext = inputContext.find(_.contextName == contextName)
    activeContext = maybeContext.getOrElse(throw new IllegalArgumentException(s"Context $contextName not found"))
  }

  def getAction(key : Int): Option[Action] = activeContext.getAction(key)

  def getState(key : Int): Option[State] = activeContext.getState(key)

  def getActiveContext: InputContext = activeContext
}
