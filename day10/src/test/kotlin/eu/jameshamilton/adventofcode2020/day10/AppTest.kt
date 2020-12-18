package eu.jameshamilton.adventofcode2020.day10

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AppTest {

    private val input = parse(object {}.javaClass.getResource("/input.txt").readText().lines())

    @Test fun testPart1() {
        assertEquals(35, part1(listOf(16, 10, 15, 5, 1, 11, 7, 19, 6, 12, 4)))
        assertEquals(220, part1(input))
    }

    @Test fun testPart2() {
        assertEquals(8, part2(listOf(16, 10, 15, 5, 1, 11, 7, 19, 6, 12, 4)))
    }
}
