package eu.jameshamilton.adventofcode2020.day03

fun main() {
    val input = object {}.javaClass.getResource("/input.txt").readText().lines()
    println(part1(3, 1, input))
    println(part2(
        listOf(
            Pair(1, 1),
            Pair(1, 3),
            Pair(1, 5),
            Pair(1, 7),
            Pair(2, 1)),
        input))
}


fun part1(right: Int, down: Int, grid: List<String>) : Int {
    var answer = 0

    for ((n, i) in (grid.indices step down).withIndex()) {
        if (grid[i][(n * right) % grid[i].length] == '#') answer++
    }

    return answer
}

fun part2(slopes: List<Pair<Int, Int>>, input: List<String>) : Long =
    slopes
        .map { part1(it.second, it.first, input).toLong() }
        .reduce { acc:Long, i -> acc * i }

