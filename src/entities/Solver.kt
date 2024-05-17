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

    var uValues: MutableList<Double> = mutableListOf()
    var vValues: MutableList<Double> = mutableListOf()
    var wValues: MutableList<Double> = mutableListOf()
    var aValues: MutableList<Double> = mutableListOf()
    var bValues: MutableList<Double> = mutableListOf()
    var yValues: MutableList<Double> = mutableListOf()

    val fileNameOut: String = "C:\\Users\\N1o\\0projects\\IntellijIdea\\kt\\Numerical_Methods_6_semester\\src\\res\\output2.txt"

    fun solve(){
        val file = File(fileNameOut)
        val printWriter = PrintWriter(file)
        printWriter.close()
        solveFirstSystem()
        solveSecondSystem()
        solveUVWABYSystem()
    }

    fun solveUVWABYSystem(){
        /*
        TODO сделать норм решение
         */
        var i=0
        while (i<data.N){
            val diff = if (aValues[i] != 0.0){-1.0*uValues[i]/aValues[i]}else{1.0}

            val y = ( wValues[i] + ( yValues[i]*diff ) )/( -1.0*vValues[i] +( -1.0*bValues[i] * diff ) )
            val yi = ( wValues[i] + vValues[i] * y )/uValues[i]

            writeToOut(
                data.nodeValues[i],
                y,
                yi,
            )
            ++i
        }
    }


    fun solveFirstSystem(){
        val u0 = data.a1
        val v0 = -1*data.b1
        val w0 = data.y1

        val ui = "($p)*($u0)+($v0)"
        val vi = "($q)*($u0)"
        val wi = "($f)*($u0)"

        uValues = getFValues(u0, ui)
        vValues = getFValues(v0, vi)
        wValues = getFValues(w0, wi)

    }

    fun solveSecondSystem(){
        val a0 = data.a2
        val b0 = -1*data.b2
        val y0 = data.y2

        val ai = "($p)*($a0)+($b0)"
        val bi = "($q)*($a0)"
        val yi = "($f)*($a0)"

        aValues = getFValues(a0, ai)
        bValues = getFValues(b0, bi)
        yValues = getFValues(y0, yi)
    }

    fun getFValues(_f0: Double, fi: String):MutableList<Double>{
        var u0 = _f0
        val fValues: MutableList<Double> = mutableListOf()
        fValues.add(u0)
        var i=1
        while(i<data.N){

            val solver: IntegrationOrdinaryDifferentialEquation =
                IntegrationOrdinaryDifferentialEquation(
                    fxy=fi,
                    A=data.nodeValues[i-1],
                    B=data.nodeValues[i],
                    C=data.nodeValues[i-1],
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
                println(yValue) // Вывод значения y в формате Double
            } else {
                u0 = 0.0
                println("Файл содержит недостаточно строк")
            }

            fValues.add(u0)

            ++i
        }
        return fValues
    }

    private fun writeToOut(x:Double, y:Double, yii:Double){
        val file = File(fileNameOut)
        val printWriter = PrintWriter(FileWriter(file, true))

        printWriter.println("x: ${x}, y: ${y}, y': ${yii}")

        printWriter.close()

    }
}