package eu.jameshamilton.adventofcode2020.day07

import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet

fun main() {
    val input = object {}.javaClass.getResource("/input.txt").readText().lines()
    val graph = parse(input)
    val root = graph.nodes.find { it.value == "shiny gold" }!!
    println(part1(root, graph))
    println(part2(root, graph))
}

fun part1(root:Node, graph:Graph): Int {
    val queue:Queue<Node> = LinkedList<Node>()
    val visited = mutableSetOf<Node>()
    queue.addAll(graph.getNeighbours(root))

    while (queue.isNotEmpty()) {
        val node = queue.poll()
        visited.add(node)
        graph.getNeighbours(node).forEach { queue.offer(it) }
    }

    return visited.size
}

fun part2(root: Node, original: Graph): Int {
    val graph = original.flipEdges()

    fun _part2(n:Node): Int = with(graph.getEdges(n)) {
        sumBy { it.weight } + map { it.weight * _part2(it.b) }.sum()
    }

    return _part2(root)
}



fun parse(lines:List<String>): Graph {
    val edges = ArrayList<Edge>()
    val nodes = HashSet<Node>()

    lines.forEach { line ->
        val regex = """^(?<a>.*) bags contain (?<neighboursString>.*)\.$""".toRegex()
        val groups = regex.find(line)?.groups
        val a = groups?.get("a")?.value
        val nodeA = Node(a.toString())
        nodes.add(nodeA)
        val neighboursString = groups?.get("neighboursString")?.value as String
        val regex2 = """(?<n>\d+) (?<b>[^.,]*) bags?,?""".toRegex()
        regex2.findAll(neighboursString).forEach {
            val b = it.groups["b"]?.value.toString()
            val n = it.groups["n"]?.value?.toInt()
            var nodeB = nodes.find { it.value == b }
            if (nodeB == null) {
                nodeB = Node(b)
                nodes.add(nodeB)
            }
            edges.add(Edge(nodeB, nodeA, n!!))
        }
    }

    return Graph(nodes, edges)
}

data class Graph(val nodes: Set<Node>, val edges:List<Edge>) {
    fun getNeighbours(a:Node) = edges.filter { it.a == a }.map { it.b }
    fun getEdges(a:Node) = edges.filter { it.a == a }
    fun flipEdges(): Graph = Graph(nodes, edges.map { Edge(it.b, it.a, it.weight) })
}

data class Edge(val a: Node, val b:Node, val weight: Int)

data class Node(val value:String)