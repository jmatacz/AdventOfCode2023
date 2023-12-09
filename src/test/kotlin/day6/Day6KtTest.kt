package day6

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.lang.IllegalStateException

class Day6KtTest {

    @Test
    fun parseRace() {
        val input = """
            Time:      7  15   30
            Distance:  9  40  200
        """.trimIndent()

        val race = Race(input)

        assertEquals(71530, race.time)
        assertEquals(940200, race.distance)
    }

    @Test
    fun parseRaces() {
        val input = """
            Time:      7  15   30
            Distance:  9  40  200
        """.trimIndent()

        val races = Race.parseRaces(input)

        races.hasNumberOfRaces(3)
        races[0]
            .inTime(7)
            .withDistance(9)
        races[1]
            .inTime(15)
            .withDistance(40)
        races[2]
            .inTime(30)
            .withDistance(200)
    }

    @Test
    fun calculateDistanceAfterHoldingButton() {
        val races = Race.parseRaces(
            """
            Time:      7  15   30
            Distance:  9  40  200
        """.trimIndent()
        )

        val race = races[0]
        assertEquals(0, race.calculateDistanceAfterHoldingButton(0))
        assertEquals(6, race.calculateDistanceAfterHoldingButton(1))
        assertEquals(10, race.calculateDistanceAfterHoldingButton(2))
        assertEquals(12, race.calculateDistanceAfterHoldingButton(3))
        assertEquals(12, race.calculateDistanceAfterHoldingButton(4))
        assertEquals(10, race.calculateDistanceAfterHoldingButton(5))
        assertEquals(6, race.calculateDistanceAfterHoldingButton(6))
        assertEquals(0, race.calculateDistanceAfterHoldingButton(7))
        assertThrows(IllegalStateException::class.java) { race.calculateDistanceAfterHoldingButton(8) }
    }

    @Test
    fun calculateWaysOfWinNaively() {
        val races = Race.parseRaces(
            """
            Time:      7  15   30
            Distance:  9  40  200
        """.trimIndent()
        )

        assertEquals(4, races[0].calculateWaysOfWinNaively())
        assertEquals(8, races[1].calculateWaysOfWinNaively())
        assertEquals(9, races[2].calculateWaysOfWinNaively())
    }

    @Test
    fun calculateWaysOfWinSmarter() {
        val races = Race.parseRaces(
            """
            Time:      7  15   30
            Distance:  9  40  200
        """.trimIndent()
        )

        assertEquals(4, races[0].calculateWaysOfWinSmarter())
        assertEquals(8, races[1].calculateWaysOfWinSmarter())
        assertEquals(9, races[2].calculateWaysOfWinSmarter())
    }

    private fun List<Race>.hasNumberOfRaces(numberOfRaces: Int) {
        assertEquals(numberOfRaces, size)
    }

    private fun Race.inTime(time: Long): Race {
        assertEquals(time, this.time)
        return this
    }

    private fun Race.withDistance(distance: Long) {
        assertEquals(distance, this.distance)
    }
}
