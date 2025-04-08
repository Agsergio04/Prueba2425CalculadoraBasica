package org.example.CalcBasicaConErrorACorregir.utils.Interfaces

interface UILog {

    fun buscar(rutaArchivo : String) : Boolean
    fun abrir(rutaArchivo : String) : List<String>

}