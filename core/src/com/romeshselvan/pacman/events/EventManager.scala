package com.romeshselvan.pacman.events

import scala.collection.mutable

/**
  * @author Romesh Selvan
  */
trait EventManager {
  def addListener[T](listener : T, clazz : Class[_ <: Event[_]]) : Unit
  def removeListener[T](listener : T, clazz : Class[_ <: Event[_]]) : Unit
  def notifyListeners[T](event : Event[T]): Unit
  def getListenerMap: Map[Class[_ <: Event[_]], List[_]]
  def clearListenerMap() : Unit
}

object EventManager extends EventManager {

  private val listenerMap : mutable.HashMap[Class[_ <: Event[_]], List[_]] = new mutable.HashMap()

  def addListener[T](listener : T, clazz : Class[_ <: Event[_]]) : Unit = {
    var list = listenerMap.getOrElse(clazz, List())
    list = listener :: list
    listenerMap += (clazz -> list)
  }

  def removeListener[T](listener : T, clazz : Class[_ <: Event[_]]) : Unit = {
    if(listenerMap.contains(clazz)) {
      val list = listenerMap(clazz)
      if(list.nonEmpty) {
        listenerMap += (clazz -> list.filter(!_.equals(listener)))
      }
    }
  }

  def notifyListeners[T](event : Event[T]): Unit = {
    val clazz : Class[Event[T]] = event.getClass.asInstanceOf[Class[Event[T]]]
    val list : List[T] = listenerMap.getOrElse(clazz, throw new IllegalArgumentException("Event Type not found")).map(_.asInstanceOf[T])
    list.foreach(event.notify)
  }

  def clearListenerMap(): Unit = listenerMap.clear()


  def getListenerMap: Map[Class[_ <: Event[_]], List[_]] = listenerMap.toMap
}
