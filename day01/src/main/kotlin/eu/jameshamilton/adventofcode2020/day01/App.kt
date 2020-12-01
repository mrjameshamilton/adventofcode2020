package eu.jameshamilton.adventofcode2020.day01

import java.util.Collections.sort

const val EXPECTED = 2020

fun main() {
    val input = object {}.javaClass.getResource("/input.txt").readText()
    val list = input.lines().filterNot { it.isBlank() }.map { it.toInt() }
    println(part1(list))
    println(part2(list))
}

fun part1(input : List<Int>) : Int {
    sort(input)
    var left = 0
    var right = input.size - 1

    while (left < right) {
        val sum = input[left] + input[right]
        when {
            sum == EXPECTED -> return input[left] * input[right]
            sum < EXPECTED -> left++
            else -> right--
        }
    }

    return -1
}

fun part2(input : List<Int>) : Int {
    val set = HashSet<Int>()

    for (a in input) {
        for (b in input) {
            if (set.contains(EXPECTED - a - b)) return (EXPECTED - a - b) * a * b
            set.add(b)
        }
        set.add(a)
    }

    return -1
}