package eu.jameshamilton.adventofcode2020.day11

import eu.jameshamilton.adventofcode2020.day11.Direction.*
import eu.jameshamilton.adventofcode2020.day11.State.*
import java.lang.RuntimeException

const val NO_LIMIT = -1

fun main() {
    val input = SeatLayout.fromLines(object {}.javaClass.getResource("/input.txt").readText().lines())
    println(part1(input))
    println(part2(input))
}

fun part1(seatLayout: SeatLayout): Int = calculateOccupiedSeats(
    seatLayout,
    maxSurroundingOccupied = 4,
    maxLookingDistance = 1
)

fun part2(seatLayout: SeatLayout): Int = calculateOccupiedSeats(seatLayout, maxSurroundingOccupied = 5)

fun calculateOccupiedSeats(originalSeatLayout: SeatLayout, maxSurroundingOccupied: Int, maxLookingDistance: Int = NO_LIMIT): Int {
    val seatLayout = originalSeatLayout.copy()
    var changed    = false
    var occupied   = 0

    for (row in 0 until originalSeatLayout.rowCount()) {
        for (column in 0 until originalSeatLayout.columnCount()) {
            when(originalSeatLayout.getState(row, column)) {
                EMPTY_SEAT -> {
                    if (originalSeatLayout.getVisibleOccupiedSeatCount(row, column, maxLookingDistance) == 0) {
                        seatLayout.setState(row, column, OCCUPIED_SEAT)
                        changed = true
                        occupied++
                    }
                }
                OCCUPIED_SEAT -> {
                    occupied++
                    if (originalSeatLayout.getVisibleOccupiedSeatCount(row, column, maxLookingDistance) >= maxSurroundingOccupied) {
                        seatLayout.setState(row, column, EMPTY_SEAT)
                        changed = true
                        occupied--
                    }
                }
                FLOOR -> { }
            }
        }
    }

    return if (changed) {
        calculateOccupiedSeats(seatLayout, maxSurroundingOccupied, maxLookingDistance)
    } else {
        occupied
    }
}

data class SeatLayout(val seatLayout: Array<Array<State>>) {

    fun getVisibleOccupiedSeatCount(row: Int, column: Int, maxDistance: Int = NO_LIMIT): Int {

        fun outOfBounds(row: Int, column: Int) =
            row < 0 || row >= rowCount() || column < 0 || column >= columnCount()

        fun isOccupiedSeatVisibleInDirection(direction: Direction, row: Int, column: Int, distance: Int = 1): Boolean {
            val next = when(direction) {
                N  -> Pair(row - distance, column)
                NE -> Pair(row - distance, column + distance)
                E  -> Pair(row, column + distance)
                SE -> Pair(row + distance, column + distance)
                S  -> Pair(row + distance, column)
                SW -> Pair(row + distance, column - distance)
                W  -> Pair(row, column - distance)
                NW -> Pair(row - distance, column - distance)
            }

            return if (outOfBounds(next.first, next.second)) {
                false
            } else when (getState(next.first, next.second)) {
                EMPTY_SEAT    -> false
                OCCUPIED_SEAT -> true
                FLOOR         -> {
                    if (maxDistance == NO_LIMIT || distance < maxDistance)
                        isOccupiedSeatVisibleInDirection(direction, row, column, distance + 1) else false
                }
            }
        }

        var result =  0
        for (direction in Direction.values()) {
            if (isOccupiedSeatVisibleInDirection(direction, row, column))
                result++
        }

        return result
    }

    fun getState(row: Int, column: Int): State {
        return seatLayout[row][column]
    }

    fun setState(row: Int, column: Int, state: State) {
        this.seatLayout[row][column] = state
    }

    fun rowCount(): Int = this.seatLayout.size

    fun columnCount(): Int = this.seatLayout.first().size

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SeatLayout

        if (!seatLayout.contentDeepEquals(other.seatLayout)) return false

        return true
    }

    override fun hashCode(): Int {
        return seatLayout.contentDeepHashCode()
    }

    override fun toString(): String {
        var string = ""
        for (row in seatLayout) {
            string += row.contentToString() + "\n"
        }
        return string
    }

    fun copy(): SeatLayout = SeatLayout(Array(seatLayout.size) { i -> seatLayout[i].clone() })

    companion object {
        fun fromLines(lines: List<String>): SeatLayout = SeatLayout(lines.map { line -> line.map { when(it) {
            'L' -> EMPTY_SEAT
            '#' -> OCCUPIED_SEAT
            '.' -> FLOOR
            else -> throw RuntimeException("Unexpected location value '$it'")
        } }.toTypedArray() }.toTypedArray())
    }
}

enum class Direction { N, NE, E, SE, S, SW, W, NW }

enum class State(val c: Char) {
    FLOOR('.'), OCCUPIED_SEAT('#'), EMPTY_SEAT('L');

    @Override
    override fun toString(): String = "$c"
}