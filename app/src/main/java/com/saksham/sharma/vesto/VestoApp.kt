package com.saksham.sharma.vesto


import android.app.Application
import com.google.firebase.FirebaseApp
import com.saksham.sharma.utilities.logging.AppLogger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class VestoApp : Application() {

    override fun onCreate() {
        super.onCreate()
        FirebaseApp.initializeApp(this)
        AppLogger.d(msg = "Application is launched")
    }
}