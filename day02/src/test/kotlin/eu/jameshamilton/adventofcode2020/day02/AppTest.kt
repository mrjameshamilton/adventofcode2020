package eu.jameshamilton.adventofcode2020.day02

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.assertEquals

class AppTest {
    private val passwords = listOf(
        Pair(Policy(1, 3, 'a'), "abcde"),
        Pair(Policy(1, 3, 'b'), "cdefg"),
        Pair(Policy(2, 9, 'c'), "ccccccccc")
    )

    @Test fun testPart1() = assertEquals(2, part1(passwords))

    @Test fun testPart2() = assertEquals(1, part2(passwords))
}
