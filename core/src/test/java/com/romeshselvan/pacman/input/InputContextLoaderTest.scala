package com.romeshselvan.pacman.input

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys
import org.scalatest.{FlatSpec, Matchers}

/**
  * @author Romesh Selvan
  */
class InputContextLoaderTest extends FlatSpec with Matchers {

  val inputContextLoader = new InputContextLoader

  "Loader" should "load the json object passed in correctly producing a list of contexts" in {
    def menuContext = new InputContext("menu", Map(19 -> Action("MENU_UP")), Map.empty)
    def gameContext = new InputContext("game", Map(131 -> Action("PAUSE")), Map(19 -> State("MOVE_UP"), 20 -> State("MOVE_DOWN")))
    val expectedContexts = List(menuContext, gameContext)

    val inputContexts = inputContextLoader.loadInputContexts(expectedJson)

    inputContexts should contain theSameElementsAs expectedContexts
  }

  private def expectedJson =
    """
      |{
      |"contexts" :
      |  [
      |    {
      |      "contextName" : "menu",
      |      "actions" : [
      |        {
      |          "key" : 19,
      |          "action" : "MENU_UP"
      |        }
      |      ],
      |      "states" : []
      |    },
      |    {
      |      "contextName" : "game",
      |      "actions" : [
      |        {
      |          "key" : 131,
      |          "action" : "PAUSE"
      |        }
      |      ],
      |      "states" : [
      |        {
      |          "key" : 19,
      |          "state" : "MOVE_UP"
      |        },
      |        {
      |          "key" : 20,
      |          "state" : "MOVE_DOWN"
      |        }
      |      ]
      |    }
      |  ]
      |}
    """.stripMargin
}
