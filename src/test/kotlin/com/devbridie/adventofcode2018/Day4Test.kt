package com.devbridie.adventofcode2018

import com.devbridie.adventofcode2018.solutions.day4
import org.junit.Assert
import org.junit.Test

class Day4Test {
    @Test
    fun `part 1 example`() {
        Assert.assertEquals(240, day4.part1(day4.loadExample("1")))
    }
}