import scala.io.Source

object App {
  val entries = Source.fromFile("map_from_wikipedia.txt").getLines().map(_.split("\\|\\|"))

  entries.foreach { entry =>
    val byte = entry(0)
    val even = entry(1)
    val odd = entry(2)

    //println(s"""Pair(Even("$even"), Odd("$odd")) // $byte""")
    println(s"""("$even", "$odd"), // $byte""")
  }
}