package day5

fun main() {
    val almanac = Almanac(day5Input)
    val lowestLocation = almanac.getInstructionsForInitialSeeds()
        .minBy { it.last() }
        .last()

    println(lowestLocation)

    println(almanac.getLowestLocationNumberForSeedRangesNaively())
}
