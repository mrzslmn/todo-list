package com.example.notes.base

import android.app.Application
import com.example.notes.initializer.ObjectBox
import com.example.notes.initializer.TimberInitializer
import com.google.android.play.core.missingsplits.MissingSplitsManagerFactory
import timber.log.Timber

/**
 * Created by M.Reza Sulaiman on 21/12/20
 * Jepara, Indonesia.
 */
class BaseAct : Application() {

    companion object Constant {
        const val  TAG = "ObjectBoxExample"
    }

    override fun onCreate() {
        // Optional: if you distribute your app as App Bundle, provides detection of incomplete
        // installs due to sideloading and helps users reinstall the app from Google Play.
        // https://docs.objectbox.io/android/app-bundle-and-split-apk
        if (MissingSplitsManagerFactory.create(this).disableAppIfMissingRequiredSplits()) {
            return // Skip app initialization.
        }

        super.onCreate()
        ObjectBox.init(this)
        TimberInitializer.init()
        Timber.d("BaseAct() running")
    }
}