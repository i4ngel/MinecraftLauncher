package org.example.Launcher

import javafx.scene.Node
import javafx.scene.layout.StackPane
import org.example.Screens.Inicio.PantallaInicio

class Launcher : StackPane() {

    var nombreUsuario: String? = null
    var versionSeleccionada: String? = null

    init {
        mostrarPantallaInicio()
    }

    private fun mostrarPantallaInicio() {
        val pantalla = PantallaInicio(this)
        cambiarContenido(pantalla)
    }

    fun cambiarContenido(nuevoContenido: Node) {
        children.clear()  // Limpia la vista actual
        children.add(nuevoContenido)  // Agrega la nueva vista/pantalla
    }

    // Métodos adicionales si es necesario, como iniciar el juego, etc.
    fun iniciarJuego() {
        // Aquí puedes agregar la lógica para iniciar el juego con el nombre de usuario y la versión
        println("Iniciando juego con el usuario: $nombreUsuario y la versión: $versionSeleccionada")
    }
}
