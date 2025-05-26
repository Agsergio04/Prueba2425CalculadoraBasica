package org.example.service

import org.example.utils.Fichero
import org.example.utils.IUtilFicheros
import java.io.File
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Log(private val fichero: IUtilFicheros) : UILog {
    private val dateFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmmss")
    private var archivoActual: String = ""
    override var rutaArchivo: String = "./log"
        set(value) {
            field = value
            crearLog(value)
        }

    override fun abrir(rutaArchivo: String): List<String> {
        TODO("Not yet implemented")
    }


    init {
        crearLog(rutaArchivo)
        generarNombreArchivo()
    }

    private fun generarNombreArchivo() {
        val fechaHora = LocalDateTime.now().format(dateFormat)
        archivoActual = "$rutaArchivo/log$fechaHora.txt"
    }

    override fun registrarOperacion(mensaje: String) {
        val linea = "${LocalDateTime.now()} - $mensaje"
        fichero.agregarLinea(archivoActual, linea)
    }

    override fun crearLog(rutaArchivo: String) {
        fichero.crearRuta(rutaArchivo)
    }


    override fun obtenerLogMasReciente(): String? {
        val archivos = File(rutaArchivo).listFiles { file ->
            file.name.matches(Regex("log\\d{14}\\.txt"))
        }?.sortedByDescending { it.name }
        return archivos?.firstOrNull()?.path
    }
}