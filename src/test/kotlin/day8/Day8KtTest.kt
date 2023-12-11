package day8

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day8KtTest {

    @Test
    fun parseInstructions() {
        val input = """
            RL

            AAA = (BBB, CCC)
            BBB = (DDD, EEE)
            CCC = (ZZZ, GGG)
            DDD = (DDD, DDD)
            EEE = (EEE, EEE)
            GGG = (GGG, GGG)
            ZZZ = (ZZZ, ZZZ)
        """.trimIndent()

        val instructions = Instructions(input)

        assertEquals("RL", instructions.steps)
        assertEquals(7, instructions.nodes.size)
        assertEquals("ZZZ", instructions.nodes["CCC"]?.first)
        assertEquals("GGG", instructions.nodes["CCC"]?.second)
        assertEquals("GGG", instructions.nodes["GGG"]?.first)
        assertEquals("GGG", instructions.nodes["GGG"]?.second)
    }

    @Test
    fun findNumberOfStepsToExit() {
        val instructions = Instructions(
            """
            RL

            AAA = (BBB, CCC)
            BBB = (DDD, EEE)
            CCC = (ZZZ, GGG)
            DDD = (DDD, DDD)
            EEE = (EEE, EEE)
            GGG = (GGG, GGG)
            ZZZ = (ZZZ, ZZZ)
        """.trimIndent()
        )

        val numberOfStepsToExit = instructions.findNumberOfStepsToExitAsHuman()

        assertEquals(2, numberOfStepsToExit)
    }

    @Test
    fun findNumberOfStepsToExit2() {
        val instructions = Instructions(
            """
            LLR
            
            AAA = (BBB, BBB)
            BBB = (AAA, ZZZ)
            ZZZ = (ZZZ, ZZZ)
        """.trimIndent()
        )

        val numberOfStepsToExit = instructions.findNumberOfStepsToExitAsHuman()

        assertEquals(6, numberOfStepsToExit)
    }

    @Test
    fun findNumberOfStepsToExit3() {
        val instructions = Instructions(
            """
            LR
            
            11A = (11B, XXX)
            11B = (XXX, 11Z)
            11Z = (11B, XXX)
            22A = (22B, XXX)
            22B = (22C, 22C)
            22C = (22Z, 22Z)
            22Z = (22B, 22B)
            XXX = (XXX, XXX)
        """.trimIndent()
        )

        val numberOfStepsToExit = instructions.findNumberOfStepsToExitAsGhostNaively()

        assertEquals(6, numberOfStepsToExit)
    }

    @Test
    fun findNumberOfStepsToExit4() {
        val instructions = Instructions(
            """
            LR
            
            11A = (11B, XXX)
            11B = (XXX, 11Z)
            11Z = (11B, XXX)
            22A = (22B, XXX)
            22B = (22C, 22C)
            22C = (22Z, 22Z)
            22Z = (22B, 22B)
            XXX = (XXX, XXX)
        """.trimIndent()
        )

        val numberOfStepsToExit = instructions.findNumberOfStepsToExitAsGhostUsingLowestCommonMultiple()

        assertEquals(6, numberOfStepsToExit)
    }
}