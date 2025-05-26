package org.example.utils

import org.example.utils.UILog
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Log(private val fichero: Fichero) : UILog {
    private val dateFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmmss")

    //Crear carpeta con mkdirs()

    override fun registrarOperacion(mensaje: String)  {
        val linea = "${LocalDateTime.now()}"
        val fechaHora = LocalDateTime.now().format(dateFormat)
        val archivo = "log$fechaHora.txt"

        fichero.agregarLinea(archivo, linea)
    }

    /*fun agregarOperacion(ruta: String, linea: String): Boolean {
        return fichero.agregarLinea(ruta,linea)
        //el .let si la ruta no es nula ejecuta el {} ?: false
        .lastModify()
    }*/

    override fun crearLog(rutaArchivo: String) {
        fichero
    }

    override fun buscar(rutaArchivo: String): Boolean{
        return fichero.existeFichero(rutaArchivo)
    }

    override fun abrir(rutaArchivo: String): List<String>{
        return fichero.leerArchivo(rutaArchivo)
    }


    /*
    * Filtrar primero si empieza por "log...txt"
    * funcion que solo le paso la ruta y otra que
    * */
}