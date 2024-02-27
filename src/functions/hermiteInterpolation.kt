package functions

import kotlin.math.pow

fun hermiteInterpolation
            (
    X: DoubleArray,
    Y: DoubleArray,
    DY: DoubleArray,
    XX: Double,
    m: Int
)
        : Double
{
    var YY = 0.0

    val closestPoints = X.mapIndexed { index, value -> Pair(value, index) }
        .sortedBy { kotlin.math.abs(it.first - XX) }
        .take(m)

    for (i in 0 until m) {
        val idx = closestPoints[i].second
        var L = 1.0
        for (j in 0 until m) {
            if (i != j) {
                L *=( ( (XX - X[closestPoints[j].second]) * (XX - X[closestPoints[j].second]) ) /
                        ( (X[idx] - X[closestPoints[j].second])*(X[idx] - X[closestPoints[j].second]) ) )
            }
        }
        /*
        0.49992
        0.1413
        0.00688
        */

        var H = ((XX - X[idx]) * L)

        /*
        1.04983
        -0.1116227
        -0.0123152
        */
        var a: Double = 0.0

        for (j in 0 until m) {
            if (i != j) {
                a += 1/(X[idx] - X[closestPoints[j].second])
            }
        }
        /*
        -1.5 * -2 = 3
        0
        1.5 * -2 = -3
         */

        a *= -2.0

        var b: Double = (1 - a*X[idx])


        YY += (
                ((a * XX) + b)
                        *
                        Y[idx]
                        /
                        (XX-X[idx])

                        +
                        DY[idx]
                ) * H
    }

    return YY
}
