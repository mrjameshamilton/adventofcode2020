package eu.jameshamilton.adventofcode2020.day11

import eu.jameshamilton.adventofcode2020.day11.State.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class AppTest {

    private val input = SeatLayout.fromLines(object {}.javaClass.getResource("/input.txt").readText().lines())

    @Test fun testPart1() {
        assertEquals(37, part1(input))
    }

    @Test fun testPart2() {
        assertEquals(26, part2(input))
    }

    @Test fun testVisibleOccupiedSees8() {
        val input = SeatLayout.fromLines(object {}.javaClass.getResource("/input8occupied.txt").readText().lines())
        assertEquals(8, input.getVisibleOccupiedSeatCount(4, 3 ))
    }

    @Test fun testVisibleOccupiedSeesNone() {
        val input = SeatLayout.fromLines(object {}.javaClass.getResource("/input0occupied.txt").readText().lines())
        assertEquals(0, input.getVisibleOccupiedSeatCount(1, 1 ))
    }

    @Test fun testVisibleOccupiedSeesNone2() {
        val input = SeatLayout.fromLines(object {}.javaClass.getResource("/input0seen2.txt").readText().lines())
        assertEquals(0, input.getVisibleOccupiedSeatCount(3, 3 ))
    }

    @Test fun testVisibleOccupiedSees4() {
        val input = SeatLayout.fromLines(object {}.javaClass.getResource("/input4seen.txt").readText().lines())
        assertEquals(4, input.getVisibleOccupiedSeatCount(0, 3, 1 ))
    }

    @Test fun testParse() {
        assertEquals(
            SeatLayout(
                arrayOf(
                arrayOf(EMPTY_SEAT, OCCUPIED_SEAT, EMPTY_SEAT, EMPTY_SEAT, FLOOR),
                arrayOf(EMPTY_SEAT, FLOOR, EMPTY_SEAT, EMPTY_SEAT, OCCUPIED_SEAT)
                )
            ),
            SeatLayout.fromLines(listOf("L#LL.", "L.LL#")))
    }
}
