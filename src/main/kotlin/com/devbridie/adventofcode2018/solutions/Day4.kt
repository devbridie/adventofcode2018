package com.devbridie.adventofcode2018.solutions

import com.devbridie.adventofcode2018.Day
import com.devbridie.adventofcode2018.executeDay

data class Time(val y: Int, val mo: Int, val d: Int, val h: Int, val mi: Int)

data class GuardRecord(val time: Time, val event: GuardEvent)
sealed class GuardEvent {
    data class SwitchedGuard(val toId: Int) : GuardEvent()
    object FellAsleep : GuardEvent()
    object WokeUp : GuardEvent()
}

private val solution1 = { data: String ->
    val timetable = createSleepTimes(data)
    val (mostSleepGuard, mostSleptTimes) = timetable.maxBy { it.value.count() }!!

    val minutes = mostSleptTimes.map { it.mi }
    val minuteMostSlept = minutes.maxByCount()!!.value
    mostSleepGuard * minuteMostSlept
}

private val solution2 = { data: String ->
    val timetable = createSleepTimes(data)
    // Of all guards, which guard is most frequently asleep on the same minute?
    // AKA what combo of (id, min) happens the most
    val guardsMinutes = timetable.entries.flatMap { (id, time) -> time.map { id to it.mi } }
    guardsMinutes.maxByCount()!!.value.let { (id, min) -> id * min }
}

fun createSleepTimes(data: String): Map<Int, List<Time>> {
    var events = data.split("\n").map(::parseLine).sortByTime()
    val timesSlept = mutableMapOf<Int, List<Time>>()
    while (events.isNotEmpty()) {
        val id = (events.first().event as GuardEvent.SwitchedGuard).toId
        val sleepEvents = events.drop(1).takeWhile { it.event !is GuardEvent.SwitchedGuard }
        sleepEvents.chunked(2).forEach { (sleep, wake) ->
            if (!(sleep.event is GuardEvent.FellAsleep && wake.event is GuardEvent.WokeUp)) error("Non-expected format")
            (sleep.time.mi until wake.time.mi).forEach { min ->
                timesSlept[id] = timesSlept.getOrDefault(id, listOf()) + sleep.time.withMinute(min)
            }
        }
        events = events.drop(1 + sleepEvents.size)
    }
    return timesSlept
}

private fun <T : Any> List<T>.maxByCount() = associateBy { m -> count { it == m } }.maxBy { it.key }

private fun parseLine(line: String): GuardRecord {
    val x = Regex("\\[(.*)-(.*)-(.*) (.*):(.*)] (.*)").matchEntire(line)!!.groupValues
    val msg = x[6]
    val event = when {
        msg.startsWith("Guard") -> GuardEvent.SwitchedGuard(msg.split(" ")[1].drop(1).toInt())
        msg.startsWith("falls") -> GuardEvent.FellAsleep
        msg.startsWith("wakes") -> GuardEvent.WokeUp
        else -> error("Unknown event")
    }
    return GuardRecord(Time(x[1].toInt(), x[2].toInt(), x[3].toInt(), x[4].toInt(), x[5].toInt()), event)
}

fun List<GuardRecord>.sortByTime() =
    sortedWith(compareBy<GuardRecord> { it.time.y }.thenBy { it.time.mo }.thenBy { it.time.d }.thenBy { it.time.h }.thenBy { it.time.mi })

fun Time.withMinute(minute: Int) = this.copy(mi = minute)

val day4 = Day(4, solution1, solution2)

fun main(args: Array<String>) = executeDay(day4)