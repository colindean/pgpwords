package cx.cad.pgpwords

case class WordList(pairs: Map[Byte, Pair], reverse: Map[String, Byte])
case class Pair(even: String, odd: String)

trait WordListSource {
  def apply: WordList
}
