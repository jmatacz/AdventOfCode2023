package day2

import day2.GameSet.Companion.gameSets

data class Game(
    val id: Int,
    val sets: List<GameSet>
) {
    companion object {
        private val gameIdRegex = Regex("Game (\\d*): ")

        fun games(input: String) : List<Game> =
            input.split("\n")
                .map { game(it) }

        private fun game(input: String): Game {
            val id = gameIdRegex.find(input)
                ?.groups
                ?.get(1)
                ?.value
                ?.toInt()!!

            val sets = gameSets(input.replace(gameIdRegex, ""))

            return Game(id, sets)
        }
    }

    fun minimalSetToPlay(): GameSet {
        val red = sets.maxBy { it.red }.red
        val green = sets.maxBy { it.green }.green
        val blue = sets.maxBy { it.blue }.blue

        return GameSet(red, green, blue)
    }

    fun isPossible(red: Int, green: Int, blue: Int): Boolean =
        sets.all { it.isPossible(red, green, blue) }
}