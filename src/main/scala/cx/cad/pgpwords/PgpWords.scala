package cx.cad.pgpwords

import cx.cad.pgpwords.PgpWords.WordList

object PgpWords {
  def encode(bytes: Array[Byte]): String = PgpWords.usingPairs(DefaultPairs.pairs).encode(bytes)
  def decode(encodedString: String): Array[Byte] = PgpWords.usingPairs(DefaultPairs.pairs).decode(encodedString)
  def usingPairs(pairs: WordList) = new PgpWordsConverter(pairs)

  type WordList = Map[Byte, Pair]
}

class PgpWordsConverter(pairs: WordList) {
  val WordSeparator = " "

  val reversePairs = pairs.flatMap{ case(index, pair) => Map(pair.even -> index, pair.odd -> index) }

  def decode(encoded: String): Array[Byte] = {
    encoded.split(WordSeparator).map { word => reversePairs(word) }
  }

  def encode(bytes: Array[Byte]): String = {
    var position: Position = Even

    bytes.map { byte =>
        val pair = pairs(byte)
        position match {
          case Even => position = Odd; pair.even;
          case Odd => position = Even; pair.odd;
        }
    }.mkString(WordSeparator)
  }
}

case class Pair(even: String, odd: String)

sealed trait Position
case object Even extends Position
case object Odd extends Position
