package com.romeshselvan.pacman.engine.eventManager

import com.romeshselvan.pacman.engine.eventManager.{Event, EventManager}
import org.scalatest.{FlatSpec, Matchers}

/**
  * @author Romesh Selvan
  */
trait EventListenerTest {
  def onListen(value : String)
}

case class TestEvent() extends Event[EventListenerTest] {
  override def notify(listener: EventListenerTest): Unit = {
    listener.onListen("Test Success")
  }
}

case class EventListenerOne(var result : String = "nothing") extends EventListenerTest {
  override def onListen(value: String): Unit = {
    result = value
  }
}

case class EventListenerTwo(var result : String = "nothing") extends EventListenerTest {
  override def onListen(value: String): Unit = {
    result = value
  }
}

class EventManagerTest extends FlatSpec with Matchers {

   var eventManager = EventManager

  "Manager" should "add event handler when no other handler is available" in {
    before()
    val handlerTest = EventListenerOne()
    eventManager.getListenerMap shouldBe empty
    eventManager.addListener[EventListenerTest](handlerTest, classOf[TestEvent])
    eventManager.getListenerMap shouldNot be (empty)
    val list = eventManager.getListenerMap(classOf[TestEvent])
    list should contain (handlerTest)
  }

  "Manager" should "add event handler to an existing list if the Listener class is found" in {
    before()
    val handlerTest = EventListenerOne()
    val handlerTestTwo = EventListenerTwo()
    eventManager.addListener[EventListenerTest](handlerTest, classOf[TestEvent])
    eventManager.getListenerMap shouldNot be (empty)
    eventManager.addListener[EventListenerTest](handlerTestTwo, classOf[TestEvent])
    eventManager.getListenerMap.size should be (1)
    eventManager.getListenerMap(classOf[TestEvent]) should contain allOf (handlerTest, handlerTestTwo)
  }

  "Manager" should "send the event to the event listener if the correct event is sent" in {
    before()
    val handlerTest = EventListenerOne()
    eventManager.addListener[EventListenerTest](handlerTest, classOf[TestEvent] )
    handlerTest.result should be ("nothing")
    eventManager.notifyListeners[EventListenerTest](TestEvent())
    handlerTest.result should be ("Test Success")
  }

  "Manager" should "throw an exception when trying to send a Notification for a game event which does not exist" in {
    before()
    val handlerTest = EventListenerOne()
    handlerTest.result should be ("nothing")
    assertThrows[IllegalArgumentException] {
      eventManager.notifyListeners[EventListenerTest](TestEvent())
    }
  }

  "Manager" should "remove a listener from the list when it is found" in {
    before()
    val handlerTest = EventListenerOne()
    val handlerTestTwo = EventListenerTwo()
    val handlerTestThree = EventListenerOne("three")
    eventManager.addListener[EventListenerTest](handlerTest, classOf[TestEvent])
    eventManager.addListener[EventListenerTest](handlerTestTwo, classOf[TestEvent])
    eventManager.addListener[EventListenerTest](handlerTestThree, classOf[TestEvent])
    eventManager.getListenerMap.size should be (1)
    eventManager.getListenerMap(classOf[TestEvent]) should contain allOf(handlerTest, handlerTestTwo, handlerTestThree)

    eventManager.removeListener[EventListenerTest](handlerTestTwo, classOf[TestEvent])
    eventManager.getListenerMap(classOf[TestEvent]) should contain allOf (handlerTest, handlerTestThree)
  }

  "Manager" should "do nothing if the list for the clazz is empty when trying to remove a listener" in {
    before()
    val handlerTest = EventListenerOne()
    val handlerTestTwo = EventListenerTwo()
    eventManager.addListener[EventListenerTest](handlerTest, classOf[TestEvent])
    eventManager.getListenerMap.size should be (1)
    eventManager.getListenerMap(classOf[TestEvent]) should contain (handlerTest)
    eventManager.removeListener[EventListenerTest](handlerTest, classOf[TestEvent])
    eventManager.getListenerMap(classOf[TestEvent]) should be (empty)
    eventManager.removeListener[EventListenerTest](handlerTestTwo, classOf[TestEvent])
  }

  "Manager" should "do nothing if the list clazz does not exist in the map when trying to remove a listener" in {
    before()
    eventManager.getListenerMap should be (empty)
    eventManager.removeListener[EventListenerTest](EventListenerOne(), classOf[TestEvent])
  }

  private def before() = eventManager.clearListenerMap()
}
