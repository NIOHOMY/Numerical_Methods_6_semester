package entities

import java.io.File
import java.io.FileWriter
import java.io.PrintWriter

class Solver(
    val data: DataModel,
    val p:String,
    val q:String,
    val f:String,
    ) {

    private val _E=0.0000000000000001

    private var uValues: MutableList<Double> = mutableListOf()
    private var vValues: MutableList<Double> = mutableListOf()
    private var wValues: MutableList<Double> = mutableListOf()
    private var aValues: MutableList<Double> = mutableListOf()
    private var bValues: MutableList<Double> = mutableListOf()
    private var yValues: MutableList<Double> = mutableListOf()

    val fileNameOut: String = "C:\\Users\\N1o\\0projects\\IntellijIdea\\kt\\Numerical_Methods_6_semester\\src\\res\\output2.txt"

    fun solve(){
        val file = File(fileNameOut)
        val printWriter = PrintWriter(file)
        printWriter.close()
        solveFirstSystem()
        solveSecondSystem()
        solveUVWABYSystem()
    }

    fun solveEquationSystemUVWABY(
        u: Double,
        v: Double,
        w: Double,
        a: Double,
        b: Double,
        y: Double
    ): Pair<Double, Double>? {

        println("u: ${u}, v: ${v}, w': ${w}")
        println("a: ${a}, b: ${b}, y': ${y}")
        println("--------------------------")
        fun det(
            p11: Double, p12: Double,
            p21: Double, p22: Double
        ) = p11 * p22 - p12 * p21

        val D = det(u, v, a, b)
        val D1 = det(w, v, y, b)
        val D2 = det(u, w, a, y)

        if (D == 0.0) {
            return null
        }

        val x1 = D1 / D
        val x2 = D2 / D

        return Pair(x1, x2)
    }

    fun solveUVWABYSystem(){
        var i=0
        while (i<data.N){

            val solution = solveEquationSystemUVWABY(
                u = uValues[i], v = -1*vValues[i], w = wValues[i],
                a = aValues[i], b = -1*bValues[i], y = yValues[i],
            )

            if (solution != null)
            writeToOut(
                data.nodeValues[i],
                solution.second,
                solution.first,
            )
            ++i
        }
    }


    fun solveFirstSystem(){
        val u0 = data.a1
        val v0 = -1*data.b1
        val w0 = data.y1

        uValues.add(u0)
        vValues.add(v0)
        wValues.add(w0)

        for (i in 1 until data.N)
        {
            val ui = "($p)*(${uValues[i-1]})+(${vValues[i-1]})"
            val vi = "($q)*(${uValues[i-1]})"
            val wi = "($f)*(${uValues[i-1]})"

            uValues.add(getFValues(uValues[i-1], ui, i))
            vValues.add(getFValues(vValues[i-1], vi, i))
            wValues.add(getFValues(wValues[i-1], wi, i))
        }

    }

    fun solveSecondSystem(){
        val a0 = data.a2
        val b0 = -1*data.b2
        val y0 = data.y2

        aValues.add(a0)
        bValues.add(b0)
        yValues.add(y0)

        for (i in 1 until data.N)
        {
            val ai = "($p)*(${aValues[i-1]})+(${bValues[i-1]})"
            val bi = "($q)*(${aValues[i-1]})"
            val yi = "($f)*(${aValues[i-1]})"

            val index = data.N-1-i
            aValues.add(getFValues(aValues[i-1], ai, index, true))
            bValues.add(getFValues(bValues[i-1], bi, index, true))
            yValues.add(getFValues(yValues[i-1], yi, index, true))
        }
        aValues.reverse()
        bValues.reverse()
        yValues.reverse()

    }

    fun getFValues(_f0: Double, fi: String, i: Int, isBack: Boolean = false): Double{
        var u0 = _f0

        var A = 0.0
        var B = 0.0
        var C = 0.0

        if (!isBack){
            A = data.nodeValues[i-1]
            B = data.nodeValues[i]
            C = data.nodeValues[i-1]
        }
        else{
            A=data.nodeValues[i+1]
            B=data.nodeValues[i]
            C=data.nodeValues[i+1]
        }
        val solver: IntegrationOrdinaryDifferentialEquation =
            IntegrationOrdinaryDifferentialEquation(
                fxy=fi,
                A=A,
                B=B,
                C=C,
                yC= u0,
                E= _E,
                hMin = _E
            )
        solver.integrate()

        val file = File(solver.fileNameOut)
        val lines = file.readLines()

        if (lines.size >= 2) { // Проверяем, что в файле есть хотя бы две строки
            val preLastLine = lines[lines.size - 2] // Получаем предпоследнюю строку
            val yValue = preLastLine.split(",")[1].trim().substringAfter("y:").toDouble()

            u0 = yValue
            println(yValue)
        } else {
            u0 = 0.0
            println("Файл содержит недостаточно строк")
        }

        return u0
    }

    private fun writeToOut(x:Double, y:Double, yii:Double){
        val file = File(fileNameOut)
        val printWriter = PrintWriter(FileWriter(file, true))

        printWriter.println("x: ${x}, y: ${y}, y': ${yii}")

        printWriter.close()

    }
}