package com.devbridie.adventofcode2018

import com.devbridie.adventofcode2018.solutions.day6
import org.junit.Assert
import org.junit.Test

class Day6Test {
    @Test
    fun `part 1 example`() {
        Assert.assertEquals("17", day6.part1(day6.loadExample("1")))
    }
}