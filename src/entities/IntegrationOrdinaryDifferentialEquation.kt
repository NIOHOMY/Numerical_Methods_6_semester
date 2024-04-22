package entities

import java.io.File
import net.objecthunter.exp4j.ExpressionBuilder
import java.io.FileWriter
import java.io.PrintWriter
import kotlin.math.abs

class IntegrationOrdinaryDifferentialEquation(fxy: String, fileName: String) {
    private var fileNameOut: String = ""
    private var A: Double = 0.0
    private var B: Double = 0.0
    private var C: Double = 0.0
    private var yC: Double = 0.0
    private var hMin: Double = 0.0
    private var E: Double = 0.0

    private val function: (Double, Double) -> Double

    private var k1_y: Double = 0.0
    private var k2_y: Double = 0.0
    private var k2_E: Double = 0.0
    private var k3_E: Double = 0.0
    private var h: Double = 0.0
    private var curX: Double = 0.0
    val k = 2*2*2

    var incorrectXCount:Int = 0
    var xCount:Int = 0
    var hMinCount:Int = 0

    var flagEnd: Boolean = false
    var flagLastStep: Boolean = false

    init {
        fileNameOut = "C:\\Users\\N1o\\0projects\\IntellijIdea\\kt\\Numerical_Methods_6_semester\\src\\res\\output.txt"
        function = { x, y ->
            val expression = fxy.replace("x", "$x").replace("y", "$y")
            evaluateExpression(expression)
        }
        File(fileName).useLines { lines ->
            val dataList = lines.toList()
            val firstLineData = dataList[0].split(" ")
            A = firstLineData[0].toDouble()
            B = firstLineData[1].toDouble()
            C = firstLineData[2].toDouble()
            yC = firstLineData[3].toDouble()
            val secondLineData = dataList[1].split(" ")
            hMin = secondLineData[0].toDouble()
            E = secondLineData[1].toDouble()
        }

        curX = C
        h = (B-A)/1000.0
        if (h<hMin)
            h=hMin
        if (C==B){
            hMin *=-1
            h*=-1
        }
    }


    fun evaluateExpression(expression: String): Double {
        val result = ExpressionBuilder(expression).build().evaluate()
        return result
    }

    private fun calculateFunctionValue(x: Double, y: Double): Double {
        return function(x, y)
    }

    fun integrate(){

        val file = File(fileNameOut)
        val printWriter = PrintWriter(file)
        printWriter.close()
        while (!flagEnd){
            calculateNext()
        }

        val printWriterF = PrintWriter(FileWriter(file, true))
        printWriterF.println("Ex: ${incorrectXCount}, hMin count: ${hMinCount}, X count: ${xCount}")
        printWriterF.close()

    }

    private fun getNextX(){
        if(h>0.0){
            if (!(B-(curX+h) < hMin)) {
                curX += h
            }
            else{
                val tmpX=curX
                if (B-curX >= 2.0*h){
                    flagLastStep = true
                    curX = B-h
                }
                else if((B-curX <= 1.5*h) || flagLastStep){
                    flagEnd = true
                    curX = B
                }
                else if((B-curX > 1.5*h)&&(B-curX < 2.0*h)){
                    flagLastStep = true
                    curX += (B - curX) / 2.0
                }
                h = curX-tmpX
            }
        }
        else{
            if (!(A-(curX+h) > hMin)) {
                curX += h
            }
            else{
                val tmpX=curX
                if (A-curX < 2.0*h){
                    flagLastStep = true
                    curX = A-h
                }
                else if((A-curX > 1.5*h) || flagLastStep){
                    flagEnd = true
                    curX = A
                }
                else if((A-curX <= 1.5*h)&&(A-curX >= 2.0*h)){
                    flagLastStep = true
                    curX += (A - curX) / 2.0
                }
                h = curX-tmpX
            }
        }
    }
    private fun calculateNext(){

        var flag: Boolean = false
        var isDivision: Boolean = false
        do {
            hMinCount += (1).let { if(h==hMin) it else 0 }
            val tmpX = curX
            getNextX()
            val o = curX
            xCount+=1
            k1_y = h*calculateFunctionValue(C, yC)
            k2_y = h*calculateFunctionValue(C + h, yC + k1_y)

            k2_E = h*calculateFunctionValue(C + 0.5*h, yC + 0.5*k1_y)
            k3_E = h*calculateFunctionValue(C + h, yC - k1_y + 2.0*k2_E)

            val acurY = yC + (1.0/6.0)*(k1_y + 4.0*k2_E +k3_E)
            val curY: Double = yC+ 0.5*(k1_y + k2_y)
            val curE: Double = acurY-curY//(-1.0/3.0)*(k1_y - 2.0*k2_E + k3_E)

            if ((abs(curE)==0.0 || (abs(curE) < E/k)) && !isDivision) {
                h *= 2.0
            }
            //else if((abs(lastNode.E) >= E/k) && (abs(lastNode.E) <= E)){}
            if((abs(curE) > E) && !(flagEnd || flagLastStep)){

                if( h!=hMin ) {
                    isDivision = true
                    curX = tmpX
                    h= (h/2.0).let { if(abs(h/2.0) >= abs(hMin)) it else hMin }
                }
                else{
                    incorrectXCount+=1
                    flag = true
                    addNode(curX, curY, curE)
                }
            }
            else{
                flag = true
                addNode(curX, curY, curE)
            }


        }while (!flag)

    }

    private fun addNode(x:Double, y:Double, E:Double){
        val file = File(fileNameOut)
        val printWriter = PrintWriter(FileWriter(file, true))

        printWriter.println("x: ${x}, y: ${y}, E: ${String.format("%.1e", E)}")

        printWriter.close()
        yC = y
        C = x
    }
}