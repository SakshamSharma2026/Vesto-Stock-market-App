package com.saksham.sharma.utilites.logging

import android.util.Log
import com.saksham.sharma.utilites.BuildConfig

object AppLogger {

    private const val TAG = "SagneyApp"


    fun d(tag: String = TAG, msg: String) {
        if (BuildConfig.DEBUG)
            Log.d(tag, msg)
    }


    fun e(tag: String = TAG, msg: String) {
        if (BuildConfig.DEBUG)
            Log.e(tag, msg)
    }


}