package com.devbridie.adventofcode2018

import com.devbridie.adventofcode2018.solutions.day1
import com.devbridie.adventofcode2018.solutions.day2
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val days = listOf(day1, day2)
    val day = selectDat(days)
    executeDay(day)
}

fun executeDay(day: Day) {
    val input = day.loadInput()
    measureTimeMillis { print("Part 1: ${day.part1(input)}") }.also { println(" -- time: ${it}ms") }
    measureTimeMillis { print("Part 2: ${day.part2(input)}") }.also { println(" -- time: ${it}ms") }
}

private fun selectDat(day: Iterable<Day>): Day {
    while (true) {
        println("Completed days: " + day.map { it.number }.joinToString())
        print("Select day:")
        val answer = readLine()
        val answerDay = day.find { it.number.toString() == answer }
        if (answerDay == null) println("Invalid day $answer.")
        else return answerDay
    }
}