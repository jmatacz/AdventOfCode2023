package day5

data class Almanac(
    val initialSeeds: List<Long>,
    private val seedToSoil: Mappings,
    private val soilToFertilizer: Mappings,
    private val fertilizerToWater: Mappings,
    private val waterToLight: Mappings,
    private val lightToTemperature: Mappings,
    private val temperatureToHumidity: Mappings,
    private val humidityToLocation: Mappings
) {
    companion object {
        operator fun invoke(input: String): Almanac {
            val paragraphs = input.split("\n\n")
            val initialSeeds = extractInitialSeeds(paragraphs)
            val seedToSoil = paragraphs.extractMapping(title = "seed-to-soil map:\n")
            val soilToFertilizer = paragraphs.extractMapping(title = "soil-to-fertilizer map:\n")
            val fertilizerToWater = paragraphs.extractMapping(title = "fertilizer-to-water map:\n")
            val waterToLight = paragraphs.extractMapping(title = "water-to-light map:\n")
            val lightToTemperature = paragraphs.extractMapping(title = "light-to-temperature map:\n")
            val temperatureToHumidity = paragraphs.extractMapping(title = "temperature-to-humidity map:\n")
            val humidityToLocation = paragraphs.extractMapping(title = "humidity-to-location map:\n")

            return Almanac(
                initialSeeds,
                seedToSoil,
                soilToFertilizer,
                fertilizerToWater,
                waterToLight,
                lightToTemperature,
                temperatureToHumidity,
                humidityToLocation
            )
        }
    }

    fun getLowestLocationNumberForSeedRangesNaively(): Long {
        var lowestLocation = Long.MAX_VALUE

        for (i in initialSeeds.indices step 2) {
            println("Processing $i range")

            val start = initialSeeds[i]
            val range = initialSeeds[i + 1]

            for (seed in start until start + range) {
                val location = getInstructionForSeed(seed).last()

                if (location < lowestLocation) {
                    lowestLocation = location
                }
            }

            println("Processed $i range")
        }

        return lowestLocation
    }

    fun getInstructionsForInitialSeeds(): List<List<Long>> =
        initialSeeds.map { getInstructionForSeed(it) }

    fun getInstructionForSeed(seed: Long): List<Long> {
        val soil = getSoil(seed)
        val fertilizer = getFertilizer(soil)
        val water = getWater(fertilizer)
        val light = getLight(water)
        val temperature = getTemperature(light)
        val humidity = getHumidity(temperature)
        val location = getLocation(humidity)

        return listOf(seed, soil, fertilizer, water, light, temperature, humidity, location)
    }

    private fun getSoil(seed: Long): Long =
        seedToSoil[seed]

    private fun getFertilizer(soil: Long): Long =
        soilToFertilizer[soil]

    private fun getWater(fertilizer: Long): Long =
        fertilizerToWater[fertilizer]

    private fun getLight(water: Long): Long =
        waterToLight[water]

    private fun getTemperature(light: Long): Long =
        lightToTemperature[light]

    private fun getHumidity(temperature: Long): Long =
        temperatureToHumidity[temperature]

    private fun getLocation(humidity: Long): Long =
        humidityToLocation[humidity]

    data class Mappings(private val mappings: List<Mapping>) {
        operator fun get(source: Long) =
            mappings.firstNotNullOfOrNull { it[source] } ?: source
    }

    data class Mapping(val sourceStart: Long, val destinationStart: Long, val rangeLength: Long) {
        operator fun get(source: Long): Long? =
            if (sourceStart <= source && source < sourceStart + rangeLength) {
                destinationStart + (source - sourceStart)
            } else {
                null
            }
    }
}

private fun extractInitialSeeds(paragraphs: List<String>): List<Long> =
    paragraphs.first { it.startsWith("seeds: ") }
        .replace("seeds: ", "")
        .split(" ")
        .map { it.toLong() }

private fun List<String>.extractMapping(title: String): Almanac.Mappings =
    first { it.startsWith(title) }
        .replace(title, "")
        .split("\n")
        .map { it.extractSingleMappingFromLine() }
        .let { Almanac.Mappings(it) }

private fun String.extractSingleMappingFromLine(): Almanac.Mapping {
    val ranges = split(" ")
    val destinationStart = ranges[0].toLong()
    val sourceStart = ranges[1].toLong()
    val rangeLength = ranges[2].toLong()

    return Almanac.Mapping(sourceStart, destinationStart, rangeLength)
}
