package day6

data class Race(
    val time: Long,
    val distance: Long
) {
    companion object {
        operator fun invoke(input: String): Race {
            val time = input.parseLine("Time:")
                .joinToString("")
                .toLong()

            val distance = input.parseLine("Distance:")
                .joinToString("")
                .toLong()

            return Race(time, distance)
        }

        fun parseRaces(input: String): List<Race> {
            val times = input.parseLine("Time:")
            val distances = input.parseLine("Distance:")

            if (times.size != distances.size) {
                throw IllegalStateException("Incorrect input file. Number of times (${times.size}) is not the same as number of distances (${distances.size})")
            }

            return times.mapIndexed { i, time -> Race(time, distances[i]) }
        }

        private fun String.parseLine(title: String): List<Long> =
            split("\n")
                .first { it.startsWith(title) }
                .replace(title, "")
                .split(" ")
                .map { it.trim() }
                .filter { it.isNotEmpty() }
                .map { it.toLong() }
    }

    fun calculateDistanceAfterHoldingButton(holdingButtonTime: Long): Long {
        if (holdingButtonTime > time) {
            throw IllegalStateException("You hold the button for too long ($holdingButtonTime > $time)")
        }

        return holdingButtonTime * (time - holdingButtonTime)
    }

    fun calculateWaysOfWinNaively(): Int =
        (1 until time)
            .map { calculateDistanceAfterHoldingButton(it) }
            .count { it > distance }

    fun calculateWaysOfWinSmarter(): Long {
        val firstThatWins = findFirstThatWins()
        val lastThatWins = findLastThatWins()

        return lastThatWins - firstThatWins + 1
    }

    private fun findFirstThatWins(): Long =
        findFirstWinInRange(range = LongProgression.fromClosedRange(1, time - 1, 1))

    private fun findLastThatWins(): Long =
        findFirstWinInRange(range = time - 1 downTo 1)

    private fun findFirstWinInRange(range: LongProgression): Long {
        for (i in range) {
            val calculatedDistance = calculateDistanceAfterHoldingButton(i)

            if (calculatedDistance > distance) {
                return i
            }
        }

        throw IllegalStateException("There is no way to win this race: $this")
    }
}
