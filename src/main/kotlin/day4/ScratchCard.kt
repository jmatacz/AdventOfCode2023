package day4

import kotlin.math.pow

data class ScratchCard(
    val id: Int,
    val winningNumbers: List<Int>,
    val scratchedNumbers: List<Int>,
    val foundNumbers: Int
) {
    companion object {
        private val scratchCardRegex = Regex("Card\\s*(\\d+):(.*)\\|(.*)")
        private val scratchNumberRegex = Regex("\\s*(\\d+)")

        operator fun invoke(input: String): ScratchCard {
            val groups = scratchCardRegex.find(input)
                ?.groupValues
                ?: throw IllegalStateException("Incorrect format")

            val id = groups[1].toInt()
            val winningNumbers = findNumbers(groups[2])
            val scratchedNumbers = findNumbers(groups[3])
            val foundNumbers = winningNumbers.intersect(scratchedNumbers.toSet()).size

            return ScratchCard(id, winningNumbers, scratchedNumbers, foundNumbers)
        }

        private fun findNumbers(input: String): List<Int> =
            scratchNumberRegex.findAll(input)
                .map { it.groupValues[1] }
                .map { it.toInt() }
                .toList()
    }

    val points: Int
        get() =
            if (foundNumbers == 0) {
                0
            } else {
                2.toDouble().pow((foundNumbers - 1).toDouble()).toInt()
            }
}
