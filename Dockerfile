FROM hseeberger/scala-sbt
WORKDIR /htwg-scala-durak
ADD . /htwg-scala-durak
CMD sbt test