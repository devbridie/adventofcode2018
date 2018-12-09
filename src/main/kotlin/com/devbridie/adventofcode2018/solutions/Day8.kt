package com.devbridie.adventofcode2018.solutions

import com.devbridie.adventofcode2018.Day
import com.devbridie.adventofcode2018.executeDay

private data class Node(val children: List<Node>, val metadata: List<Int>)

private fun readTree(values: List<String>) = readNodes(values, 0).first

private fun readNodes(values: List<String>, position: Int): Pair<Node, Int> {
    val (childNodeCount, metadataCount) = values.slice(position until position + 2)
    var read = position + 2
    val children = (0 until childNodeCount.toInt()).map {
        val (out, pos) = readNodes(values, read)
        read = pos
        out
    }
    val afterMetadata = read + metadataCount.toInt()
    val metadata = values.slice(read until afterMetadata).map { it.toInt() }

    return Node(children, metadata) to afterMetadata
}

private val solution1 = { data: String ->
    val root = readTree(data.split(" "))
    fun Node.traverseMetadataEntries(): List<Int> {
        return this.metadata + this.children.flatMap { it.traverseMetadataEntries() }
    }
    root.traverseMetadataEntries().sum()
}

private val solution2 = { data: String ->
    val root = readTree(data.split(" "))

    fun Node.findValue(): Int {
        return if (this.children.isEmpty()) {
            this.metadata.sum()
        } else {
            this.metadata.map { index -> this.children.getOrNull(index-1)?.findValue() ?: 0 }.sum()
        }
    }
    root.findValue()
}

val day8 = Day(8, solution1, solution2)

fun main(args: Array<String>) = executeDay(day8, true, true)