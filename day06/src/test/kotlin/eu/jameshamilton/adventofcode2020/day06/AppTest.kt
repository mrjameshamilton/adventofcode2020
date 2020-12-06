package eu.jameshamilton.adventofcode2020.day06


import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AppTest {

    @Test fun testPart1() {
        assertEquals(11, part1(parse(object {}.javaClass.getResourceAsStream("/input.txt"))))
    }

    @Test fun testPart2() {
        assertEquals(6, part2(parse(object {}.javaClass.getResourceAsStream("/input.txt"))))
    }
}
