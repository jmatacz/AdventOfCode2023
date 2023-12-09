package day7

enum class CardType {
    // values ordered by the value of card
    A, K, Q, T, NINE, EIGHT, SEVEN, SIX, FIVE, FOUR, THREE, TWO, J;

    companion object {
        private val mapping = mapOf(
            'A' to A,
            'K' to K,
            'Q' to Q,
            'J' to J,
            'T' to T,
            '9' to NINE,
            '8' to EIGHT,
            '7' to SEVEN,
            '6' to SIX,
            '5' to FIVE,
            '4' to FOUR,
            '3' to THREE,
            '2' to TWO
        )

        fun valueOf(card: Char): CardType =
            mapping[card] ?: throw IllegalArgumentException("Card $card not found")
    }
}