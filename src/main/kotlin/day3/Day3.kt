package day3

fun main() {
    val engineManual = EngineManual.create(day3Input)
    val parts = engineManual.findPartNumbers()
    val sumOfParts = parts.sum()

    println(sumOfParts)

    val gears = engineManual.findGears()
    val sumOfRatios = gears.sumOf { it.ratio }

    println(sumOfRatios)
}
