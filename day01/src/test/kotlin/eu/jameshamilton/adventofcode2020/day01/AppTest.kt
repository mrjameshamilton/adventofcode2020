package eu.jameshamilton.adventofcode2020.day01

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.assertEquals

class AppTest {
    @Test fun testPart1() {
        assertEquals(514579, part1(listOf(1721, 979, 366, 299, 675, 1456)))
    }

    @Test fun testPart2() {
        assertEquals(241861950, part2(listOf(1721, 979, 366, 299, 675, 1456)))
    }
}
