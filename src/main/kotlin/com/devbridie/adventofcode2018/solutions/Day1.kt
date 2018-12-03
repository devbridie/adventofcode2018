package com.devbridie.adventofcode2018.solutions

import com.devbridie.adventofcode2018.Day
import com.devbridie.adventofcode2018.executeDay

private val solution1 = { data: String ->
    val startFrequency = 0
    val changes = data.split("\n").map { it.toInt() }
    changes.fold(startFrequency) { acc, i -> acc + i }
}

private val solution2 = { data: String ->
    val changes = data.split("\n").map { it.toInt() }.infiniteSequence()

    // TODO remove this mutable state
    val encounters = mutableSetOf<Int>()
    var state = 0
    changes.first { change ->
        encounters += state
        state += change
        encounters.contains(state)
    }
    state
}

val day1 = Day(1, solution1, solution2)

fun main(args: Array<String>) = executeDay(day1)

fun <T> List<T>.infiniteSequence(): Sequence<T> {
    return generateSequence(0) { (it + 1) % this.size }.map { this[it] }
}