package com.saksham.sharma.vesto

import android.app.Application
import com.saksham.sharma.utilites.logging.AppLogger
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class VestoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        AppLogger.d(msg = "Application is launched")
    }
}