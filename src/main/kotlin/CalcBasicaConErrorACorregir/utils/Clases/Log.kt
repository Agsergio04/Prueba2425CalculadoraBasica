package org.example.CalcBasicaConErrorACorregir.utils.Clases

import org.example.CalcBasicaConErrorACorregir.utils.Interfaces.UILog
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class Log(private val fichero: Fichero) : UILog {
    private val dateFormat = DateTimeFormatter.ofPattern("yyyyMMddHHmmss")

    override fun registrarOperacion(mensaje: String, error: Boolean) {
        val linea : String

        val fechaHora = LocalDateTime.now().format(dateFormat)
        val archivo = "log$fechaHora.txt"

        if (error){
            linea = "${LocalDateTime.now()} - ${"\"No existen ficheros de Log\"\n"}: $mensaje"
        } else {
            linea = "${LocalDateTime.now()}"
        }

        fichero.agregarLinea(archivo, linea)
    }

    override fun crearLog(rutaArchivo: String,mensaje : List<String>): Boolean {
        return fichero.escribirArchivo(rutaArchivo,mensaje)
    }

    override fun buscar(rutaArchivo: String): Boolean{
        return fichero.existeFichero(rutaArchivo)
    }

    override fun abrir(rutaArchivo: String): List<String>{
        return fichero.leerArchivo(rutaArchivo)
    }


}
