package day7

data class Hands(
    val hands: List<Hand>
) {
    companion object {
        operator fun invoke(input: String): Hands =
            input.split("\n")
                .map { Hand(it) }
                .let { Hands(it) }
    }

    val ranked: List<Hand>
        get() = hands.sortedBy { it }

    operator fun get(index: Int): Hand =
        hands[index]

    val winnings: Int
        get() = ranked.mapIndexed{ index, hand -> hand.bid * (index + 1) }
            .sum()
}
