package cx.cad.pgpwords

import org.scalatest.{ShouldMatchers, WordSpec}
import org.scalatest.matchers.{MatchResult, Matcher}

class PgpWordsSpec extends WordSpec with ShouldMatchers with EncodingMatchers {

  val referenceBytesString = "E582 94F2 E9A2 2748 6E8B 061B 31CC 528F D7FA 3F19"
  val referenceWords = "topmost Istanbul Pluto vagabond treadmill Pacific brackish dictator goldfish Medusa afflict bravado chatter revolver Dupont midsummer stopwatch whimsical cowbell bottomless"

  "PgpWords" can {

    "Encode one byte with numbers" in {
      PgpWords.encode(Array(255.toByte)) should be("Zulu")
    }

    "Encode one byte" in {
      "FF" should beEncodedTo("Zulu")
    }

    "Encode two bytes without not duplication" in {
      "FFFF" should beEncodedTo("Zulu Yucatan")
    }

    "Encode simple bytes" in {
      "E582" should beEncodedTo("topmost Istanbul")
    }

    "Encode reference bytes" in {
      referenceBytesString should beEncodedTo(referenceWords)
    }

    "Decode simple bytes" in {
      "miser travesty" should beDecodedTo("82E5")
    }

    "Decode reference bytes" in {
      referenceWords should beDecodedTo(referenceBytesString)
    }

  }
}

trait EncodingMatchers extends ShouldMatchers {

  def removeSpaces(withSpaces: String) = withSpaces.replace(" ", "")

  def byteStringToByteArray(byteString: String): Array[Byte] = {
    removeSpaces(byteString).grouped(2).map { Integer.parseInt(_, 16).toByte }.toArray
  }

  def beEncodedTo(expectedEncoded: String) = {
    Matcher { (byteString: String) =>
      MatchResult(
        PgpWords.encode(byteStringToByteArray(byteString)) == expectedEncoded,
        s"[$byteString] did not encode to [$expectedEncoded] but was ${PgpWords.encode(byteStringToByteArray(byteString))}",
        s"[$byteString] encoded to [$expectedEncoded]"
      )
    }
  }

  def beDecodedTo(byteString: String) = {
    Matcher { (encoded: String) =>
      MatchResult(
        byteStringToByteArray(byteString).toList == PgpWords.decode(encoded).toList,
        s"[$encoded] did not decode to [$byteString] but was ${PgpWords.decode(encoded).toList} and should have been ${byteStringToByteArray(byteString).toList}",
        s"[$encoded] decoded to [$byteString]"
      )
    }
  }
}