package day01

import utils.ResourceUtils

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

    val similarityMap: Map<Int, Int> = similarityMap(firstList, secondList, emptyMap())

    val similaritySum = firstList.sumOf { number ->
        number * similarityMap.getOrDefault(number, 0)
    }

    println("Similarity: $similaritySum")
}

fun similarityMap(input: List<Int>, hayStack: List<Int>, similarityMap: Map<Int, Int>): Map<Int, Int> {
    if (input.isEmpty()) return similarityMap

    val number = input.first()

    val updatedMap = if (similarityMap.containsKey(number)) {
        similarityMap
    } else {
        val count = hayStack.count { it == number }
        similarityMap + (number to count)
    }

    return similarityMap(input.drop(1), hayStack, updatedMap)
}