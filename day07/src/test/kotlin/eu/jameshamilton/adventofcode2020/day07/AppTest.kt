package eu.jameshamilton.adventofcode2020.day06


import eu.jameshamilton.adventofcode2020.day07.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class AppTest {

    private val input = object {}.javaClass.getResource("/input.txt").readText().lines()
    private val graph = parse(input)
    private val root = graph.nodes.find { it.value == "shiny gold" }!!

    @Test fun testPart1() {
        assertEquals(4, part1(root, graph))
    }

    @Test fun testPart2() {
        assertEquals(32, part2(root, graph))
    }

    @Test fun testParse() {
        val lightRed = Node("light red")
        val darkOrange = Node("dark orange")
        val brightWhite = Node("bright white")
        val mutedYellow = Node("muted yellow")
        val shinyGold = Node("shiny gold")
        val darkOlive = Node("dark olive")
        val vibrantPlum = Node("vibrant plum")
        val fadedBlue = Node("faded blue")
        val dottedBlack = Node("dotted black")

        val nodes = setOf(
            lightRed,
            darkOrange,
            brightWhite,
            mutedYellow,
            shinyGold,
            darkOlive,
            vibrantPlum,
            fadedBlue,
            dottedBlack
        )

/*
light red bags contain 1 bright white bag, 2 muted yellow bags.
dark orange bags contain 3 bright white bags, 4 muted yellow bags.
bright white bags contain 1 shiny gold bag.
muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
dark olive bags contain 3 faded blue bags, 4 dotted black bags.
vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
faded blue bags contain no other bags.
dotted black bags contain no other bags.
 */
        val edges = listOf(
            Edge(brightWhite, lightRed, 1),
            Edge(mutedYellow, lightRed, 2),
            Edge(brightWhite, darkOrange, 3),
            Edge(mutedYellow, darkOrange, 4),
            Edge(shinyGold, brightWhite, 1),
            Edge(shinyGold, mutedYellow, 2),
            Edge(fadedBlue, mutedYellow,  9),
            Edge(darkOlive, shinyGold, 1),
            Edge(vibrantPlum, shinyGold, 2),
            Edge(fadedBlue, darkOlive, 3),
            Edge(dottedBlack, darkOlive, 4),
            Edge(fadedBlue, vibrantPlum, 5),
            Edge(dottedBlack, vibrantPlum, 6)
        )
        assertEquals(Graph(nodes, edges), parse(input))
        println(Graph(nodes, edges))
    }
}
