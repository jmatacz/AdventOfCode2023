package day8

fun main() {
    val instructions = Instructions(day8Input)

    println(instructions.findNumberOfStepsToExitAsHuman())
    println(instructions.findNumberOfStepsToExitAsGhostUsingLowestCommonMultiple())
}
