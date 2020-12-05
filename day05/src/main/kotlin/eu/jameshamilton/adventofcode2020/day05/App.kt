package eu.jameshamilton.adventofcode2020.day05

import java.lang.RuntimeException
import kotlin.math.*

fun main() {
    val input = object {}.javaClass.getResource("/input.txt").readText().lines()
    println(part1(input))
    println(part2(input))
}

fun part1(seats: List<String>): Int =
    seats
        .map { Seat.fromString(it).id }
        .max()!!

fun part2(seats: List<String>): Int =
    seats.asSequence()
        .map { Seat.fromString(it).id }
        .sorted()
        .zipWithNext()
        .filter { it.second != it.first + 1 }
        .map { it.second - 1 }
        .single()


data class Seat(val row:Int, val column:Int) {
    val id = (row * 8) + column

    companion object {
        fun fromString(s:String): Seat = Seat(
            row = convert(s.take(7), 0, 127),
            column = convert(s.drop(7), 0, 7)
        )
    }
}

fun convert(s:String, min:Int, max:Int): Int {
    return if (s.isEmpty()) min
    else when (s.first()) {
        'F', 'L' -> convert(s.drop(1), min, floor((max + min) / 2.0).roundToInt())
        'B', 'R' -> convert(s.drop(1), ceil((max + min) / 2.0).roundToInt(), max)
        else -> throw RuntimeException("Error")
    }
}

