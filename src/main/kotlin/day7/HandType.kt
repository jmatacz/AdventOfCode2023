package day7

enum class HandType {
    // values ordered by the value of hand
    FIVE, FOUR, FULL_HOUSE, THREE, TWO_PAIRS, ONE_PAIR, HIGH_CARD;

    companion object {
        fun calculateUsingNewRules(cards: String): HandType {
            val grouped = cards.groupBy { it }
                .map { it.key to it.value.count() }

            if (grouped.any { it.second == 5 }) {
                return FIVE
            } else if (grouped.any { it.second == 4 }) {
                return if (grouped.hasJoker()) {
                    FIVE
                } else {
                    FOUR
                }
            } else if (grouped.any { it.second == 3 } && grouped.any { it.second == 2 }) {
                return if (grouped.hasJoker()) {
                    FIVE
                } else {
                    FULL_HOUSE
                }
            } else if (grouped.any { it.second == 3 }) {
                return if (grouped.hasJoker()) {
                    FOUR
                } else {
                    THREE
                }
            } else if (grouped.all { it.second == 1 }) {
                return if (grouped.hasJoker()) {
                    ONE_PAIR
                } else {
                    HIGH_CARD
                }
            } else {
                val pairs = grouped.filter { it.second == 2 }

                return if (pairs.count() == 2) {
                    if (grouped.hasJoker() && grouped.hasNumberOfJokers(1)) {
                        FULL_HOUSE
                    } else if(grouped.hasJoker() && grouped.hasNumberOfJokers(2)) {
                        FOUR
                    } else {
                        TWO_PAIRS
                    }
                } else {
                    if(grouped.hasJoker()) {
                        THREE
                    } else {
                        ONE_PAIR
                    }
                }
            }
        }

        private fun List<Pair<Char, Int>>.hasNumberOfJokers(numberOfJokers: Int): Boolean =
            first { it.first == 'J' }.second == numberOfJokers

        private fun List<Pair<Char, Int>>.hasJoker(): Boolean =
            any { it.first == 'J' }

        fun calculate(cards: String): HandType {
            val grouped = cards.groupBy { it }
                .map { it.key to it.value.count() }

            if (grouped.any { it.second == 5 }) {
                return FIVE
            } else if (grouped.any { it.second == 4 }) {
                return FOUR
            } else if (grouped.any { it.second == 3 } && grouped.any { it.second == 2 }) {
                return FULL_HOUSE
            } else if (grouped.any { it.second == 3 }) {
                return THREE
            } else if (grouped.all { it.second == 1 }) {
                return HIGH_CARD
            } else {
                val pairs = grouped.filter { it.second == 2 }

                return if (pairs.count() == 2) {
                    TWO_PAIRS
                } else {
                    ONE_PAIR
                }
            }
        }
    }
}