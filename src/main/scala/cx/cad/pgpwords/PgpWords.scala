package cx.cad.pgpwords

object PgpWords {
  def encode(bytes: Array[Byte]): String = PgpWords.usingWordList(PgpWordList).encode(bytes)
  def decode(encodedString: String): Array[Byte] = PgpWords.usingWordList(PgpWordList).decode(encodedString)

  private def usingWordList(wordList: WordListSource) = new PgpWordsConverter(wordList.apply)
}

class PgpWordsConverter(wordList: WordList) {
  val WordSeparator = " "

  def decode(encoded: String): Array[Byte] = {
    encoded.split(WordSeparator).map { word => wordList.reverse(word) }
  }

  def encode(bytes: Array[Byte]): String = {
    var position: Position = Even

    bytes.map { byte =>
        val pair = wordList.pairs(byte)
        position match {
          case Even => position = Odd; pair.even;
          case Odd => position = Even; pair.odd;
        }
    }.mkString(WordSeparator)
  }
}


sealed trait Position
private case object Even extends Position
private case object Odd extends Position
