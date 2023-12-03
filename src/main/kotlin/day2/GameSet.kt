package day2

data class GameSet(
    val red: Int,
    val green: Int,
    val blue: Int
) {
    companion object {
        private val blueInSetRegex = Regex("(\\d*) blue")
        private val greenInSetRegex = Regex("(\\d*) green")
        private val redInSetRegex = Regex("(\\d*) red")

        fun gameSets(input: String): List<GameSet> =
            input.split(";")
                .map { gameSet(it) }

        private fun gameSet(input: String): GameSet {
            val blue = input.extractCubesCount(blueInSetRegex)
            val green = input.extractCubesCount(greenInSetRegex)
            val red = input.extractCubesCount(redInSetRegex)

            return GameSet(red, green, blue)
        }
    }

    fun powerOfSet(): Int =
        red * green * blue

    fun isPossible(red: Int, green: Int, blue: Int) =
        this.red <= red && this.green <= green && this.blue <= blue
}

private fun String.extractCubesCount(color: Regex) =
    color.find(this)
        ?.groups
        ?.get(1)
        ?.value
        ?.toInt()
        ?: 0