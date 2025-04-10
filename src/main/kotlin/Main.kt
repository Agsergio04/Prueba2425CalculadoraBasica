package org.example

import CalcBasicaConErrorACorregir.app.Calculadora
import CalcBasicaConErrorACorregir.ui.Consola
import CalcBasicaConErrorACorregir.ui.IEntradaSalida
import org.example.CalcBasicaConErrorACorregir.utils.Clases.Fichero
import org.example.CalcBasicaConErrorACorregir.utils.Clases.Log

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    Calculadora(Consola(),Log(Fichero())).iniciarLog()
}