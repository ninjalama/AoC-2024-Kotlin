package day03

import utils.ResourceUtils

fun main() {
    val inputStr = ResourceUtils.getResourceAsText("/inputs/day03/input.txt")
    val sumOfMultiplications = """mul\((\d+),(\d+)\)""".toRegex().findAll(inputStr).filterNot { match ->
        inputStr.lastIndexOf("don't()", match.range.first) > inputStr.lastIndexOf("do()", match.range.first)
    }.sumOf {
        it.groupValues[1].toInt() * it.groupValues[2].toInt()
    }
    println("Sum of multiplications: $sumOfMultiplications")
}
