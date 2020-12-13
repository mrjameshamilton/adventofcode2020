package eu.jameshamilton.adventofcode2020.day09

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AppTest {

    private val input = parse(object {}.javaClass.getResource("/input.txt").readText().lines())

    @Test fun testPart1() = assertEquals(127, part1(input, preambleSize = 5))

    @Test fun testPart2() = assertEquals(62, part2(input, preambleSize = 5))

    @Test fun testValid() {
        assertTrue(isValid((1L..25L).shuffled().toList(), 26))
        assertTrue(isValid((1L..25L).shuffled().toList(), 49))

        assertTrue(isValid((1L..25L).filterNot { it == 20L }.shuffled().toList() + listOf(45L), 26), "26 should be valid")
        assertTrue(isValid((1L..25L).filterNot { it == 20L }.shuffled().toList() + listOf(45L), 64), "64 should be valid")
        assertTrue(isValid((1L..25L).filterNot { it == 20L }.shuffled().toList() + listOf(45L), 66), "66 should be valid")
    }

    @Test fun testInvalid() {
        assertFalse(isValid((1L..25L).shuffled().toList(), 100), "100 should be invalid")
        assertFalse(isValid((1L..25L).shuffled().toList(), 50), "50 should be invalid")

        assertFalse(isValid((1L..25L).filterNot { it == 20L }.shuffled().toList() + listOf(45L), 65), "65 should be invalid")
    }


}
