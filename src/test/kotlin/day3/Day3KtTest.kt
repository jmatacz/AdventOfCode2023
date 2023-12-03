package day3

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import kotlin.test.assertContains

class Day3KtTest {

    @Test
    fun findParts() {
        val engineManual = EngineManual.create(
            """
            467..114..
            ...*......
            ..35..633.
            ......#...
            617*......
            .....+.58.
            ..592.....
            ......755.
            ...${'$'}.*....
            .664.598..
        """.trimIndent()
        )

        val parts = engineManual.findPartNumbers()

        assertContains(parts, 467)
        assertContains(parts, 35)
        assertContains(parts, 633)
        assertContains(parts, 617)
        assertContains(parts, 592)
        assertContains(parts, 755)
        assertContains(parts, 664)
        assertContains(parts, 598)
        assertEquals(8, parts.size)
    }

    @Test
    fun findGears() {
        val engineManual = EngineManual.create(
            """
            467..114..
            ...*......
            ..35..633.
            ......#...
            617*......
            .....+.58.
            ..592.....
            ......755.
            ...${'$'}.*....
            .664.598..
        """.trimIndent()
        )

        val gears = engineManual.findGears()

        assertEquals(2, gears.size)
        assertEquals(16345, gears[0].ratio)
        assertEquals(451490, gears[1].ratio)
    }
}