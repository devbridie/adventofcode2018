package com.devbridie.adventofcode2018.solutions

import com.devbridie.adventofcode2018.Day
import com.devbridie.adventofcode2018.executeDay

private data class Point(val x: Int, val y: Int, val xVelocity: Int, val yVelocity: Int)

private fun parsePoints(data: String): List<Point> {
    val regex = Regex("^position=<(.*),(.*)> velocity=<(.*),(.*)>$")
    return data.split("\n").map {
        val (x, y, xVelocity, yVelocity) = regex.matchEntire(it)!!.groupValues.drop(1).map(String::trim).map(String::toInt)
        Point(x, y, xVelocity, yVelocity)
    }
}

private fun List<Point>.move() = this.map { Point(it.x + it.xVelocity, it.y + it.yVelocity, it.xVelocity, it.yVelocity) }

private fun List<Point>.display() {
    val xs = map { it.x }
    val ys = map { it.y }
    (ys.min()!! .. ys.max()!!).forEach { y ->
        (xs.min()!!..xs.max()!!).forEach { x ->
            if (any { it.x == x && it.y == y }) {
                print("#")
            } else {
                print(".")
            }
        }
        println()
    }
}

private val solutions = { data: String ->
    var points = parsePoints(data)
    var n = 0
    while (true) {
        val xs = points.map { it.x }
        val ys = points.map { it.y }
        if (xs.max()!! - xs.min()!! <= points.size && ys.max()!! - ys.min()!! <= points.size) {
            points.display()
            println("n = $n")
            println()
            readLine()
        }
        points = points.move()
        n++
    }
}

val day10 = Day(10, solutions, solutions)

fun main(args: Array<String>) = executeDay(day10, false, true)