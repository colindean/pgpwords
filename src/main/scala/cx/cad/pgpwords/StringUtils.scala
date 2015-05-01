package cx.cad.pgpwords


object StringUtils {
  def removeSpaces(withSpaces: String) = withSpaces.replace(" ", "")

  def byteStringToByteArray(byteString: String): Array[Byte] = {
    removeSpaces(byteString).grouped(2).map { Integer.parseInt(_, 16).toByte }.toArray
  }
}
