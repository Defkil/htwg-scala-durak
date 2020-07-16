package de.htwg.se.durak.aview

import de.htwg.se.durak.controller.controllerComponent.ControllerInterface
import de.htwg.se.durak.model.gameElementsComponent.CardInterface
import de.htwg.se.durak.model.gameElementsComponent.gameElementsBaseImpl.Card
import javafx.event.{ActionEvent, EventHandler}
import javafx.scene.input.MouseEvent
import scalafx.geometry.Pos
import scalafx.scene.Scene
import scalafx.scene.control.Button
import scalafx.scene.image.ImageView
import scalafx.scene.layout.{HBox, VBox}
import scalafx.scene.paint.Color
import scalafx.scene.text.Text

class Board(f: GUI, controller: ControllerInterface) extends Scene {
  val CARD_HEIGHT = 160
  val CARD_WIDTH = 120
  val gameData = controller.roundStack(controller.roundStack.size - 1)
  val turnData = gameData.turnData.get
  val roundData = gameData.roundData
  val currentplayer = turnData.currentPlayer
  fill = Color.White
  content = new HBox {
    children = Seq(new VBox {
      def position() = if(roundData.siteID == 10) "Angriff: " else if(roundData.siteID == 12) "Verteidigung: " else ""
      children = Seq(new Text(position() + turnData.players(turnData.currentPlayer)) {
        fill = Color.Blue
      },
        new Text("Field"),
        field(),
        new Text("Cardstack"),
        cardStack(),
      )
    },
      //Trumpffarbe + Button Fertig und nehmen
      new VBox {
        alignment = Pos.Center
        children = Seq(
          new Text("Trumpf"),
          getCard(new Card( 3, gameData.turnData.get.trump)),
          new Button("Hallo, Hi") {
            onAction = (t: ActionEvent) => println("Hallo, Hi")
          },
          new Button("Undo") {
            onAction = (t: ActionEvent) => controller.undo
          }
        )
      }
    )
  }

  private def getCard(c:CardInterface) = new ImageView(
      "de/htwg/se/durak/aview/CardLogos/"
        + c.symbol + "-" + c.rank + ".png") {
    fitHeight = CARD_HEIGHT
    fitWidth = CARD_WIDTH
  }

  def cardStack():HBox = new HBox {
    val stack = turnData.playerDecks(currentplayer)
    var s = Seq[ImageView]()
    for(i <- 0 until stack.size){
      val c = getCard(stack.deck(i))
      s = s :+ c
      c.onMouseClicked = (t: MouseEvent) => onCardChosen(i)
    }
    children = s
  }

  def field():HBox = new HBox {
    val fieldStack = gameData.turnData.get.field.cardDeck.deck
    def cardTuple(c1:CardInterface, c2:CardInterface):VBox =new VBox {
      children = Seq(getCard(c1), getCard(c2))
    }

    val numberOfCards = fieldStack.size
    val numberOfFullBoxes = numberOfCards/2
    val lastBox = Math.floorMod(numberOfCards, 2)
    var s = Seq[VBox]()
    for(i <- 0 until numberOfFullBoxes) s = s :+ cardTuple(fieldStack(2 * i), fieldStack(2 * i + 1))

    if(lastBox == 1) s = s :+ new VBox {
      children = getCard(fieldStack(fieldStack.size - 1))
    }

    children = s
  }
  private def onCardChosen(i:Int):Unit = {
    controller.solve("0")
    f.input = i.toString
  }
}
