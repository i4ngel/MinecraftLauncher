package org.example.Screens.Inicio

import javafx.geometry.Insets
import javafx.scene.control.Alert
import javafx.scene.control.Alert.AlertType
import javafx.scene.control.Button
import javafx.scene.control.ButtonType
import javafx.scene.control.ComboBox
import javafx.scene.control.Label
import javafx.scene.control.TextField
import javafx.scene.layout.VBox
import org.example.Launcher.Launcher
import java.io.File
import java.awt.Desktop
import java.net.URI

class PantallaInicio(private val launcher: Launcher) : VBox() {

    init {
        spacing = 10.0
        padding = Insets(20.0)

        val labelUsuario = Label("Ingresa tu nombre de usuario:")
        val inputUsuario = TextField()
        val labelVersion = Label("Selecciona la versión:")

        // Crear el ComboBox para las versiones
        val comboBoxVersion = ComboBox<String>()

        // Ruta a la carpeta Versions
        val versionsFolder = File("src/main/kotlin/Componentes/Versions") // Ajusta si estás fuera de src

        // Verificamos que existe y es un directorio
        if (versionsFolder.exists() && versionsFolder.isDirectory) {
            val versiones = versionsFolder.listFiles()
                ?.filter { it.isDirectory && it.name.startsWith("V_") }
                ?.map { it.name }
                ?.sorted() // Ordenar alfabéticamente si quieres
                ?: emptyList()

            comboBoxVersion.items.addAll(versiones)
        } else {
            println("La carpeta 'Versions' no existe o no es un directorio.")
        }

        val btnContinuar = Button("Continuar")

        btnContinuar.setOnAction {
            val nombreUsuario = inputUsuario.text.trim()
            val versionSeleccionada = comboBoxVersion.value

            if (nombreUsuario.isNotEmpty() && versionSeleccionada != null) {
                launcher.nombreUsuario = nombreUsuario
                launcher.versionSeleccionada = versionSeleccionada

                println("Usuario ingresado: $nombreUsuario")
                println("Versión seleccionada: $versionSeleccionada")

                // Verificar si Minecraft está instalado
                val minecraftPath = "C:\\Program Files\\Minecraft\\Minecraft.exe" // Ajusta la ruta de Minecraft
                val minecraftFile = File(minecraftPath)

                if (minecraftFile.exists()) {
                    // Minecraft está instalado, lanzar el juego
                    lanzarMinecraft(minecraftPath, versionSeleccionada)
                } else {
                    // Minecraft no está instalado, mostrar alerta
                    mostrarAlertaDeInstalacion()
                }
            } else {
                println("Por favor completa ambos campos.")
            }
        }

        children.addAll(labelUsuario, inputUsuario, labelVersion, comboBoxVersion, btnContinuar)
    }

    // Función para mostrar la alerta de instalación
    private fun mostrarAlertaDeInstalacion() {
        val alerta = Alert(AlertType.INFORMATION)
        alerta.title = "Minecraft no instalado"
        alerta.headerText = "No se encuentra Minecraft"
        alerta.contentText = "Por favor instala Minecraft correctamente. Puedes descargarlo desde el sitio oficial."

        // Mostrar el botón predeterminado en la alerta
        alerta.showAndWait().ifPresent {
            // Cuando el usuario hace clic en el botón "Aceptar", abrir la página de descarga
            if (it == ButtonType.OK) {
                Desktop.getDesktop().browse(URI("https://www.minecraft.net"))
            }
        }
    }

    // Función para lanzar el Minecraft con la versión seleccionada
    private fun lanzarMinecraft(minecraftPath: String, version: String) {
        try {
            val versionFolder = File("src/main/kotlin/Componentes/Versions/$version")
            if (versionFolder.exists()) {
                // Aquí puedes usar la ruta de instalación de Minecraft y la carpeta de versión seleccionada
                // Comando para abrir el launcher de Minecraft
                val command = "$minecraftPath --version $version" // Ajusta según la configuración
                val process = ProcessBuilder(command).start()
                println("Abriendo Minecraft con la versión: $version")
            } else {
                println("La carpeta de la versión no existe.")
            }
        } catch (e: Exception) {
            println("Error al intentar abrir Minecraft: ${e.message}")
        }
    }
}
