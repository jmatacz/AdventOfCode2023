package day4

data class ScratchCardsPile(
    val cards: List<ScratchCard>,
    val points: Int
) {
    companion object {
        operator fun invoke(input: String): ScratchCardsPile {
            val cards = input.split("\n")
                .map { ScratchCard(it) }

            val points = cards.sumOf { it.points }

            return ScratchCardsPile(cards, points)
        }
    }

    private fun findCardWithId(id: Int): ScratchCard? =
        cards.firstOrNull { it.id == id }

    fun getCardWithId(id: Int): ScratchCard =
        findCardWithId(id) ?: throw NoSuchElementException("Card $id not found")

    fun wonCopies(): List<ScratchCard> {
        var currentId = cards.first().id
        val maxId = cards.maxBy { it.id }.id
        val copies = cards.associate { it.id to mutableListOf(it) }
            .toMutableMap()

        while (currentId != maxId) {
            val copiesForProcessing = copies[currentId] ?: throw IllegalStateException("Card $currentId not found")

            for(i in 0 until copiesForProcessing.size) {
                val card = copiesForProcessing[i]

                if (card.foundNumbers > 0) {
                    for (cardIdToAdd in currentId + 1..currentId + card.foundNumbers) {
                        copies.addCopyOfCard(cardIdToAdd)
                    }
                }
            }

            currentId++
        }

        return copies.entries
            .flatMap { it.value }
    }

    // more performant way for only count
    fun wonCopiesCount(): Int {
        var currentId = cards.first().id
        val maxId = cards.maxBy { it.id }.id
        val cardCounters = cards.associate { it.id to 1 }
            .toMutableMap()

        while (currentId != maxId) {
            val numberOfCardsToProcess = cardCounters[currentId] ?: throw IllegalStateException("Card counter for id $currentId not found")

            for(i in 0 until numberOfCardsToProcess) {
                val card = getCardWithId(currentId)

                if (card.foundNumbers > 0) {
                    for (cardIdToAdd in currentId + 1..currentId + card.foundNumbers) {
                        cardCounters.increaseCounterOfCard(cardIdToAdd)
                    }
                }
            }

            currentId++
        }

        return cardCounters.entries.sumOf { (_, cardCounter) -> cardCounter }
    }

    private fun MutableMap<Int, Int>.increaseCounterOfCard(cardIdToAdd: Int) {
        this[cardIdToAdd] = this[cardIdToAdd]?.plus(1) ?: throw IllegalStateException("Card counter not found")
    }

    private fun MutableMap<Int, MutableList<ScratchCard>>.addCopyOfCard(cardIdToAdd: Int) {
        val copiedCard = findCardWithId(cardIdToAdd)?.copy()

        copiedCard?.let {
            val copiesOfCard = getOrDefault(it.id, mutableListOf())
            copiesOfCard.add(it)
            put(it.id, copiesOfCard)
        }
    }
}
