package org.example

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage
import org.example.Componentes.Layout.Layout
import org.example.Launcher.Launcher

class Main : Application() {
    override fun start(primaryStage: Stage) {
        // Cargar el icono desde resources/img/logo.png
        val icono = Image("img/logo.png") // La ruta desde resources

        // Establecer el icono en la ventana
        primaryStage.icons.add(icono)

        val layout = Layout()
        val launcher = Launcher()
        layout.setContenidoCentral(launcher)

        val scene = Scene(layout, 1000.0, 700.0)
        primaryStage.title = "Minecraft Custom Launcher"
        primaryStage.scene = scene
        primaryStage.isResizable = true
        primaryStage.show()
    }
}

fun main() {
    Application.launch(Main::class.java)
}
