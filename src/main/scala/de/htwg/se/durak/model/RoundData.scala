package de.htwg.se.durak.model

case class RoundData(
      siteID: Int,
      nextInput: Int, // 0 = Int, 1 = String, 2 = Line, 3 = Array[Int]
      param: Array[String] = null,
      inputMaxInt: Int = -1, // from 0 to inputMaxInt
      inputMaxStringSize: Int = -1,
      inputMinLineSpacers: Int = -1,
      inputMaxLineSpacers: Int = -1,
      allowedInput: Array[Int] = null,
)
