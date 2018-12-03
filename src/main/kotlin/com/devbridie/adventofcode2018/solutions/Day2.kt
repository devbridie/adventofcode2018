package com.devbridie.adventofcode2018.solutions

import com.devbridie.adventofcode2018.Day
import com.devbridie.adventofcode2018.executeDay


private val solution1 = { data: String ->
    data.split("\n")
        .map {
            val chars = it.toCharArray()
            val counts = chars.distinct().associateBy { c1 -> chars.count { c2 -> c2 == c1 } }
            counts.containsKey(2) to counts.containsKey(3)
        }
        .unzip()
        .let { (twos, threes) -> twos.count { it } * threes.count { it } }
}

private val solution2 = { data: String ->
    data.split("\n")
        .windowed(data.length, partialWindows = true)
        .mapNotNull { window ->
            val search = window.first()
            val rest = window.drop(1)
            rest.map { it.zip(search) }
                .find { it.count { (a, b) -> a != b } == 1 }
        }.first().let { pairs ->
            pairs.dropWhile { (a, b) -> a != b }
                .unzip().first.joinToString(separator = "")
        }
}

val day2 = Day(2, solution1, solution2)

fun main(args: Array<String>) = executeDay(day2)