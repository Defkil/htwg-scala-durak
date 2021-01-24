FROM sergiorussia/docker-openjdk-javafx-sbt:8_1.4.2_2.13.3
WORKDIR /htwg-scala-durak
ADD . /htwg-scala-durak
CMD sbt test