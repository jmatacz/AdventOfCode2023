package day6

import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

@OptIn(ExperimentalTime::class)
fun main() {
    val races = Race.parseRaces(day6Input)

    val naiveTime = measureTime {
        val numberOfWaysToBeatRecord = races.map { it.calculateWaysOfWinNaively() }
            .reduce { acc, i -> acc * i }

        println(numberOfWaysToBeatRecord)
    }

    val smartTime = measureTime {
        val numberOfWaysToBeatRecord = races.map { it.calculateWaysOfWinSmarter() }
            .reduce { acc, i -> acc * i }

        println(numberOfWaysToBeatRecord)
    }

    println("Naive time: $naiveTime")
    println("Smart time: $smartTime")

    val bigRace = Race(day6Input)

    val naiveTimeForBigRace = measureTime {
        val numberOfWaysToBeatRecord = bigRace.calculateWaysOfWinNaively()
        println(numberOfWaysToBeatRecord)
    }

    val smartTimeForBigRace = measureTime {
        val numberOfWaysToBeatRecord = bigRace.calculateWaysOfWinSmarter()
        println(numberOfWaysToBeatRecord)
    }

    println("Naive time for the big race: $naiveTimeForBigRace")
    println("Smart time for the big race: $smartTimeForBigRace")
}
