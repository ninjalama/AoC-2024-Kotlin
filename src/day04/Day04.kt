package day04

import utils.ResourceUtils

enum class Direction(val dy: Int, val dx: Int) {
    UP(-1, 0),
    DOWN(1, 0),
    RIGHT(0, 1),
    RIGHT_DOWN(1,1),
    RIGHT_UP(-1,1),
    LEFT(0,-1),
    LEFT_UP(-1,-1),
    LEFT_DOWN(1, -1)
}

typealias WordSearch = List<String>

fun WordSearch.diagonalStr(wantedStrLength: Int, x: Int, y: Int, direction: Direction): String {
    tailrec fun dStr(wantedStrLength: Int, cRow: Int, cCol: Int, rowIncrement: Int, colIncrement: Int, str: String) : String {
        if (str.length == wantedStrLength || cRow < 0 || cCol < 0 || cRow >= this.size || cCol >= this[0].length) {
            return str
        }
        val nString = str + this[cRow][cCol]
        return dStr(wantedStrLength, cRow + rowIncrement, cCol + colIncrement, rowIncrement, colIncrement, nString)
    }
    return dStr(wantedStrLength, y, x, direction.dy, direction.dx,"")
}

fun part1(wordSearch: WordSearch) =
    wordSearch.flatMapIndexed { y, row ->
        row.mapIndexed { x, cell ->
            if (cell == 'X') {
                Direction.entries.map { direction ->
                    wordSearch.diagonalStr(4, x, y, direction) == "XMAS"
                }.count { it }
            } else 0
        }
    }.sum()

fun part2(wordSearch: WordSearch) =
    wordSearch.flatMapIndexed() { y, row ->
        row.mapIndexed { x, cell ->
            cell == 'A' && listOf(
                wordSearch.diagonalStr(3, x - 1, y - 1, Direction.RIGHT_DOWN),
                wordSearch.diagonalStr(3, x + 1, y - 1, Direction.LEFT_DOWN)
            ).let { dList ->
                dList + dList.map {
                    it.reversed()
                }
            }.filter { it == "MAS" }
                .size == 2
        }
    }.count { it }

fun main() {
    val inputStr = ResourceUtils.getResourceAsText("/inputs/day04/input.txt")
    val wordSearch: WordSearch = inputStr.split("\n").map { it.trim() }

    println("Number of XMAS: " + part1(wordSearch))
    println("Number of MAS: " + part2(wordSearch))
}
