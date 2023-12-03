package day2

import day2.Game.Companion.games

fun main() {
    val sumOfIds = games(day2Input)
        .findPossibleGames(red = 12, green = 13, blue = 14)
        .sum()

    println(sumOfIds)

    val sumOfPowerOfMinimalSets = games(day2Input)
        .map { it.minimalSetToPlay() }
        .sumOf { it.powerOfSet() }

    println(sumOfPowerOfMinimalSets)
}

fun List<Game>.findPossibleGames(red: Int, green: Int, blue: Int): List<Int> =
    filter { it.isPossible(red, green, blue) }
        .map { it.id }
