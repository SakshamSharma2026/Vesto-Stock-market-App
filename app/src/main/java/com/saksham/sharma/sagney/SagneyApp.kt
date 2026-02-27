package com.saksham.sharma.sagney

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class SagneyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Log.e(TAG, "onCreate: ", )
    }
    
    companion object{
        const val TAG = "SagneyApp"
    }
}