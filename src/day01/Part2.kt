package day01

import utils.ResourceUtils

fun main() {
    //val inputStr = ResourceUtils.getResourceAsText("/inputs/day01/sample_01.txt")
    val inputStr = ResourceUtils.getResourceAsText("/inputs/day01/input.txt")
    val input: List<Pair<Int, Int>> =
        inputStr
            .split("\n")
            .map { numberLine ->
                numberLine.split(Regex("[ \\t]+")).map { numberStr ->
                    numberStr.trim().toInt()
                }
            }.map {
                it[0] to it[1]
            }

    val firstList = input.map { it.first }
    val secondList = input.map { it.second }

    val similaritySum = firstList.sumOf { number ->
        number * secondList.count { it == number }
    }
    println("Similarity: $similaritySum")
}