package ru.vasilev.labs.logger

import java.util.*

class Event(private var type: EventType, private var message: String) {
    private val dateTime: Date = Date()

    override fun toString(): String {
        return "\n$type: $dateTime [$message]"
    }
}