package com.devbridie.adventofcode2018

import com.devbridie.adventofcode2018.solutions.day5
import org.junit.Assert
import org.junit.Test

class Day5Test {
    @Test
    fun `part 1 example`() {
        Assert.assertEquals("dabCBAcaDA", day5.part1("dabAcCaCBAcCcaDA"))
        Assert.assertEquals("aabAAB", day5.part1("aabAAB"))
    }
}