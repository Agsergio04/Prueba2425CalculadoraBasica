package CalcBasicaConErrorACorregir.app

import CalcBasicaConErrorACorregir.ui.IEntradaSalida
import CalcBasicaConErrorACorregir.model.Operadores
import org.example.service.UILog
import kotlin.text.format

class Calculadora(
    private val ui: IEntradaSalida,
    private val log : UILog
) {

    companion object {
        private const val RUTA_POR_DEFECTO = "./log"
    }

    private fun pedirNumero(msj: String, msjError: String = "Número no válido!"): Double {
        return ui.pedirDouble(msj) ?: throw InfoCalcException(msjError)
    }

    private fun pedirInfo() = Triple(
        pedirNumero("Introduce el primer número: ", "El primer número no es válido!"),
        Operadores.getOperador(ui.pedirInfo("Introduce el operador (+, -, x, /): ").firstOrNull())
            ?: throw InfoCalcException("El operador no es válido!"),
        pedirNumero("Introduce el segundo número: ", "El segundo número no es válido!"))

    private fun realizarCalculo(numero1: Double, operador: Operadores, numero2: Double) =
        when (operador) {
            Operadores.SUMA -> numero1 + numero2
            Operadores.RESTA -> numero1 - numero2
            Operadores.MULTIPLICACION -> numero1 * numero2
            Operadores.DIVISION -> numero1 / numero2
        }

    fun iniciarLog(args: Array<String>) {
        if (!procesarArgumentos(args)) return

        ui.mostrar("Presione Enter para continuar...")
        ui.pedirInfo("")
        ui.limpiarPantalla()

        do {
            try {
                val (numero1, operador, numero2) = pedirInfo()
                val resultado = realizarCalculo(numero1, operador, numero2)
                ui.mostrar("$numero1 ${operador.simbolos.first()} $numero2 = $resultado")
                log.registrarOperacion("$numero1 ${operador.simbolos.first()} $numero2 = $resultado")
            } catch (e: Exception) {
                ui.mostrarError("Error en argumentos: ${e.message}")
            }
        } while (ui.preguntar())
    }

    private fun procesarArgumentos(args: Array<String>): Boolean {
        return when (args.size) {
            0 -> manejarCasoSinArgumentos()
            1 -> manejarUnArgumento(args[0])
            4 -> manejarCuatroArgumentos(args)
            else -> {
                ui.mostrarError("Número de argumentos inválido. Esperado: 0, 1 o 4.")
                false
            }
        }
    }

    private fun manejarCuatroArgumentos(args: Array<String>): Boolean {
        val ruta = args[0]
        log.rutaArchivo = ruta

        try {
            val num1 = args[1].toDouble()
            val operador = Operadores.getOperador(args[2].firstOrNull())
                ?: throw InfoCalcException("Operador inválido")
            val num2 = args[3].toDouble()

            val resultado = realizarCalculo(num1, operador, num2)
            ui.mostrar("Resultado: ${"%.2f".format(resultado)}")
            log.registrarOperacion("$num1 ${operador.simbolos.first()} $num2 = $resultado")
            return true
        } catch (e: Exception) {
            ui.mostrarError("Error en argumentos: ${e.message}")
            return false
        }
    }

    private fun manejarCasoSinArgumentos(): Boolean {
        log.rutaArchivo = RUTA_POR_DEFECTO
        mostrarLogsExistentes()
        return true
    }

    private fun manejarUnArgumento(ruta: String): Boolean {
        log.rutaArchivo = ruta
        mostrarLogsExistentes()
        return true
    }

    private fun mostrarLogsExistentes() {
        val logReciente = log.obtenerLogMasReciente()
        if (logReciente != null) {
            ui.mostrar("\n--- Log más reciente ---")
            log.abrir(logReciente).forEach { ui.mostrar(it) }
        } else {
            ui.mostrar("No existen ficheros de Log")
        }
    }


}