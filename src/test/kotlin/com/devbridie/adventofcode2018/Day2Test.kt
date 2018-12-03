package com.devbridie.adventofcode2018

import com.devbridie.adventofcode2018.solutions.day2
import org.junit.Assert
import org.junit.Test

class Day2Test {
    @Test
    fun `part 2 example`() {
        Assert.assertEquals("fgij", day2.part2(day2.loadExample("1")))
    }
}