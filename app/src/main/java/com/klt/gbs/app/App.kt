package com.klt.gbs.app

import android.app.Application
import com.klt.gbs.BuildConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

// Hilt : 1 - declare and build di graph for app
@HiltAndroidApp
class App : Application() {
    override fun onCreate() {
        super.onCreate()
        //for log
        if (BuildConfig.DEBUG) Timber.plant(Timber.DebugTree())
    }
}