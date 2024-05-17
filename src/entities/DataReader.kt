package entities
import java.io.File

class DataReader(fileName: String) {

    private lateinit var data: DataModel

    init {
        val file = File(fileName)
        var lineCount = 0
        var a1: Double = 0.0
        var b1: Double = 0.0
        var y1: Double = 0.0
        var a2: Double = 0.0
        var b2: Double = 0.0
        var y2: Double = 0.0
        var A: Double = 0.0
        var B: Double = 0.0
        var N: Int = 0
        var nodeValues: MutableList<Double> = mutableListOf()
        file.forEachLine { line ->
            when (lineCount) {
                0 -> {
                    val values = line.trim().split(" ")
                    a1 = values[0].toDouble()
                    b1 = values[1].toDouble()
                    y1 = values[2].toDouble()
                    a2 = values[3].toDouble()
                    b2 = values[4].toDouble()
                    y2 = values[5].toDouble()
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
            a1 = a1,
            a2 = a2,
            b1 = b1,
            b2 = b2,
            y1 = y1,
            y2 = y2,
            A = A,
            B = B,
            N = N,
        )
    }

    fun getData(): DataModel{
        return data;
    }
}
