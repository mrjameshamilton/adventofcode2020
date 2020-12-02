package eu.jameshamilton.adventofcode2020.day02

fun main() {
    val input = object {}.javaClass.getResource("/input.txt").readText().lines()
    val passwords = input.filterNot { it.isBlank() }.map { line ->
        val regex = """(\d+)-(\d+) ([a-z]): ([a-z]+)""".toRegex()
        val matchResult = regex.find(line)
        val (min, max, char, password) = matchResult!!.destructured
        Pair(Policy(min.toInt(), max.toInt(), char[0]), password)
    }
    println(part1(passwords))
    println(part2(passwords))
}

fun part1(input: List<Pair<Policy, String>>) : Int = input.count { (policy, password) ->
    with(password.count { char -> char == policy.char }) {
        this >= policy.min && this <= policy.max
    }
}

fun part2(input: List<Pair<Policy, String>>) : Int = input.count { (policy, password) ->
    (password.getOrNull(policy.min - 1) == policy.char || password.getOrNull(policy.max - 1) == policy.char) &&
    (password.getOrNull(policy.min - 1) != password.getOrNull(policy.max - 1))
}

data class Policy(val min: Int, val max: Int, val char: Char)