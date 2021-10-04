package ru.vasilev.labs.logger

import android.util.Log

class EventLogger {
    companion object {

        @JvmStatic
        private val TAG = "EventLogger"

        @JvmStatic
        private val events = ArrayList<Event>()

        @JvmStatic
        fun add(event: Event) {
            events.add(event)
        }

        @JvmStatic
        fun print() {
            Log.e(TAG, events.toString())
        }

        @JvmStatic
        fun clear() {
            events.clear()
        }
    }
}