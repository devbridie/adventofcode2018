package com.devbridie.adventofcode2018.solutions

import com.devbridie.adventofcode2018.Day
import com.devbridie.adventofcode2018.executeDay

private data class FuelCell(val serial: Int, val x: Int, val y: Int) {
    val rackId = x + 10
    val powerLevel = (((((rackId * y + serial) * rackId) / 100) % 10) - 5)
}

private fun Array<Array<FuelCell>>.getTotalPowerInSquare(topLeftX: Int, topLeftY: Int, size: Int): Int {
    var sum = 0
    (topLeftX until topLeftX + size).forEach { x ->
        (topLeftY until topLeftY + size).forEach { y ->
            sum += this[x][y].powerLevel
        }
    }
    return sum
}

private val solution1 = { data: String ->
    val grid = buildPowerGrid(data)

    (0 until 297).flatMap { x ->
        (0 until 297).map { y ->
            (x to y) to grid.getTotalPowerInSquare(x, y, 3)
        }
    }.maxBy { it.second }!!.first.let { "${it.first},${it.second}" }
}

// removed collection operations in favor of imperative for loops for speed :(
private val solution2 = { data: String ->
    val grid = buildPowerGrid(data)

    var max = 0
    var maxString = ""
    (1..300).forEach { size ->
        for (x in 0 until 300 - size) {
            for (y in 0 until 300 - size) {
                val power = grid.getTotalPowerInSquare(x, y, size)
                if (max < power) {
                    max = power
                    maxString = "$x,$y,$size"
                }
            }
        }
    }
    maxString
}

private fun buildPowerGrid(serial: String): Array<Array<FuelCell>> {
    val s = serial.toInt()
    return Array(300) { x -> Array(300) { y -> FuelCell(s, x, y) } }
}

val day11 = Day(11, solution1, solution2)

fun main(args: Array<String>) = executeDay(day11, true, true)