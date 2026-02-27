package com.saksham.sharma.sagney

import android.app.Application
import android.util.Log
import com.saksham.sharma.utilites.logging.AppLogger
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class SagneyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        AppLogger.d(msg = "Application is launched")
    }
}