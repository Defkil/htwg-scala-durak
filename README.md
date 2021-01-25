# Durak Scala Spiel
[![Build Status](https://travis-ci.org/Defkil/htwg-scala-durak.svg?branch=master)](https://travis-ci.org/Defkil/htwg-scala-durak)
[![Coverage Status](https://coveralls.io/repos/github/Defkil/htwg-scala-durak/badge.svg?branch=master)](https://coveralls.io/github/Defkil/htwg-scala-durak?branch=master)
[![Documentation Status](https://readthedocs.org/projects/ansicolortags/badge/?version=latest)](https://defkil.github.io/htwg-scala-durak/master/docs/de/htwg/se/durak/index.html)
> Ein Projekt für das Modul Software Engineering

## :books: Inhaltsverzeichnis
- [Installation](#package-installation)
- [Componenten](#information_source-Componenten)
- [GameData](#capital_abcd-GameData)
- [Spielinfo](#framed_picture-Spielinfo)

## :package: Installation
Benötigt zum starten ist [Java](https://www.oracle.com/java/technologies/javase-downloads.html) und [Scala](https://www.scala-lang.org/)

Github Projekt Clonen oder [Downloaden](https://github.com/Defkil/htwg-scala-durak/archive/master.zip)

```sh
# Um das Spiel mit der GUI zu starten
sbt runMain de.htwg.se.durak.DurakGUI

# Um das Spiel mit der TUI zu starten
sbt runMain de.htwg.se.durak.DurakTUI
```

## :information_source: Componenten
* controller/controllerComponent Controller mit Comannd Solver
* controller/gameLogicComponent Enthält die Spiel Logic und die des Menüs
* model/fileIoComponent Zum Speichern und Laden von Spielständen
* model/gameElementsComponent Spiel Elementen wie Karten und Karten Decks
* model/playerComponent Spieler Infos
* model/roundComponent Zum generieren von Runden Elementen

## :capital_abcd: GameData
Besteht aus _RoundData_ und _TurnData_.

_RoundData_ besitzt Informationen über die Runde, wie die aktuelle Seite und mögliche Eingaben.
Es können nur Eingaben gemacht werden, die in RoundData gelistet werden. So können nur mögliche Karten
gelegt werden.

_TurnData_ besitzt Informationen über das Spiel. Darin sind alle Karten Decks drinnen (der Spieler, des Feldes, der Stacks)

# :framed_picture: Spielinfo
[Wikipedia](https://de.wikipedia.org/wiki/Durak_(Kartenspiel)) |
[Online Version](https://durak.hlop.de/)
