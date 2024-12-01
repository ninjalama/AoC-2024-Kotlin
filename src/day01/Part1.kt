package day01

import utils.ResourceUtils
import kotlin.math.abs

fun main() {
    //val inputStr = ResourceUtils.getResourceAsText("/inputs/day01/sample.txt")
    val inputStr = ResourceUtils.getResourceAsText("/inputs/day01/input.txt")
    val input: List<Pair<Int, Int>> =
        inputStr
            .replace(Regex("[ \\t]+"), ",")
            .split("\n")
            .map {
                it.split(",").map { numberStr ->
                    numberStr.trim().toInt()
                }
            }.map {
                it[0] to it[1]
            }

    val firstList = input.map { it.first }
    val secondList = input.map { it.second }

    val distance: Int = firstList.sorted().zip(secondList.sorted()).sumOf {
        abs(
            it.first - it.second
        )
    }

    println("Distance: $distance")
}