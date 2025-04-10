package org.example.CalcBasicaConErrorACorregir.utils.Interfaces

interface UILog {

    fun buscar(rutaArchivo : String) : Boolean
    fun abrir(rutaArchivo : String) : List<String>
    fun registrarOperacion(mensaje: String, error: Boolean = false)
    fun crearLog(rutaArchivo: String,mensaje : List<String>): Boolean
}