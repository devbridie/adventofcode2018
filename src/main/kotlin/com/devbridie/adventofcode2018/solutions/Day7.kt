package com.devbridie.adventofcode2018.solutions

import com.devbridie.adventofcode2018.Day
import com.devbridie.adventofcode2018.executeDay

private fun createDependencyMap(data: String) = mutableMapOf<String, List<String>>().also { map ->
    data.split("\n").forEach {
        val (name, dependency) = Regex("^Step (.*) must .* step (.*) can.*$").matchEntire(it)!!.destructured
        val list = map.getOrDefault(name, listOf())
        map[name] = list + dependency
        map[dependency] = map.getOrDefault(dependency, listOf())
    }
}


private val solution1 = { data: String ->
    val map = createDependencyMap(data)

    val done = mutableSetOf<String>()
    while (map.isNotEmpty()) {
        // find jobs that are never a dep of another job
        val starts = map.keys.filter { key -> !map.values.any { it.contains(key) } }
        val next = starts.sorted().first()
        map.remove(next)
        done += next
    }
    done.joinToString("")
}

private data class Worker(val workingOn: String, val doneTime: Int)

val day7solution2 = { data: String, workerSize: Int ->
    val penalty = 30

    val workers = mutableListOf<Worker>()
    val map = createDependencyMap(data)
    var time = 0
    while (map.isNotEmpty()) {
        workers.filter { it.doneTime == time }.forEach {
            workers.remove(it)
            map.remove(it.workingOn)
        }

        // find jobs that are never a dep of another job
        val startable = map.keys.filter { key -> !map.values.any { it.contains(key) } && workers.none { it.workingOn == key } }
        startable.sorted().forEach { next ->
            if (workers.size <= workerSize) {
                workers.add(Worker(next, time + penalty + (next[0] - 'A') + 1))
            }
        }
        time++
    }
    time
}

val day7 = Day(7, solution1, { data -> day7solution2(data, 5)})

fun main(args: Array<String>) = executeDay(day7, true, true)