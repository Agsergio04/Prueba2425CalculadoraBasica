package org.example.service

interface UILog {

    var rutaArchivo : String

    fun abrir(rutaArchivo : String) : List<String>
    fun crearLog(rutaArchivo: String)
    fun registrarOperacion(mensaje: String)
    fun obtenerLogMasReciente(): String?

}