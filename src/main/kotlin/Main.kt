import javafx.application.Application
import javafx.application.Platform
import javafx.event.EventHandler
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.layout.BorderPane
import javafx.scene.text.Font
import javafx.scene.text.Text
import javafx.stage.Stage
import javafx.stage.WindowEvent
import java.net.URL
import java.net.URLDecoder


class HelloApplication : Application() {
    override fun start(primaryStage: Stage) {
//        val path: String = HelloApplication::class.java.getProtectionDomain().getCodeSource().getLocation().getPath()
//        var decodedPath = URLDecoder.decode(path, "UTF-8")
//        val last = decodedPath.lastIndexOf("/")
//        val newPath = decodedPath.subSequence(0, last)
//        val fxmlPath = "$newPath/hello-view.fxml"
//        println("path = $fxmlPath")
//        println(HelloApplication::class.java.getResource("/my/hello-view.fxml"))
//        println(URL("file:$fxmlPath"))
////        val fxmlLoader = FXMLLoader(HelloApplication::class.java.getResource("/my/hello-view.fxml"))
//        val fxmlLoader = FXMLLoader(URL("file:$fxmlPath"))
//        val scene = Scene(fxmlLoader.load(), 320.0, 240.0)
//        primaryStage.title = "Hello!"
//        primaryStage.scene = scene
//        primaryStage.show()
//        val helloWorld = Text() //создаем простой текст
//        helloWorld.setText("Hello World") //задаем его значение


//        val path: String = HelloApplication::class.java.getProtectionDomain().getCodeSource().getLocation().getPath()
//        var decodedPath = URLDecoder.decode(path, "UTF-8")
//        val last = decodedPath.lastIndexOf("/")
//        val newPath = decodedPath.subSequence(0, last)
        val fxmlPath = "${getCurrentPath()}/mainWindow.fxml"
        println("path = $fxmlPath")
//        println(HelloApplication::class.java.getResource("/my/hello-view.fxml"))
        println(URL("file:$fxmlPath"))
//        val fxmlLoader = FXMLLoader(this.javaClass.getResource("mainWindow.fxml")) //для запуска из среды
//        val fxmlLoader = FXMLLoader(HelloApplication::class.java.getResource("/my/hello-view.fxml"))
        val fxmlLoader = FXMLLoader(URL("file:$fxmlPath")) //для jar-файла
        val scene = Scene(fxmlLoader.load())
        primaryStage?.title = "Hello!"
        primaryStage?.scene = scene
//        primaryStage?.initStyle(StageStyle.UNDECORATED)
        primaryStage?.setMaximized(true)
        primaryStage?.show()
        primaryStage!!.setOnCloseRequest(object : EventHandler<WindowEvent?> {
            override fun handle(t: WindowEvent?) {
                Platform.exit()
                System.exit(0)
            }
        })
    }

        companion object { //специальный объект для запуска проекта в рамках фреймворка JavaFX
        @JvmStatic // его всегда оставляем одинаковым для всех проектов

        fun main(args: Array<String>) {
            launch(HelloApplication::class.java) // Main – имя запускного класса
        }
    }
}

//fun main() {
//    Application.launch(HelloApplication::class.java)
//}