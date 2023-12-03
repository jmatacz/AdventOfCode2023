package day3

data class Gear(
    private val parts: List<Int>
) {
    val ratio: Int
        get() = parts.reduce { accumulator, element -> accumulator * element }

    companion object {
        private val gearRegex = Regex("(\\*)")

        fun gears(lineNumber: Int, line: String, parts: List<Part>): List<Gear> =
            gearRegex.findAll(line)
                .map { it.gear(lineNumber, parts) }
                .filterNotNull()
                .toList()

        private fun MatchResult.gear(lineNumber: Int, parts: List<Part>): Gear? {
            val partsNearGear = parts.findPartsNearGear(lineNumber, range)

            return if (partsNearGear.size == 2) {
                Gear(partsNearGear.map { it.partNumber })
            } else {
                null
            }
        }

        private fun List<Part>.findPartsNearGear(lineNumber: Int, range: IntRange): List<Part> =
            filter { it.lineNumber == lineNumber || it.lineNumber == lineNumber - 1 || it.lineNumber == lineNumber + 1 }
                .filter { it.range.intersect(IntRange(range.first - 1, range.last + 1)).isNotEmpty() }
    }
}
