import entities.DataReader
import entities.Solver


fun main() {
    val dataReader = DataReader("C://Users//N1o//0projects//IntellijIdea//kt//Numerical_Methods_6_semester//src//res//data.txt")
    val data = dataReader.getData()
    val solver = Solver(data,
        "2x",
        "3x",
        "x",
    )
    solver.solve()

}
