package org.example

import CalcBasicaConErrorACorregir.app.Calculadora
import CalcBasicaConErrorACorregir.ui.Consola
import org.example.utils.Fichero
import org.example.service.Log

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main(args : Array<String>) {
    Calculadora(Consola(),Log(Fichero())).iniciarLog(args)
}
/*
* fun main(args : Array<String>){
* Convierte los argumentos en un vector de elementos string
*
* lateint la puedes inicializar despues de crear la instancia
* patron service repositorio
* }
* */