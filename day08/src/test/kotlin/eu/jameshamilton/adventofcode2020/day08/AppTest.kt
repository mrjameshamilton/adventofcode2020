package eu.jameshamilton.adventofcode2020.day08

import eu.jameshamilton.adventofcode2020.day08.Op.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AppTest {

    private val input = object {}.javaClass.getResource("/input.txt").readText().lines()

    @Test
    fun testPart1() = assertEquals(5, part1(exec(parse(input))))

    @Test
    fun testPart2() = assertEquals(8, part2(exec(parse(input))))

    @Test fun testParse() {
        assertEquals(
            listOf(
                Instruction(NOP, 0),
                Instruction(ACC, 1),
                Instruction(JMP, 4),
                Instruction(ACC, 3),
                Instruction(JMP, -3),
                Instruction(ACC, -99),
                Instruction(ACC, 1),
                Instruction(JMP, -4),
                Instruction(ACC, 6)
            ),
            parse(input))
    }
}
