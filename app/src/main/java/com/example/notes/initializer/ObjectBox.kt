package com.example.notes.initializer

import android.content.Context
import com.example.notes.base.BaseAct
import com.example.notes.persistence.entity.MyObjectBox
import io.objectbox.BoxStore
import io.objectbox.android.AndroidObjectBrowser
import io.objectbox.android.BuildConfig
import timber.log.Timber

/**
 * Created by M.Reza Sulaiman on 21/12/20
 * Jepara, Indonesia.
 */
object ObjectBox {
    lateinit var boxStore: BoxStore

    fun init(context: Context) {
        boxStore = MyObjectBox.builder()
            .androidContext(context.applicationContext)
            .build()

        if (com.example.notes.BuildConfig.DEBUG) {
//            Timber.tag(BaseApplication.TAG).i(
//                "Started..: %s ObjectBox version: %s",
//                true,
//                BoxStore.getVersion() + " (" + BoxStore.getVersionNative() + ")"
//            )
            // Enable Data Browser on debug builds.
            // https://docs.objectbox.io/data-browser
            val started =  AndroidObjectBrowser(boxStore).start(context.applicationContext)
            Timber.tag(BaseAct.TAG).i("init() object browser started: %s", started)
            Timber.d("init() object browser started")
        }
    }

}