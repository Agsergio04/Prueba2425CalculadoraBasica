package CalcBasicaConErrorACorregir.app

import CalcBasicaConErrorACorregir.ui.IEntradaSalida
import CalcBasicaConErrorACorregir.model.Operadores
import kotlin.text.format

class Calculadora(private val ui: IEntradaSalida) {

    private fun pedirNumero(msj: String, msjError: String = "Número no válido!"): Double {
        return ui.pedirDouble(msj) ?: throw InfoCalcException(msjError)
    }

    private fun pedirInfo() = Triple(
        pedirNumero("Introduce el primer número: ", "El primer número no es válido!"),
        Operadores.getOperador(ui.pedirInfo("Introduce el operador (+, -, *, /): ").firstOrNull())
            ?: throw InfoCalcException("El operador no es válido!"),
        pedirNumero("Introduce el segundo número: ", "El segundo número no es válido!"))

    private fun realizarCalculo(numero1: Double, operador: Operadores, numero2: Double) =
        when (operador) {
            Operadores.SUMA -> numero1 + numero2
            Operadores.RESTA -> numero1 - numero2
            Operadores.MULTIPLICACION -> numero1 * numero2
            Operadores.DIVISION -> numero1 / numero2
        }

    fun iniciar() {
        do {
            try {
                ui.limpiarPantalla()
                val (numero1, operador, numero2) = pedirInfo()
                val resultado = realizarCalculo(numero1, operador, numero2)
                ui.mostrar("Resultado: %.2f".format(resultado))

            } catch(e: Exception) {
                ui.mostrarError(e.message ?: "Se ha producido un error!",true)
            } catch (e : NumberFormatException){
                ui.mostrarError(e.message ?: "Por favor,Introduce un numero !",true)
            } catch (e : ArithmeticException){
            ui.mostrarError(e.message ?: "No se puede dividir entre 0 !",true)
            }
        } while (ui.preguntar())
        ui.limpiarPantalla()
    }

}