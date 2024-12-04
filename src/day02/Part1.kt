package day02

import utils.ResourceUtils

fun main() {
    //val inputStr = ResourceUtils.getResourceAsText("/inputs/day02/sample_01.txt")
    val inputStr = ResourceUtils.getResourceAsText("/inputs/day02/input.txt")

    val input: List<List<Int>> =
        inputStr
            .split("\n")
            .map { inputLine ->
                inputLine.split(" ")
                    .map { numberStr ->
                        numberStr.trim().toInt()
                    }
            }

    val allIncreasingByMax3: (List<Int>) -> Boolean = { intList ->
        intList.windowed(2, 1).all { wList ->
            wList.first() < wList.last() && (wList.last() - wList.first() <= 3)
        }
    }

    val okRecordsCount = input.map { intList ->
        allIncreasingByMax3(intList) || allIncreasingByMax3(intList.reversed())
    }.count { it }

    println("Number of ok records: $okRecordsCount")
}