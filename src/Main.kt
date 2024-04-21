import entities.IntegrationOrdinaryDifferentialEquation


fun main() {

    val solver: IntegrationOrdinaryDifferentialEquation =
        IntegrationOrdinaryDifferentialEquation(
            "2*x+y+1",
            "C:\\Users\\N1o\\0projects\\IntellijIdea\\kt\\Numerical_Methods_6_semester\\src\\res\\data.txt"
        )

    solver.integrate()

}
