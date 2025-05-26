package org.example.utils

import java.io.File

interface IUtilFicheros {


    fun leerArchivo(ruta: String): List<String>
    fun agregarLinea(ruta: String, linea: String): Boolean
    fun escribirArchivo(ruta: String, elementos: List<String>): Boolean
    fun existeFichero(ruta: String): Boolean
    fun existeDirectorio(ruta: String): Boolean
    fun crearRuta(ruta : String) : Boolean
    fun listarArchivos(ruta: String): List<File>
}