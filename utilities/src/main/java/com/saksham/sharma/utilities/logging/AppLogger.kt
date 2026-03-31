package com.saksham.sharma.utilities.logging

import android.util.Log
import com.saksham.sharma.utilities.BuildConfig

object AppLogger {

    private const val TAG = "VestoApp"


    fun d(tag: String = TAG, msg: String) {
        if (BuildConfig.DEBUG)
            Log.d(tag, msg)
    }


    fun e(tag: String = TAG, msg: String) {
        if (BuildConfig.DEBUG)
            Log.e(tag, msg)
    }


}