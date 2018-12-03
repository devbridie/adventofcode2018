package com.devbridie.adventofcode2018

typealias Solution = (data: String) -> Any

data class Day(
    val number: Int,
    val part1: Solution,
    val part2: Solution
)

fun Day.loadInput() = loadInput("day$number")