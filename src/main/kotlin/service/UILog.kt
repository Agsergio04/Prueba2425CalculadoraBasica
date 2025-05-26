package org.example.service

interface UILog {

    val rutaArchivo : String

    fun buscar(rutaArchivo : String) : Boolean
    fun abrir(rutaArchivo : String) : List<String>
    fun crearLog(rutaArchivo: String)
    fun registrarOperacion(mensaje: String)

}