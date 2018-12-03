package com.devbridie.adventofcode2018.solutions

import com.devbridie.adventofcode2018.Day
import com.devbridie.adventofcode2018.executeDay

private typealias Grid = Array<Array<Set<Int>>>

private data class Claim(val id: Int, val x: Int, val y: Int, val w: Int, val h: Int)

private fun emptyGrid() = Array(1000) { Array(1000) { emptySet<Int>() } }

private fun Grid.claim(claim: Claim) = claim.positions().forEach { pos ->
    this[pos] = this[pos] + claim.id
}

private fun line2claim(line: String): Claim {
    val (id, x, y, w, h) = Regex("^#(.*) @ (.*),(.*): (.*)x(.*)$").find(line)!!.groupValues.drop(1).map(String::toInt)
    return Claim(id, x, y, w, h)
}

private val solution1 = { data: String ->
    val grid = emptyGrid()
    val claims = data.split("\n").map(::line2claim)
    claims.forEach { grid.claim(it) }

    grid.flatMap { it.asIterable() }.count { it.size >= 2 }
}

private val solution2 = { data: String ->
    val grid = emptyGrid()
    val claims = data.split("\n").map(::line2claim)
    claims.forEach { grid.claim(it) }

    claims.first {
        it.positions().all { pos -> grid[pos].size == 1 }
    }.id
}

val day3 = Day(3, solution1, solution2)

fun main(args: Array<String>) = executeDay(day3)

private fun Claim.positions() = (x until x + w).flatMap { a -> (y until y + h).map { b -> a to b } }

private operator fun Grid.get(position: Pair<Int, Int>) = this[position.first][position.second]
private operator fun Grid.set(position: Pair<Int, Int>, value: Set<Int>) {
    this[position.first][position.second] = value
}