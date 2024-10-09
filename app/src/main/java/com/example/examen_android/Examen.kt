package org.example

import kotlin.math.abs
import kotlin.math.sqrt

fun ecuacionSegundoGrado(a: Double, b: Double, c: Double): Array<Double> {
    if (a == 0.0 && b != 0.0) {
        val x = -c / b
        return arrayOf(2.0, x, 0.0)
    } else if (a == 0.0 && b == 0.0) {
        return arrayOf(0.0, 0.0, 0.0)
    } else {
        val discriminante = b * b - 4 * a * c
        return when {
            discriminante > 0 -> {
                val x1 = (-b + Math.sqrt(discriminante)) / (2 * a)
                val x2 = (-b - Math.sqrt(discriminante)) / (2 * a)
                arrayOf(1.0, x1, x2)
            }
            discriminante == 0.0 -> {
                val x = -b / (2 * a)
                arrayOf(1.0, x, x)
            }
            else -> {
                arrayOf(0.0, 0.0, 0.0)
            }
        }
    }
}

fun f1(a: Double, b: Double, c: Double, solver: (Double, Double, Double) -> Array<Double>, multiplicar: (Double, Double, Double) -> Double): Pair<Double, Double> {
    val resultadoEcuacion = solver(a, b, c)

    val resultadoRaices = when (resultadoEcuacion[0]) {
        0.0 -> 0.0
        1.0 -> Math.abs(resultadoEcuacion[1]) + Math.abs(resultadoEcuacion[2])
        2.0 -> resultadoEcuacion[1]
        else -> 0.0
    }

    val resultadoMultiplicacion = multiplicar(a, b, c)

    return Pair(resultadoRaices, resultadoMultiplicacion)
}



fun f2(array: Array<Double>, operacion: (Array<Double>) -> Double): Double {
    return operacion(array)
}

fun main() {
    val multiplicacion: (Double, Double, Double) -> Double = { x, y, z ->
        if (x == 0.0 && y == 0.0 && z == 0.0) 1.0 else x * y * z
    }
    //Paso 1
    val resultado1 = f1(1.0, -3.0, 2.0, ::ecuacionSegundoGrado, multiplicacion)
    println("Resultado de la ecuación con dos soluciones: $resultado1")

    val resultado2 = f1(1.0, -2.0, 1.0, ::ecuacionSegundoGrado, multiplicacion)
    println("Resultado de la ecuación con dos soluciones dobles: $resultado2")

    val resultado3 = f1(0.0, 4.0, -8.0, ::ecuacionSegundoGrado, multiplicacion)
    println("Resultado de la ecuación con una solución: $resultado3")

    val resultado4 = f1(1.0, 2.0, 3.0, ::ecuacionSegundoGrado, multiplicacion)
    println("Resultado de la ecuación sin solución: $resultado4")

    // Paso 2
    val (resultadoEcuacion, resultadoMultiplicacion) = f1(1.0, -3.0, 2.0, ::ecuacionSegundoGrado, multiplicacion)

    println("Resultado de la ecuación: $resultadoEcuacion")
    println("Resultado de la multiplicación: $resultadoMultiplicacion")

    //Paso 3
    val a = 1.0
    val b = -3.0
    val c = 2.0
    val resultadoF1 = f1(a, b, c, ::ecuacionSegundoGrado, multiplicacion)
    println("Resultado de f1: $resultadoF1")

    val array = Array(10) { it.toDouble() }

    val sumatorioLambda: (Array<Double>) -> Double = { arr -> arr.sum() }

    val resultadoF2 = f2(array, sumatorioLambda)
    println("Resultado del sumatorio: $resultadoF2")
}
