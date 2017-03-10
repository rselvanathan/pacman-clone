package com.romeshselvan.pacman.engine.input

import com.romeshselvan.pacman.engine.eventManager.EventManager
import com.romeshselvan.pacman.engine.input.events.{InputStateListener, StatePressedEvent, StateReleasedEvent}
import org.mockito.Mockito._
import org.scalatest.mockito.MockitoSugar
import org.scalatest.{BeforeAndAfterEach, FlatSpec, Matchers}

/**
  * @author Romesh Selvan
  */
class InputStateListenerImpl(var state : State = State("Test"),
                             var keyUp : Boolean = false,
                             var keyDown : Boolean = false) extends InputStateListener {

  override def onStatePressed(state: State): Unit = {
    this.state = state
    keyDown = true
  }

  override def onStateReleased(state: State): Unit = {
    this.state = state
    keyUp = true
  }
}

class InputHandlerTest extends FlatSpec with Matchers with BeforeAndAfterEach with MockitoSugar {

  val contextManager: InputContextManager = mock[InputContextManager]
  val inputHandler = new InputHandler(contextManager, EventManager)
  var inputStateListener : InputStateListenerImpl = _

  val expectedState = State("MOVE_FORWARD")

  override def beforeEach: Unit = {
    inputStateListener = new InputStateListenerImpl()
    EventManager.addListener[InputStateListener](inputStateListener, classOf[StatePressedEvent])
    EventManager.addListener[InputStateListener](inputStateListener, classOf[StateReleasedEvent])
  }

  override def afterEach(): Unit = {
    EventManager.clearListenerMap()
  }

  "Handler" should "send a state pressed event to its listener when the event has been fired" in {
    inputStateListener.keyDown should be (false)
    when(contextManager.getState(3)).thenReturn(Some(expectedState))
    inputHandler.keyDown(3)
    inputStateListener.state.stateId should be (expectedState.stateId)
    inputStateListener.keyDown should be (true)
  }

  "Handler" should "send a state released event to its listener when the event has been fired" in {
    inputStateListener.keyUp should be (false)
    when(contextManager.getState(3)).thenReturn(Some(expectedState))
    inputHandler.keyUp(3)
    inputStateListener.state.stateId should be (expectedState.stateId)
    inputStateListener.keyUp should be (true)
  }

  "Handler" should "do nothing if key press down key code was not found" in {
    when(contextManager.getState(3)).thenReturn(None)
    inputHandler.keyDown(3)
    inputStateListener.state.stateId should be ("Test")
    inputStateListener.keyDown should be (false)
  }

  "Handler" should "do nothing if key press up key code was not found" in {
    when(contextManager.getState(3)).thenReturn(None)
    inputHandler.keyUp(3)
    inputStateListener.state.stateId should be ("Test")
    inputStateListener.keyUp should be (false)
  }
}
