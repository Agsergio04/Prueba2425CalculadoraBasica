package org.example.utils

interface UILog {

    fun buscar(rutaArchivo : String) : Boolean
    fun abrir(rutaArchivo : String) : List<String>
    fun crearLog(rutaArchivo: String)
    fun registrarOperacion(mensaje: String)

}