package com.devbridie.adventofcode2018.solutions

import com.devbridie.adventofcode2018.Day
import com.devbridie.adventofcode2018.executeDay

private typealias PotsState = Map<Int, Boolean>

private data class Rule(val config: PotsState, val becomes: Boolean)

private fun applyRules(state: PotsState, rules: List<Rule>): PotsState {
    return (state.keys.min()!! - 2..state.keys.max()!! + 2).map { index ->
        val rule = rules.find { rule ->
            rule.config.entries.all { (i, v) -> state.getOrDefault(i + index, false) == v }
        }
        index to if (rule == null) {
            state.getOrDefault(index, false)
        } else {
            rule.becomes
        }
    }.toMap()
}

private val solution1 = { data: String ->
    val (initialState, rules) = parseInput(data)

    val lastState = (0 until 20).fold(initialState) { acc, _ -> applyRules(acc, rules) }
    lastState.entries.filter { it.value }.sumBy { it.key }
}

// removed collection operations in favor of imperative for loops for speed :(
private val solution2 = { data: String ->
    val (initialState, rules) = parseInput(data)

    val lastState = (0 until 50000000000).fold(initialState) { acc, _ -> applyRules(acc, rules) }
    lastState.entries.filter { it.value }.sumBy { it.key }
}

private fun parseInput(data: String): Pair<PotsState, List<Rule>> {
    val lines = data.lines()
    val initialState = lines.first().split(" ")[2].mapIndexed { index, c -> index to c.toFlowerBoolean() }.toMap()
    val rules = lines.drop(2).map {
        val (config, _, becomes) = it.split(" ")
        val miniState = config.mapIndexed { index, c -> index - 2 to c.toFlowerBoolean() }.toMap()
        Rule(miniState, becomes[0].toFlowerBoolean())
    }
    return initialState to rules
}

private fun Char.toFlowerBoolean() = when (this) {
    '.' -> false
    '#' -> true
    else -> error("Not a . or #")
}

val day12 = Day(12, solution1, solution2)

fun main(args: Array<String>) = executeDay(day12, true, true)