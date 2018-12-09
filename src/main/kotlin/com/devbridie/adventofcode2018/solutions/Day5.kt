package com.devbridie.adventofcode2018.solutions

import com.devbridie.adventofcode2018.Day
import com.devbridie.adventofcode2018.executeDay

private fun react(d: String): String {
    val regex = Regex(('a'..'z').flatMap { listOf("" + it + it.toUpperCase(), "" +  it.toUpperCase() + it) }.joinToString("|"))
    var string = d
    while (true) {
        val new = regex.replace(string, "")
        if (string == new) break
        string = new
    }
    return string
}

private val solution1 = { data: String ->
    react(data).length
}

private val solution2 = { data: String ->
    val removed = ('a'..'z').map {
        react(data.replace("$it", "", ignoreCase = true))
    }

    removed.minBy { it.length }!!.length
}

val day5 = Day(5, solution1, solution2)

fun main(args: Array<String>) = executeDay(day5)