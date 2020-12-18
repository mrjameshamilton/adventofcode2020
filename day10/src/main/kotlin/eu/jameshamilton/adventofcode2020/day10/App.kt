package eu.jameshamilton.adventofcode2020.day10


fun main() {
    val input = parse(object {}.javaClass.getResource("/input.txt").readText().lines())
    println(part1(input))
    println(part2(input))
}

fun part1(input: List<Int>): Int {
    val voltages = input.sorted()
    var oneDiffs = 0
    var threeDiffs = 1
    var currentVoltage = 0

    for (next in voltages) {
        val diff = next - currentVoltage
        if (diff == 1) oneDiffs++
        else if (diff == 3) threeDiffs++
        currentVoltage = next
    }

    return oneDiffs * threeDiffs
}

fun part2(input: List<Int>): Long {
    val cache = HashMap<Int, Long>()
    val max = input.max()!!
    fun countArrangements(from: Int): Long {
        var sum = 0L
        for (next in (from + 1)..(from + 3)) when {
            next == max                     -> sum += 1
            input.count { it == next } == 1 -> sum += cache.getOrPut(next) { countArrangements(next) }
        }
        return sum
    }

    return countArrangements(0)
}

fun parse(lines:List<String>): List<Int> = lines.map { it.toInt() }