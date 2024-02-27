import tests.*

fun main() {
    /*
    val X = DoubleArray(10) { it + 1.0 } // X = [1.0, 2.0, 3.0, ..., 10.0]
    val Y = X.map { it * it }.toDoubleArray() // Y = [1.0, 4.0, 9.0, ..., 100.0]
    val DY = DoubleArray(10) { 2*( it + 1.0) } // Производная функции f(x) = 2x
    //val DY = DoubleA // Производная функции f(x) = 2x
    val XX = 1.1 // Точка, в которой будем вычислять интерполяционное значение
    val m = 3
    */

    dependenceOfPrecisionOnNumberofPoints()
    dependenceOfPrecisionOnDistancePoints()
    checkMultipleMultipleDegrees()

}
