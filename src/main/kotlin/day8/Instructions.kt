package day8

import java.lang.IllegalStateException

private const val EXIT = "ZZZ"

data class Instructions(
    val steps: String,
    val nodes: Map<String, Pair<String, String>>
) {
    companion object {
        private val nodeRegex = Regex("(.*?)\\s*=\\s\\((.*?)\\s*,\\s*(.*?)\\)")

        operator fun invoke(input: String): Instructions {
            val lines = input.split("\n")
            val steps = lines.first()
            val nodes = lines.filter { it != steps && it.trim() != "" }
                .associate { it.toNode() }

            return Instructions(steps, nodes)
        }

        private fun String.toNode(): Pair<String, Pair<String, String>> =
            nodeRegex.find(this)
                ?.let { it.groupValues[1] to (it.groupValues[2] to it.groupValues[3]) }
                ?: throw IllegalArgumentException("Incorrect format of instruction: $this")
    }

    fun findNumberOfStepsToExitAsHuman(): Int =
        findNumberOfStepsToExitAsHuman("AAA") { step -> step != "ZZZ" }

    private fun findNumberOfStepsToExitAsHuman(initialStep: String, isNotFinishedStep: (String) -> Boolean): Int {
        var numberOfSteps = 0
        var currentStep = initialStep

        while (isNotFinishedStep(currentStep)) {
            for (i in steps.indices) {
                val nextStep = findNextStep(i, currentStep) ?: throw IllegalStateException("Step $currentStep not found")
                currentStep = nextStep
                numberOfSteps++
            }
        }

        return numberOfSteps
    }

    fun findNumberOfStepsToExitAsGhostUsingLowestCommonMultiple(): Long =
        findStartSteps()
            .map { findNumberOfStepsToExitAsHuman(it) { step -> !step.endsWith("Z") } }
            .map { it.toLong() }
            .let { findLowestCommonMultiple(it) }

    fun findNumberOfStepsToExitAsGhostNaively(): Long {
        var numberOfSteps = 0L
        val currentSteps = findStartSteps()

        while (currentSteps.allStepsAreNotExitSteps()) {
            for ((stepIndex, _) in currentSteps.withIndex()) {
                for (i in steps.indices) {
                    val nextStep = findNextStep(i, currentSteps[stepIndex]) ?: throw IllegalStateException("Step $currentSteps not found")
                    currentSteps[stepIndex] = nextStep
                    numberOfSteps++
                }
            }
        }

        return numberOfSteps / currentSteps.size
    }

    private fun findNextStep(index: Int, step: String): String? {
        val turn = steps[index]

        return if (turn.isLeft()) {
            nodes[step]?.first
        } else if (turn.isRight()) {
            nodes[step]?.second
        } else {
            null
        }
    }

    private fun findStartSteps(): MutableList<String> =
        nodes.filter { it.key.endsWith('A') }
            .map { it.key }
            .toMutableList()

    private fun List<String>.allStepsAreNotExitSteps(): Boolean =
        any { !it.endsWith('Z') }

    private fun Char.isRight(): Boolean =
        this == 'R'

    private fun Char.isLeft(): Boolean =
        this == 'L'
}

private fun findLowestCommonMultiple(a: Long, b: Long): Long {
    val larger = if (a > b) {
        a
    } else {
        b
    }

    val maxLcm = a * b
    var lcm = larger
    while (lcm <= maxLcm) {
        if (lcm % a == 0L && lcm % b == 0L) {
            return lcm
        }
        lcm += larger
    }

    return maxLcm
}

private fun findLowestCommonMultiple(numbers: List<Long>): Long {
    var result = numbers[0]

    for (i in 1 until numbers.size) {
        result = findLowestCommonMultiple(result, numbers[i])
    }

    return result
}
