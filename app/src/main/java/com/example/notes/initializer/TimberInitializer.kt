package com.example.notes.initializer

import com.example.notes.BuildConfig
import timber.log.Timber

/**
 * Created by M.Reza Sulaiman on 21/12/20
 * Jepara, Indonesia.
 */

object TimberInitializer {

    fun init() {
        if (BuildConfig.DEBUG) {
            Timber.plant(object : Timber.DebugTree() {
                override fun createStackElementTag(element: StackTraceElement): String? {
                    return super.createStackElementTag(element) + " >> " + element.lineNumber
                }
            })

        } else {
            Timber.plant(TimberReleaseTree())
        }
    }

}