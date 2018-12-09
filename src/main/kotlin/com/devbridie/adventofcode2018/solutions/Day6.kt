package com.devbridie.adventofcode2018.solutions

import com.devbridie.adventofcode2018.Day
import com.devbridie.adventofcode2018.executeDay

private fun hamming(p1: Position, p2: Position): Int {
    return Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y)
}

private data class Position(val x: Int, val y: Int)

private val solution1 = { data: String ->
    val coords = data.split("\n").map {
        val (x, y) = it.split(", ").map(String::toInt)
        Position(x, y)
    }

    val xlen = coords.map { it.x }.max()!!
    val ylen = coords.map { it.y }.max()!!

    val positions = (0..xlen).flatMap { x -> (0..ylen).map { y -> Position(x, y) } }
    val positionValues = positions.map { p1 ->
        val dists = coords.map { it to hamming(p1, it) }
        val closestDistance = dists.minBy { it.second }!!
        val qualifying = dists.filter { (_, d) -> d == closestDistance.second }

        if (qualifying.size == 1) {
            p1 to closestDistance.first
        } else null
    }.filterNotNull()
    val infiniteIndexes = positionValues.filter { (pos, _) -> // infinite indexes touch one of the sides of the box
        val (x, y) = pos
        x == 0 || x == xlen || y == 0 || y == ylen
    }.map { it.second }.toSet()
    val withoutInfinite = positionValues.filterNot { (_, index) -> infiniteIndexes.contains(index) }
    withoutInfinite.map { it.second }.maxByCount()!!.key
}

private fun <T : Any> Iterable<T>.maxByCount() = associateBy { m -> count { it == m } }.maxBy { it.key }


private val solution2 = { data: String ->
    val coords = data.split("\n").map {
        val (x, y) = it.split(", ").map(String::toInt)
        Position(x, y)
    }
    val xlen = coords.map { it.x }.max()!!
    val ylen = coords.map { it.y }.max()!!
    val positions = (0..xlen).flatMap { x -> (0..ylen).map { y -> Position(x, y) } }

    positions.count { coords.sumBy { coord -> hamming(it, coord) } < 10000 }
}

val day6 = Day(6, solution1, solution2)

fun main(args: Array<String>) = executeDay(day6)