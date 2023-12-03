package day1

fun main() {
    val sum = day1Input.calculateSumOfDigits()
    println(sum)
}

fun String.calculateSumOfDigits(): Long =
    split("\n")
        .asSequence()
        .map { it.trimIndent() }
        .map { it.findDigits() }
        .map { "${it.first()}${it.last()}" }
        .map { it.toLong() }
        .sum()

private fun String.findDigits(): List<String> =
    mapper.entries
        .flatMap { findAllOccurrencesOfDigit(it)}
        .filter { it.first != -1 }
        .sortedBy { it.first }
        .map { mapper[it.second]!! }

private fun String.findAllOccurrencesOfDigit(digitMapping: Map.Entry<String, String>): MutableList<Pair<Int, String>> {
    val occurrences = mutableListOf<Pair<Int, String>>()
    var indexOfDigit = this.indexOf(digitMapping.key, ignoreCase = true)

    while (indexOfDigit != -1) {
        occurrences.add(indexOfDigit to digitMapping.key)
        indexOfDigit = this.indexOf(digitMapping.key, indexOfDigit + 1, ignoreCase = true)
    }

    return occurrences
}

private val mapper =
    mapOf(
        "1" to "1",
        "one" to "1",

        "2" to "2",
        "two" to "2",

        "3" to "3",
        "three" to "3",

        "4" to "4",
        "four" to "4",

        "5" to "5",
        "five" to "5",

        "6" to "6",
        "six" to "6",

        "7" to "7",
        "seven" to "7",

        "8" to "8",
        "eight" to "8",

        "9" to "9",
        "nine" to "9"
    )