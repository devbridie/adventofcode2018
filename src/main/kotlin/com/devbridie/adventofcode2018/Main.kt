package com.devbridie.adventofcode2018

import com.devbridie.adventofcode2018.solutions.day1
import com.devbridie.adventofcode2018.solutions.day2
import com.devbridie.adventofcode2018.solutions.day3
import com.devbridie.adventofcode2018.solutions.day4

fun main(args: Array<String>) {
    val days = listOf(day1, day2, day3, day4)
    val day = selectDay(days)
    executeDay(day)
}

fun executeDay(day: Day) {
    val input = day.loadInput()
    println("Part 1: ${day.part1(input)}")
    println("Part 2: ${day.part2(input)}")
}

private fun selectDay(day: Iterable<Day>): Day {
    while (true) {
        println("Completed days: " + day.map { it.number }.joinToString())
        print("Select day:")
        val answer = readLine()
        val answerDay = day.find { it.number.toString() == answer }
        if (answerDay == null) println("Invalid day $answer.")
        else return answerDay
    }
}