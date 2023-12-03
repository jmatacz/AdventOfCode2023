package day3

data class EngineManual(
    private val content: List<String>
) {
    companion object {
        fun create(content: String) =
            EngineManual(content.split("\n"))
    }

    fun findPartNumbers(): List<Int> =
        findParts().map { it.partNumber }

    private fun findParts(): List<Part> =
        content.flatMapIndexed { index, line -> Part.parts(index, line, this) }

    fun findGears(): List<Gear> {
        val parts = findParts()

        return content.flatMapIndexed { index, line -> Gear.gears(index, line, parts) }
    }

    fun hasSymbolOnLeftSide(index: Int, lineNumber: Int): Boolean =
        if (index > 0) {
            isSymbolInAnyLine(index - 1, lineNumber)
        } else {
            false
        }

    fun hasSymbolUpOrDown(range: IntRange, lineNumber: Int): Boolean {
        for (middleIndex in range) {
            if (isSymbolInAnyLine(middleIndex, lineNumber)) {
                return true
            }
        }

        return false
    }

    fun hasSymbolOnRightSide(index: Int, lineNumber: Int): Boolean =
        if (index + 1 < content[lineNumber].length) {
            isSymbolInAnyLine(index + 1, lineNumber)
        } else {
            false
        }

    private fun isSymbolInAnyLine(index: Int, lineNumber: Int): Boolean {
        if (lineNumber > 0) {
            val symbolInPreviousLine = content[lineNumber - 1].hasSymbol(index)

            if (symbolInPreviousLine) {
                return true
            }
        }

        if (content[lineNumber].hasSymbol(index)) {
            return true
        }

        if (lineNumber + 1 < content.size) {
            val symbolInNextLine = content[lineNumber + 1].hasSymbol(index)

            if (symbolInNextLine) {
                return true
            }
        }

        return false
    }

    private fun String.hasSymbol(index: Int) =
        this[index] != '.' && !this[index].isDigit()
}
