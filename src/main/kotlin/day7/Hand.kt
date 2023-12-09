package day7

data class Hand(
    val cards: List<CardType>,
    val type: HandType,
    val bid: Int
): Comparable<Hand> {
    companion object {
        operator fun invoke(input: String): Hand {
            val (cards, bid) = input.split(" ")

            return Hand(
                cards = cards.map { CardType.valueOf(it) },
                type = HandType.calculateUsingNewRules(cards),
                bid = bid.toInt()
            )
        }
    }

    override operator fun compareTo(other: Hand): Int {
        return if (type == other.type) {
            other.cards.compareCards(cards)
        } else {
            other.type.ordinal.compareTo(type.ordinal)
        }
    }

    private fun List<CardType>.compareCards(cards: List<CardType>): Int {
        for(i in indices) {
            val card = this[i]
            val otherCard = cards[i]
            val comparingResult = card.ordinal.compareTo(otherCard.ordinal)

            if(comparingResult != 0) {
                return comparingResult
            }
        }

        return 0
    }
}
