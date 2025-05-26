package org.example.utils

import CalcBasicaConErrorACorregir.ui.Consola
import org.example.utils.IUtilFicheros
import java.io.File
import java.io.IOException

class Fichero : IUtilFicheros {

    private val consola = Consola()


    override fun leerArchivo(ruta: String): List<String> {
        return try {
            File(ruta).readLines()
        } catch (e: IOException) {
            consola.mostrarError("Error al leer el archivo: ${e.message}")
            emptyList()
        }
    }


    override fun agregarLinea(ruta: String, linea: String): Boolean {
        return try {
            File(ruta).appendText("$linea\n")
            true
        } catch (e: IOException) {
            consola.mostrarError("Error al escribir en el archivo: ${e.message}")
            false
        }
    }


    override fun  escribirArchivo(ruta: String, elementos: List<String>): Boolean {
        return try {
            File(ruta).bufferedWriter().use { out ->
                elementos.forEach { out.write(it.toString() + "\n") }
            }
            true
        } catch (e: IOException) {
            consola.mostrarError("Error al escribir en el archivo: ${e.message}")
            false
        }
    }


    override fun existeFichero(ruta: String): Boolean {
        return File(ruta).exists()
    }


    override fun existeDirectorio(ruta: String): Boolean {
        val archivo = File(ruta)
        return archivo.exists() && archivo.isDirectory
    }

    override fun crearRuta(ruta: String): Boolean {
        val dir = File(ruta)
        return if (!dir.exists()) dir.mkdirs() else false
    }

    override fun listarArchivos(ruta: String): List<File> {
        return File(ruta).listFiles()?.toList() ?: emptyList()
    }
}