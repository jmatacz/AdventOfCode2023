package day1

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

class Day1KtTest {

    @Test
    fun example1() {
        val input = """
            two1nine
            eightwothree
            abcone2threexyz
            xtwone3four
            4nineeightseven2
            zoneight234
            7pqrstsixteen
        """.trimIndent()

        val sum = input.calculateSumOfDigits()

        assertEquals(281, sum)
    }

    @Test
    fun example2() {
        val input = """
            1abc2
            pqr3stu8vwx
            a1b2c3d4e5f
            treb7uchet
        """.trimIndent()

        val sum = input.calculateSumOfDigits()

        assertEquals(142, sum)
    }

    @Test
    fun example3() {
        val parameters = mapOf<String, Long>(
            "two1nine" to 29,
            "eightwothree" to 83,
            "abcone2threexyz" to 13,
            "xtwone3four" to 24,
            "4nineeightseven2" to 42,
            "zoneight234" to 14,
            "7pqrstsixteen" to 76,
            "1abc2" to 12,
            "pqr3stu8vwx" to 38,
            "a1b2c3d4e5f" to 15,
            "treb7uchet" to 77,
            "fourthreefour" to 44,
            "four" to 44,
            "twone" to 21
        )

        parameters.forEach { (input, output) ->
            val sum = input.calculateSumOfDigits()

            assertEquals(output, sum)
        }
    }
}