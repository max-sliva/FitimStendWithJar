import java.net.URLDecoder

fun getCurrentPath(): String {
    val path: String = HelloApplication::class.java.getProtectionDomain().getCodeSource().getLocation().getPath()
    var decodedPath = URLDecoder.decode(path, "UTF-8")
    val last = decodedPath.lastIndexOf("/")
    val newPath = decodedPath.subSequence(0, last)

    return newPath.toString()
}