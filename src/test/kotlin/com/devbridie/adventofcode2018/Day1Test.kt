package com.devbridie.adventofcode2018

import com.devbridie.adventofcode2018.solutions.day1
import org.junit.Assert.*
import org.junit.Test


class Day1Test {
    @Test
    fun `solution 2 example`() {
        val input = day1.loadExample("1").split(", ").joinToString(separator = "\n")
        assertEquals(2, day1.part2(input))
    }
}