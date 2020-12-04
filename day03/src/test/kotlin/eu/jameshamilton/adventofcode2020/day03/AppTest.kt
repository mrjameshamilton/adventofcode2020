package eu.jameshamilton.adventofcode2020.day03

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.assertEquals

class AppTest {
    private val input = object {}.javaClass.getResource("/input.txt").readText().lines()

    @Test fun testPart1() = testOneDownThreeRight()

    @Test fun testOneDownOneRight() = assertEquals(2, part1(1, 1, input))

    @Test fun testOneDownThreeRight() = assertEquals(7, part1(3, 1, input))

    @Test fun testOneDownFiveRight() = assertEquals(3, part1(5, 1, input))

    @Test fun testOneDownSevenRight() = assertEquals(4, part1(7, 1, input))

    @Test fun testTwoDownOneRight() = assertEquals(2, part1(1, 2, input))

    @Test fun testPart2() {
        assertEquals(336, part2(
            listOf(
                Pair(1, 1),
                Pair(1, 3),
                Pair(1, 5),
                Pair(1, 7),
                Pair(2, 1)
            ), input))
    }
}
