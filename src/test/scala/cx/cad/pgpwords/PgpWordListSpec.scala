package cx.cad.pgpwords

import org.scalatest.{ShouldMatchers, WordSpec}

class PgpWordListSpec extends WordSpec with ShouldMatchers {
  "PgpWordListSource" should {
    "have 256 elements" in {
      PgpWordList.pairs should have size(256)
    }
    "have expected first pair" in {
      PgpWordList.pairs(0) should be(Pair("aardvark", "adroitness"))
    }
    "reverse words to bytes" in {
      PgpWordList.reversePairs("aardvark") should be(0)
    }
  }
}
