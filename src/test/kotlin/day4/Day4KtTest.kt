package day4

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.assertContentEquals

class Day4KtTest {

    @Test
    fun scratchCardParse() {
        val input = """
            Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53
            Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19
            Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1
            Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83
            Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36
            Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11
        """.trimIndent()

        val pile = ScratchCardsPile(input)

        pile.getCardWithId(1)
            .haveWinningNumbers(41, 48, 83, 86, 17)
            .haveScratchedNumbers(83, 86, 6, 31, 17, 9, 48, 53)
            .havePoints(8)

        pile.getCardWithId(2)
            .haveWinningNumbers(13, 32, 20, 16, 61)
            .haveScratchedNumbers(61, 30, 68, 82, 17, 32, 24, 19)
            .havePoints(2)

        pile.getCardWithId(3)
            .haveWinningNumbers(1, 21, 53, 59, 44)
            .haveScratchedNumbers(69, 82, 63, 72, 16, 21, 14, 1)
            .havePoints(2)

        pile.getCardWithId(4)
            .haveWinningNumbers(41, 92, 73, 84, 69)
            .haveScratchedNumbers(59, 84, 76, 51, 58, 5, 54, 83)
            .havePoints(1)

        pile.getCardWithId(5)
            .haveWinningNumbers(87, 83, 26, 28, 32)
            .haveScratchedNumbers(88, 30, 70, 12, 93, 22, 82, 36)
            .havePoints(0)

        pile.getCardWithId(6)
            .haveWinningNumbers(31, 18, 13, 56, 72)
            .haveScratchedNumbers(74, 77, 10, 23, 35, 67, 36, 11)
            .havePoints(0)

        assertEquals(13, pile.points)
        assertEquals(30, pile.wonCopies().size)
        assertEquals(30, pile.wonCopiesCount())
    }
}

private fun ScratchCard.haveWinningNumbers(vararg numbers: Int): ScratchCard {
    assertEquals(numbers.size, winningNumbers.size)
    assertContentEquals(winningNumbers, numbers.toList())

    return this
}

private fun ScratchCard.haveScratchedNumbers(vararg numbers: Int): ScratchCard {
    assertEquals(numbers.size, scratchedNumbers.size)
    assertContentEquals(scratchedNumbers, numbers.toList())

    return this
}

private fun ScratchCard.havePoints(points: Int) {
    assertEquals(points, this.points)
}
