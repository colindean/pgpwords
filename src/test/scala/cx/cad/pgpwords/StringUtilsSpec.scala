package cx.cad.pgpwords

import org.scalatest.{Matchers, WordSpec}

class StringUtilsSpec extends WordSpec with Matchers {

  "StringUtils" can {
    "remove spaces" in {
      StringUtils.removeSpaces("no spaces here") should be("nospaceshere")
    }

    "convert a bytestring to a byte array" in new ByteStringAndByteArray {
      StringUtils.byteStringToByteArray(byteString) should contain theSameElementsAs byteArray
    }

    "convert a byte array to a bytestring" in new ByteStringAndByteArray {
      StringUtils.byteArrayToByteString(byteArray) should be(byteString)
    }
  }
}

trait ByteStringAndByteArray {
  val byteString = "FF"
  val byteArray = new Array[Byte](1)
  byteArray(0) = -1
}
