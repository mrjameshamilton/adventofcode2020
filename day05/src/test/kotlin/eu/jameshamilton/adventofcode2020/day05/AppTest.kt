package eu.jameshamilton.adventofcode2020.day05


import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AppTest {
    @Test fun testSeats() {
        assertEquals(Seat(44, 5), Seat.fromString("FBFBBFFRLR"))
        assertEquals(Seat(70, 7), Seat.fromString("BFFFBBFRRR"))
        assertEquals(Seat(14, 7) , Seat.fromString("FFFBBBFRRR"))
        assertEquals(Seat(102, 4), Seat.fromString("BBFFBBFRLL"))
    }

    @Test fun testSeatId() {
        assertEquals(567, Seat(70, 7).id)
        assertEquals(119, Seat(14, 7).id)
        assertEquals(820, Seat(102, 4).id)
    }

    @Test fun testPart1() {
        //BFFFBBFRRR: row 70, column 7, seat ID 567.
        //FFFBBBFRRR: row 14, column 7, seat ID 119.
        //BBFFBBFRLL: row 102, column 4, seat ID 820.
        assertEquals(820, part1(listOf("BFFFBBFRRR", "FFFBBBFRRR", "BBFFBBFRLL")))
    }
}
