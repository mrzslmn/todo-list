package com.example.notes.initializer

/**
 * Created by M.Reza Sulaiman on 21/12/20
 * Jepara, Indonesia.
 */

import android.util.Log
import timber.log.Timber

class TimberReleaseTree : Timber.Tree() {
    override fun isLoggable(tag: String?, priority: Int): Boolean {
        return when (priority) {
            Log.VERBOSE, Log.DEBUG -> false
            else -> //Only Log Warn, Error, WTF
                true
        }
    }

    override fun log(
        priority: Int,
        tag: String?,
        message: String,
        t: Throwable?) {
        if (isLoggable(tag, priority)) {
            // Message is short enough, doesn't need to be broken into chunks
            if (message.length < MAX_LOG_LENGTH) {
//                Crashlytics.log(priority, tag, message);
                return
            }
            var i = 0
            val length = message.length
            while (i < length) {
                var newline = message.indexOf('\n', i)
                newline = if (newline != -1) newline else length
                do {
                    val end =
                        Math.min(newline, i + MAX_LOG_LENGTH)
                    val part = message.substring(i, end)
                    Timber.d(part)
                    //                    Crashlytics.log(priority, tag, part);
                    i = end
                } while (i < newline)
                i++
            }
        }
    }

    companion object {
        private const val MAX_LOG_LENGTH = 4000
    }
}