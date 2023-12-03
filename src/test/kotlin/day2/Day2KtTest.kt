package day2

import day2.Game.Companion.games
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.assertContains

class Day2KtTest {

    @Test
    fun findPossibleGames1() {
        val games = games(
            """
            Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
            Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
            Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
            Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
            Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
        """.trimIndent()
        )

        val possibleGames: List<Int> = games.findPossibleGames(red = 12, green = 13, blue = 14)

        assertEquals(possibleGames.size, 3)
        assertContains(possibleGames, 1)
        assertContains(possibleGames, 2)
        assertContains(possibleGames, 5)
    }

    @Test
    fun findPossibleGames2() {
        val games = games(
            """
            Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
            Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
            Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
            Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
            Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
        """.trimIndent()
        )

        val possibleGames: List<Int> = games.findPossibleGames(red = 1, green = 1, blue = 1)

        assertEquals(possibleGames.size, 0)
    }

    @Test
    fun toGames() {
        val input = """
            Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
            Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
            Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
            Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
            Game 5555: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
        """.trimIndent()

        val games: List<Game> = games(input)

        assertEquals(games.size, 5)

        games.game(1)
            .shouldHaveNumberOfSets(3)
            .shouldHaveSet(red = 4, green = 0, blue = 3)
            .shouldHaveSet(red = 1, green = 2, blue = 6)
            .shouldHaveSet(red = 0, green = 2, blue = 0)


        games.game(2)
            .shouldHaveNumberOfSets(3)
            .shouldHaveSet(red = 0, green = 2, blue = 1)
            .shouldHaveSet(red = 1, green = 3, blue = 4)
            .shouldHaveSet(red = 0, green = 1, blue = 1)

        games.game(3)
            .shouldHaveNumberOfSets(3)
            .shouldHaveSet(red = 20, green = 8, blue = 6)
            .shouldHaveSet(red = 4, green = 13, blue = 5)
            .shouldHaveSet(red = 1, green = 5, blue = 0)

        games.game(4)
            .shouldHaveNumberOfSets(3)
            .shouldHaveSet(red = 3, green = 1, blue = 6)
            .shouldHaveSet(red = 6, green = 3, blue = 0)
            .shouldHaveSet(red = 14, green = 3, blue = 15)

        games.game(5555)
            .shouldHaveNumberOfSets(2)
            .shouldHaveSet(red = 6, green = 3, blue = 1)
            .shouldHaveSet(red = 1, green = 2, blue = 2)
    }

    @Test
    fun minimalSetToPlay() {
        val games = games(
            """
            Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green
            Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue
            Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red
            Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red
            Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green
        """.trimIndent()
        )

        val gamesToMinimalSetToPlay = games.groupBy({ it.id }, { it.minimalSetToPlay() })

        gamesToMinimalSetToPlay[1].shouldBe(red = 4, green = 2, blue = 6, power = 48)
        gamesToMinimalSetToPlay[2].shouldBe(red = 1, green = 3, blue = 4, power = 12)
        gamesToMinimalSetToPlay[3].shouldBe(red = 20, green = 13, blue = 6, power = 1560)
        gamesToMinimalSetToPlay[4].shouldBe(red = 14, green = 3, blue = 15, power = 630)
        gamesToMinimalSetToPlay[5].shouldBe(red = 6, green = 3, blue = 2, power = 36)
    }

    private fun List<Game>.game(id: Int): Game =
        first { it.id == id }

    private fun Game.shouldHaveNumberOfSets(numberOfSets: Int): Game {
        assertEquals(numberOfSets, sets.size)
        return this
    }

    private fun Game.shouldHaveSet(red: Int, green: Int, blue: Int): Game {
        val set = sets.firstOrNull { it.red == red && it.green == green && it.blue == blue }
        assertNotNull(set)
        return this
    }

    private fun List<GameSet>?.shouldBe(red: Int, green: Int, blue: Int, power: Int) {
        assertNotNull(this)
        assertEquals(1, this!!.size)
        assertEquals(red, this[0].red)
        assertEquals(green, this[0].green)
        assertEquals(blue, this[0].blue)
        assertEquals(power, this[0].powerOfSet())
    }
}
