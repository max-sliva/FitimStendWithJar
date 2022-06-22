import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.layout.FlowPane
import javafx.scene.web.WebEngine
import javafx.scene.web.WebView
import java.io.File
import java.net.URL
import org.json.JSONObject

class WebController {
    @FXML lateinit var button1: Button // кнопка из интерфейса
    @FXML lateinit var webView: WebView
    @FXML lateinit var flowPaneWithButtons: FlowPane
//    var pagesNumbersMap = mapOf<String, String>("1" to "HTML/index1.html", "2" to "HTML/floppy8.html")
    fun initialize() { //ф-ия, срабатывающая при загрузке интерфейса,
//ассоциированного с этим контроллером
//        val webEngine = webView.getEngine()
//        val file = File(String(getClass().getResource("RateCalculatorHelp.html")))
        webView.getEngine().loadContent("<html>hello, world</html>", "text/html");
        var engine = WebEngine()
//        val url: URL = this.javaClass.getResource("HTML/index1.html")
        val url2: URL = this.javaClass.getResource("HTML/floppy8.html")
//        val url2 = URL("file:"+getCurrentPath()+"HTML/floppy8.html")
        webView.zoom = 1.5
        engine = webView.getEngine()
        engine.javaScriptEnabledProperty().set(true)
        engine.load(url2.toString())
        println("js = ${engine.isJavaScriptEnabled}")
        println("Controller working")
        button1.setOnAction { //обработчик нажатия кнопки
            println("Button clicked!")
            engine.load(URL("https://www.nvsu.ru").toString())
           // engine.executeScript("includeHTML()");
//            engine.executeScript("clickFunc()")
        }
    val jsObj = JSONObject("{'rr' : '3' }")
    println("jsObj = $jsObj ")
        val htmlData = fromFileToJSON()
        println("htmlData = $htmlData")
//        val htmlFiles = htmlData.get("htmlFiles")
//        println("htmlFiles = $htmlFiles")
//        val htmlFilesJSONArray = JSONArray(htmlFiles)
        val htmlFilesJSONArray = htmlData.getJSONArray("htmlFiles")
//        val htmlArray = JSONArray.decodeFromString<List<PageFile>>(htmlFilesJSONArray)
        for (i in 0 until htmlFilesJSONArray.length()) {
            val htmlFile = htmlFilesJSONArray.getJSONObject(i)
            println("html = ${htmlFile.get("html")} name =  ${htmlFile.get("name")}")
            val buton = Button(htmlFile.get("name").toString())
            flowPaneWithButtons.children.add(buton)
            buton.setOnMouseClicked {
                val url: URL = this.javaClass.getResource("HTML/${htmlFile.get("html")}")
                engine.load(url.toString())
            }
        }
//        configureArduinoConnect(engine, pagesNumbersMap)
        configureArduinoConnect(engine, htmlFilesJSONArray)
    }

    //todo сделать загрузку объектов из settings.json в 2 хешмапа
    fun fromFileToJSON(): JSONObject { //метод для чтения из файла в json-объект
//        val settingsURL = this.javaClass.getResource("/HTML/settings.json")
        val settingsURL = getCurrentPath()+"/HTML/settings.json"
        println("settingsURL = $settingsURL")
//        val str = "{'data': 111}"
        val str =  File(settingsURL).readText(Charsets.UTF_8)
        println("file data = $str")
        return JSONObject(str)
    }
}