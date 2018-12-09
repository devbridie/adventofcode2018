package com.devbridie.adventofcode2018.solutions

import com.devbridie.adventofcode2018.Day
import com.devbridie.adventofcode2018.executeDay

private data class GameState(
    val players: Int,
    val lastMarblePoints: Int = 0,
    val playerScores: List<Int> = List(players) { 0 },
    val marbles: List<Int> = listOf(0),
    val getLastPlacedPlayer: Int = -1,
    val currentMarblePos: Int = 0
) {
    override fun toString(): String {
        return "[$lastMarblePoints] " + marbles.mapIndexed { index, i -> if (index == currentMarblePos) "($i)" else "$i" }.joinToString(" ")
    }
}

private fun GameState.insertMarble(): GameState {
    val next = lastMarblePoints + 1
    val newPlayerTurn = (getLastPlacedPlayer + 1) % players
    return if (next % 23 == 0) {
        var removeIndex = currentMarblePos - 7
        while (removeIndex < 0) removeIndex += marbles.size
        val removeValue = marbles[removeIndex]
        val newPlayersScore = playerScores.mapIndexed { index, i -> if (index == newPlayerTurn) i + removeValue + next else i }

        val newMarbles = marbles.subList(0, removeIndex) + marbles.subList(removeIndex + 1, marbles.size)
        GameState(this.players, next, newPlayersScore, newMarbles, newPlayerTurn, removeIndex)
    } else {
        val newPos = currentMarblePos + 2
        val realPos = newPos % marbles.size
        val newMarbles = marbles.subList(0, realPos) + next + marbles.subList(realPos, marbles.size)

        GameState(this.players, next, playerScores, newMarbles, newPlayerTurn, realPos)
    }
}

private val solution1 = { data: String ->
    val (players, value) = Regex("(.*) players; last marble is worth (.*) points").matchEntire(data)!!.groupValues.drop(1).map(String::toInt)
    val last = (0 until value).fold(GameState(players)) { acc, _ -> acc.insertMarble() }
    last.playerScores.max()!!
}

private val solution2 = { data: String ->
    val (players, value) = Regex("(.*) players; last marble is worth (.*) points").matchEntire(data)!!.groupValues.drop(1).map(String::toInt)
    val last = (0 until value*100).fold(GameState(players)) { acc, _ -> acc.insertMarble() }
    last.playerScores.max()!!
}

val day9 = Day(9, solution1, solution2)

fun main(args: Array<String>) = executeDay(day9, true, true)