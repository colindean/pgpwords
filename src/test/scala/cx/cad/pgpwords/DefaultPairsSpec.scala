package cx.cad.pgpwords

import org.scalatest.{ShouldMatchers, WordSpec}

class DefaultPairsSpec extends WordSpec with ShouldMatchers {
  "DefaultPairs" should {
    "have 256 elements" in {
      DefaultPairs.pairs should have size(256)
    }
    "have expected first pair" in {
      DefaultPairs.pairs(0) should be(Pair("aardvark", "adroitness"))
    }
  }
}
