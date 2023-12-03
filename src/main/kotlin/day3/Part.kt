package day3

data class Part(
    val lineNumber: Int,
    val range: IntRange,
    val partNumber: Int
) {
    companion object {
        private val partIdRegex = Regex("(\\d+)")

        fun parts(lineNumber: Int, line: String, engineManual: EngineManual): List<Part> =
            partIdRegex.findAll(line)
                .filter { it.isPart(lineNumber, engineManual) }
                .map { it.part(lineNumber) }
                .toList()

        private fun MatchResult.isPart(lineNumber: Int, engineManual: EngineManual): Boolean =
            engineManual.hasSymbolOnLeftSide(range.first, lineNumber)
                    || engineManual.hasSymbolUpOrDown(range, lineNumber)
                    || engineManual.hasSymbolOnRightSide(range.last, lineNumber)

        private fun MatchResult.part(lineNumber: Int): Part =
            Part(lineNumber, range, value.toInt())
    }
}
