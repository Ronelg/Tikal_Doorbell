package com.tikal.doorbell.android

import android.app.Application
import com.tikal.doorbell.mobile.BuildConfig
import timber.log.Timber

class DoorbellApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
