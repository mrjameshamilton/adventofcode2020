package eu.jameshamilton.adventofcode2020.day06

import java.io.InputStream

fun main() {
    println(part1(parse(object {}.javaClass.getResourceAsStream("/input.txt"))))
    println(part2(parse(object {}.javaClass.getResourceAsStream("/input.txt"))))
}

fun part1(groups: Sequence<Group>): Int =
    groups
        .map { it.answers.reduce { acc, set -> acc + set } }
        .sumBy { it.size }

fun part2(groups: Sequence<Group>): Int =
    groups
        .map { it.answers.fold(it.answers.first().toMutableSet()) { acc, answers -> acc.apply { retainAll(answers) } } }
        .sumBy { it.size }

fun parse(inputStream: InputStream): Sequence<Group> = sequence {
    inputStream.bufferedReader().useLines { lines ->
        val list = mutableListOf<Set<Char>>()
        lines.forEach { s ->
            if (s.isBlank()) {
                yield(Group(list.toList()))
                list.clear()
            } else list.add(s.toHashSet())
        }
        yield(Group(list.toList()))
    }
}

data class Group(val answers: List<Set<Char>>)