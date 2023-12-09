package day7

import day7.CardType.A
import day7.CardType.FIVE
import day7.CardType.J
import day7.CardType.K
import day7.CardType.Q
import day7.CardType.SEVEN
import day7.CardType.SIX
import day7.CardType.T
import day7.CardType.THREE
import day7.CardType.TWO
import day7.HandType.ONE_PAIR
import day7.HandType.TWO_PAIRS
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class Day7KtTest {

    @Test
    fun parseHands() {
        val input = """
            32T3K 765
            T55J5 684
            KK677 28
            KTJJT 220
            QQQJA 483
        """.trimIndent()

        val hands = Hands(input)

        assertEquals(5, hands.hands.size)
        hands[0].hasCards(THREE, TWO, T, THREE, K)
            .hasType(ONE_PAIR)
            .withBid(765)
        hands[1].hasCards(T, FIVE, FIVE, J, FIVE)
            .hasType(HandType.THREE)
            .withBid(684)
        hands[2].hasCards(K, K, SIX, SEVEN, SEVEN)
            .hasType(TWO_PAIRS)
            .withBid(28)
        hands[3].hasCards(K, T, J, J, T)
            .hasType(TWO_PAIRS)
            .withBid(220)
        hands[4].hasCards(Q, Q, Q, J, A)
            .hasType(HandType.THREE)
            .withBid(483)
    }

    @Test
    fun compare() {
        val hands = Hands(
            """
            32T3K 765
            T55J5 684
            KK677 28
            KTJJT 220
            QQQJA 483
        """.trimIndent()
        )

        val sorted = hands.ranked

        sorted[0].hasCards(THREE, TWO, T, THREE, K)
        sorted[1].hasCards(K, T, J, J, T)
        sorted[2].hasCards(K, K, SIX, SEVEN, SEVEN)
        sorted[3].hasCards(T, FIVE, FIVE, J, FIVE)
        sorted[4].hasCards(Q, Q, Q, J, A)
    }

    @Test
    fun winnings() {
        val hands = Hands(
            """
            32T3K 765
            T55J5 684
            KK677 28
            KTJJT 220
            QQQJA 483
        """.trimIndent()
        )

        val winnings = hands.winnings

        assertEquals(6440, winnings)
    }

    /* if these tests pass (new rules of sorting) the other will not */
    @Test
    fun winningsAccordingToTheNewJokerRule() {
        val hands = Hands(
            """
            32T3K 765
            T55J5 684
            KK677 28
            KTJJT 220
            QQQJA 483
        """.trimIndent()
        )

        val winnings = hands.winnings

        assertEquals(5905, winnings)
    }

    @Test
    fun newHandTypeCalculation() {
        assertEquals(HandType.FIVE, HandType.calculateUsingNewRules("JJJJJ"))

        assertEquals(HandType.FIVE, HandType.calculateUsingNewRules("2222J"))
        assertEquals(HandType.FOUR, HandType.calculateUsingNewRules("22223"))
        assertEquals(HandType.FIVE, HandType.calculateUsingNewRules("JJJJ2"))

        assertEquals(HandType.FIVE, HandType.calculateUsingNewRules("222JJ"))
        assertEquals(HandType.FIVE, HandType.calculateUsingNewRules("JJJ22"))
        assertEquals(HandType.FULL_HOUSE, HandType.calculateUsingNewRules("33322"))

        assertEquals(HandType.FOUR, HandType.calculateUsingNewRules("JJJ23"))
        assertEquals(HandType.FOUR, HandType.calculateUsingNewRules("3332J"))

        assertEquals(HandType.THREE, HandType.calculateUsingNewRules("33324"))
        assertEquals(HandType.FOUR, HandType.calculateUsingNewRules("333J4"))
        assertEquals(HandType.FOUR, HandType.calculateUsingNewRules("JJJ24"))

        assertEquals(HandType.HIGH_CARD, HandType.calculateUsingNewRules("23456"))

        assertEquals(HandType.ONE_PAIR, HandType.calculateUsingNewRules("23256"))
        assertEquals(HandType.THREE, HandType.calculateUsingNewRules("J3J56"))
        assertEquals(HandType.THREE, HandType.calculateUsingNewRules("2J256"))
        assertEquals(HandType.ONE_PAIR, HandType.calculateUsingNewRules("23J56"))
        assertEquals(HandType.THREE, HandType.calculateUsingNewRules("23J5J"))

        assertEquals(HandType.TWO_PAIRS, HandType.calculateUsingNewRules("22334"))
        assertEquals(HandType.FULL_HOUSE, HandType.calculateUsingNewRules("2233J"))
        assertEquals(HandType.FOUR, HandType.calculateUsingNewRules("JJ334"))

        assertEquals(HandType.ONE_PAIR, HandType.calculateUsingNewRules("22345"))
        assertEquals(HandType.THREE, HandType.calculateUsingNewRules("223J5"))
        assertEquals(HandType.THREE, HandType.calculateUsingNewRules("JJ345"))
    }
}

private fun Hand.withBid(bid: Int) {
    assertEquals(bid, this.bid)
}

private fun Hand.hasType(type: HandType): Hand {
    assertEquals(type, this.type)
    return this
}

private fun Hand.hasCards(vararg cards: CardType): Hand {
    assertEquals(cards.size, this.cards.size)

    for (i in cards.indices) {
        assertEquals(cards[i], this.cards[i])
    }

    return this
}
