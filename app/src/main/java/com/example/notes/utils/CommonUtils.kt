package com.example.notes.utils

import android.content.Context
import android.view.Gravity
import android.widget.Toast
import timber.log.Timber
import java.util.*
import kotlin.concurrent.timerTask

/**
 * Created by M.Reza Sulaiman on 03/08/2023
 * Jakarta, Indonesia.
 */
object CommonUtils {
    private var toast: Toast? = null
    private const val TOAST_TIMEOUT_MS: Long = 1000
    private var lastToastTime: Long = 0

    fun standardToast(context: Context,message: String) {
        Timber.d("standardToast toast :%s", toast)

        val now = System.currentTimeMillis();
        if (lastToastTime + TOAST_TIMEOUT_MS < now) {
            toast = Toast.makeText(
                context, message, Toast.LENGTH_SHORT
            )
            toast?.setGravity(Gravity.CENTER, 0, 100)
            toast?.show()
            lastToastTime = now;
        }
        Timer().schedule(timerTask {
            toast?.cancel()
        }, 1500)

    }
}