package tests

import functions.hermiteInterpolation

fun dependenceOfPrecisionOnNumberofPoints()
{
    // x^2

    val X1 = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0)
    val Y1 = X1.map { it * it }.toDoubleArray()
    val DY1 = doubleArrayOf(2.0, 4.0, 6.0, 8.0, 10.0)
    val XX1 = 2.5
    val m1 = 1

    val result1 = hermiteInterpolation(X1, Y1, DY1, XX1, m1)
    println("f = x^2, m = $m1: Interpolation value at point $XX1 = $result1")

    val X2 = doubleArrayOf(0.0, 1.0, 2.0, 3.0)
    val Y2 = X2.map { it * it }.toDoubleArray()
    val DY2 = doubleArrayOf(2.0, 4.0, 6.0, 8.0)
    val XX2 = 1.5
    val m2 = 2

    val result2 = hermiteInterpolation(X2, Y2, DY2, XX2, m2)
    println("f = x^2, m = $m2: Interpolation value at point $XX2 = $result2")

    val X3 = doubleArrayOf(2.0, 4.0, 6.0, 8.0, 10.0)
    val Y3 = X3.map { it * it }.toDoubleArray()
    val DY3 = doubleArrayOf(4.0, 8.0, 12.0, 16.0, 20.0)
    val XX3 = 5.0
    val m3 = 3

    val result3 = hermiteInterpolation(X3, Y3, DY3, XX3, m3)
    println("f = x^2, m = $m3: Interpolation value at point $XX3 = $result3")

    //  x^4 + 2x^3 + 3x^2 + 4x

    val X4 = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0)
    val Y4 = X1.map { it * it * it * it + 2 * it * it * it + 3 * it * it + 4 * it }.toDoubleArray()
    val DY4 = doubleArrayOf(20.0, 72.0, 184.0, 380.0, 684.0)
    val XX4 = 2.5   // 99.0625
    var m4 = 1

    val result4 = hermiteInterpolation(X4, Y4, DY4, XX4, m4)
    println("f = x^4 + 2x^3 + 3x^2 + 4x, m = $m4: Interpolation value at point $XX1 = $result4")

    m4 = 2

    val result5 = hermiteInterpolation(X4, Y4, DY4, XX4, m4)
    println("f = x^4 + 2x^3 + 3x^2 + 4x, m = $m4: Interpolation value at point $XX4 = $result5")

    m4 = 3

    val result6 = hermiteInterpolation(X4, Y4, DY4, XX4, m4)
    println("f = x^4 + 2x^3 + 3x^2 + 4x, m = $m4: Interpolation value at point $XX4 = $result6")

    m4 = 4

    val result7 = hermiteInterpolation(X4, Y4, DY4, XX4, m4)
    println("f = x^4 + 2x^3 + 3x^2 + 4x, m = $m4: Interpolation value at point $XX4 = $result7")

}

fun dependenceOfPrecisionOnDistancePoints()
{
    val X = doubleArrayOf(-1000.0, -999.0, 1.0, 2.0, 3.0, 4.0, 5.0, 1000.0)
    val Y = X.map { 1000.0 }.toDoubleArray()
    val DY = doubleArrayOf(1000.0, 1000.0, 2.0, 4.0, 6.0, 8.0, 10.0, 1000.0)
    var XX = 1.1
    val m = 3

    var result = hermiteInterpolation(X, Y, DY, XX, m)
    println("\nf = 1000, m = $m: interpolation value at point $XX = $result \n")

    XX = 3.1
    result = hermiteInterpolation(X, Y, DY, XX, m)
    println("\nf = 1000, m = $m: interpolation value at point $XX = $result \n")

    XX = -500.0
    result = hermiteInterpolation(X, Y, DY, XX, m)
    println("\nf = 1000, m = $m: interpolation value at point $XX = $result \n")

}

fun checkMultipleMultipleDegrees()
{
    // Пример для многочлена степени 1 f = x+1
    val X1 = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0)
    val Y1 = doubleArrayOf(2.0, 3.0, 4.0, 5.0, 6.0)
    val DY1 = doubleArrayOf(1.0, 1.0, 1.0, 1.0, 1.0)
    val XX1 = 2.5
    val m1 = 2

    val result1 = hermiteInterpolation(X1, Y1, DY1, XX1, m1)
    println("f = x+1, m = $m1: Interpolation value at point $XX1 = $result1")

    // Пример для многочлена степени 2 x^2
    val X2 = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0)
    val Y2 = doubleArrayOf(1.0, 4.0, 9.0, 16.0, 25.0)
    val DY2 = doubleArrayOf(2.0, 4.0, 6.0, 8.0, 10.0)
    val XX2 = 2.5
    val m2 = 3

    val result2 = hermiteInterpolation(X2, Y2, DY2, XX2, m2)
    println("f = x^2, m = $m2: Interpolation value at point $XX2 = $result2")

    // Пример для многочлена степени 3 x^3
    val X3 = doubleArrayOf(1.0, 2.0, 3.0, 4.0, 5.0)
    val Y3 = doubleArrayOf(1.0, 8.0, 27.0, 64.0, 125.0)
    val DY3 = doubleArrayOf(3.0, 12.0, 27.0, 48.0, 75.0)
    val XX3 = 2.5
    val m3 = 4


    val result3 = hermiteInterpolation(X3, Y3, DY3, XX3, m3)
    println("f = x^3, m = $m3: Interpolation value at point $XX3 = $result3")
}