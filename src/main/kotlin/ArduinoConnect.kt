import javafx.application.Platform
import javafx.scene.web.WebEngine
import jssc.SerialPort
import jssc.SerialPortList
import org.json.JSONArray
import org.json.JSONObject

//fun configureArduinoConnect(engine: WebEngine, pagesNumbersMap: Map<String, String>) {
fun configureArduinoConnect(engine: WebEngine, htmlFilesJSONArray: JSONArray) {
    var serialPort: SerialPort?
    for (port in SerialPortList.getPortNames()) {
        println(port)
        serialPort = SerialPort(port)
        serialPort.openPort()
        serialPort.setParams(9600, 8, 1, 0)
        serialPort.addEventListener{
            if (it.isRXCHAR()) {// если есть данные для приема
                var str = serialPort.readString()
//убираем лишние символы (типа пробелов, которые могут быть в принятой строке)
                str = str.trim();
                println(str) //выводим принятую строку
                Platform.runLater{
//                    val url: URL = Main.javaClass.getResource(pagesNumbersMap.get(str))
                    val htmlFile = htmlFilesJSONArray.first {
                        (it as JSONObject).get("number")==str
                    } as JSONObject
//                    val url = Main.javaClass.getResource("HTML/${htmlFile.get("html")}")
                    //todo проверить с Arduino работу jar-файла
                    val url= HelloApplication::class.java.getResource("HTML/${htmlFile.get("html")}")
                    println("htmlFile = $htmlFile")
                    println("file = $url")
                    engine.load(url.toString())
                }
            }
        }
    }
}

