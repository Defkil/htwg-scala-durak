package de.htwg.se.durak.aview.gui.scenes

import de.htwg.se.durak.aview.gui.GUI
import de.htwg.se.durak.controller.controllerComponent.ControllerInterface
import de.htwg.se.durak.model.gameElementsComponent.CardDeckInterface
import de.htwg.se.durak.model.roundComponent.TurnDataInterface
import javafx.scene.input.MouseEvent
import scalafx.geometry.Insets
import scalafx.scene.Scene
import scalafx.scene.control.{Button, Label}
import scalafx.scene.image.ImageView
import scalafx.scene.layout.{BorderPane, HBox, VBox}

class PlayScene(controller: ControllerInterface) extends Scene {
  val turnData: TurnDataInterface = controller.turnData.get
  val CARD_HEIGHT = 160
  val CARD_WIDTH = 120

  // top info box
  val defendPlayer = new Label("Verteidiger: " + turnData.players(turnData.defendPlayer).toString)
  val currentPlayer = new Label("Aktueller Spieler: " + turnData.players(turnData.currentPlayer).toString)

  val trump = new Label("Trump: " + (turnData.trump match {
    case 1 => "\u2660"
    case 2 => "\u2666"
    case 3 => "\u2663"
    case 4 => "\u2665"
  }))

  val turnType = new Label("\tRunden Typ: " + (turnData.turnType match {
    case 0 => "Angriff/Verteidigung"
    case 1 => "Angriff vom vorherigen Spieler ausgesetzt"
    case 2 => "Verteidiger nimmt die Karten auf"
  }))

  val restCards = new Label("Karten im Stapel: " + turnData.mainDeck.deck.length)

  val topInfoBox: HBox = new HBox {
    padding = Insets(0, 20, 0, 20)
    spacing = (20)
    children = Seq(
      defendPlayer,
      currentPlayer,
      trump,
      turnType,
      restCards
    )
  }
  if(turnData.players(turnData.defendPlayer).toString == turnData.players(turnData.currentPlayer).toString) {
    val takeButton = new Button("Karten aufnehmen") {
      prefWidth = (200)
      prefHeight = (40)
      onMouseClicked = (t: MouseEvent) => skipOrTake()
    }
    topInfoBox.getChildren.add(takeButton)
  } else if(turnData.field.size > 0) {
    val skipButton = new Button("Angriff überspringen") {
      prefWidth = (200)
      prefHeight = (40)
      onMouseClicked = (t: MouseEvent) => skipOrTake()
    }
    topInfoBox.getChildren.add(skipButton)
  }



  // game field
  val fieldBoxInfo: VBox = new VBox {
    padding = Insets(50, 0, 50, 10)
    children = Seq(
      new Label("Angreifer Karten:"),
      new Label("Verteidiger Karten:")
    )
  }

  val fieldBoxTop: HBox = new HBox {
    padding = Insets(50, 0, 50, 10)
    children = Seq()
  }

  val fieldBoxBottom: HBox = new HBox {
    padding = Insets(50, 0, 50, 10)
    children = Seq()
  }


  for (i <- Range(0, turnData.field.size, 2)) {
    val topCard = new ImageView(
      "file://../CardLogos/%d-%d.png".format(turnData.field.deck(i).symbol, turnData.field.deck(i).rank)) {
      fitHeight = CARD_HEIGHT
      fitWidth = CARD_WIDTH
    }
    fieldBoxTop.getChildren.add(topCard)


    if(i + 1 < turnData.field.size) {
      val bottomCard = new ImageView(
        "file://../CardLogos/%d-%d.png".format(turnData.field.deck(i + 1).symbol, turnData.field.deck(i + 1).rank)) {
        fitHeight = CARD_HEIGHT
        fitWidth = CARD_WIDTH
      }
      fieldBoxBottom.getChildren.add(bottomCard)
    }
  }


  val fieldBoxContainer: VBox = new VBox {
    children = Seq(
      fieldBoxTop,
      fieldBoxBottom
    )
  }

  val fieldBox: HBox = new HBox {
    children = Seq(
      fieldBoxInfo,
      fieldBoxContainer
    )
  }





  // player card field
  val playerDeck: CardDeckInterface = turnData.playerDecks(turnData.currentPlayer)

  var playerCardBox: HBox = new HBox {
    padding = Insets(20, 20, 20, 20)
  }
  playerCardBox.getChildren.add(new Label("Deine Karten:"));

  for (i <- Range(0, playerDeck.deck.size, 1)) {
    val card = new ImageView(
      "file://../CardLogos/%d-%d.png".format(playerDeck.deck(i).symbol, playerDeck.deck(i).rank)) {
      fitHeight = CARD_HEIGHT
      fitWidth = CARD_WIDTH
      onMouseClicked = (t: MouseEvent) => playCard(i.toString)
    }
    playerCardBox.getChildren.add(card)
  }

  def playCard(id: String): Unit = {
    if(controller.roundData.validateInputList.contains(id)) {
      controller.solve(id)
    }
  }

  def skipOrTake(): Unit = {
    controller.solve("s")
  }



  content  = new BorderPane {
    top = topInfoBox
    center = fieldBox
    bottom = playerCardBox
  }
}
