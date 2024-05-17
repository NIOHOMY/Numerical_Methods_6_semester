package entities
import java.io.File

class DataReader(fileName: String) {

    private lateinit var data: DataModel

    init {
        val file = File(fileName)
        var lineCount = 0
        var a: Double = 0.0
        var b: Double = 0.0
        var y: Double = 0.0
        var A: Double = 0.0
        var B: Double = 0.0
        var N: Int = 0
        var nodeValues: MutableList<Double> = mutableListOf()
        file.forEachLine { line ->
            when (lineCount) {
                0 -> {
                    val values = line.trim().split(" ")
                    a = values[0].toDouble()
                    b = values[1].toDouble()
                    y = values[2].toDouble()
                }
                1 -> {
                    val values = line.trim().split(" ")
                    A = values[0].toDouble()
                    B = values[1].toDouble()
                    N = values[2].toInt()
                }
                2 -> {
                    val values = line.trim().split(" ")
                    for (i in 0 until N) {
                        nodeValues.add(values[i].substringAfter(",").substringBefore(")").toDouble())
                    }
                }
            }
            lineCount++
        }
        data = DataModel(
            a = a,
            b = b,
            y = y,
            A = A,
            B = B,
            N = N,
        )
    }

    fun getData(): DataModel{
        return data;
    }
}
