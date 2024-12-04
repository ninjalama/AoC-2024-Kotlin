package day03

import utils.ResourceUtils

fun main() {
    val inputStr = ResourceUtils.getResourceAsText("/inputs/day03/input.txt")
    """mul\((\d+),(\d+)\)""".toRegex().findAll(inputStr).sumOf {
        it.groupValues[1].toInt() * it.groupValues[2].toInt()
    }.also { println("Sum of multiplications: $it") }
}