package entities

data class DataModel(
    val a1: Double,
    val b1: Double,
    val y1: Double,
    val a2: Double,
    val b2: Double,
    val y2: Double,
    val A: Double,
    val B: Double,
    val N: Int,
    val nodeValues: MutableList<Double> = mutableListOf()
)
