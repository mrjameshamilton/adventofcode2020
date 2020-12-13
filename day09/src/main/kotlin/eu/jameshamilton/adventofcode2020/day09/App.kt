package eu.jameshamilton.adventofcode2020.day09


fun main() {
    val input = parse(object {}.javaClass.getResource("/input.txt").readText().lines())
    println(part1(input))
    println(part2(input))
}

fun part1(numbers: List<Long>, preambleSize: Int = 25): Long {
    val result = 0L

    numbers.windowed(preambleSize + 1).forEach { window ->
        val next = window[preambleSize]
        if (!isValid(window.subList(0, preambleSize), next)) return next
    }

    return result
}

fun part2(numbers: List<Long>, preambleSize: Int = 25): Long {
    val part1Result = part1(numbers, preambleSize)
    val set = HashSet<Long>()
    for ((i, a) in numbers.withIndex()) {
        set.add(a)

        for (b in numbers.subList(i + 1, numbers.size)) {
            set.add(b)
            if (set.sum() >= part1Result) break
        }

        if (set.sum() == part1Result) break
        else set.clear()
    }

    return set.min()!! + set.max()!!
}

fun isValid(input: List<Long>, next:Long): Boolean {
    val set = HashSet<Long>()

    for (a in input) {
        for (b in input) {
            if (set.contains(next - a) && next - a != a) return true
            set.add(b)
        }
        set.add(a)
    }

    return false
}

fun parse(lines:List<String>): List<Long> = lines.map { it.toLong() }