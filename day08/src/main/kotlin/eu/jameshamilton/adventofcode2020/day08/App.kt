package eu.jameshamilton.adventofcode2020.day08

import eu.jameshamilton.adventofcode2020.day08.Op.*
import java.util.*

fun main() {
    val input   = object {}.javaClass.getResource("/input.txt").readText().lines()
    val program = parse(input)
    val result  = exec(program)
    println(part1(result))
    println(part2(result))
}

fun part1(result: Result): Int = result.first
fun part2(result: Result): Int = result.second

fun exec(program:Program): Result {
    var part2Result = -1
    fun execRecursive(env: Environment, program: Program, mainExecution: Boolean = true) {
        while (!env.executed.get(env.pc)) {
            env.executed.set(env.pc)

            if (env.pc >= program.size) {
                // If pc >= program size, it means we've successfully terminated
                part2Result = env.acc
                return
            }

            val instruction = program[env.pc]

            // Execute instruction
            if (instruction.op == ACC) env.acc += instruction.arg
            // swap nop/jmp and try executing, to see if the swap allows termination
            else if (mainExecution && part2Result == -1) execRecursive(
                Environment(env.pc + if (instruction.op == JMP) 1 else instruction.arg, env.acc), program, false
            )

            // Increment program counter
            when (instruction.op) {
                ACC, NOP -> env.pc++
                JMP      -> env.pc += instruction.arg
            }
        }
    }

    return with(Environment()) {
        execRecursive(this, program)
        Result(this.acc, part2Result)
    }
}

fun parse(instructions: List<String>): Program = instructions
    .map { str -> str.split(' ') }
    .map { str -> Pair(str.first(), str.last().toInt()) }
    .map { (op, arg) ->
        when (op) {
            "acc" -> Instruction(ACC, arg)
            "jmp" -> Instruction(JMP, arg)
            "nop" -> Instruction(NOP, arg)
            else  -> throw RuntimeException("Unknown operation $op")
        }
    }

typealias Result = Pair<Int, Int>
typealias Program = List<Instruction>
data class Instruction(val op: Op, val arg: Int)
data class Environment(var pc: Int = 0, var acc: Int = 0, val executed: BitSet = BitSet())
enum class Op { ACC, JMP, NOP }