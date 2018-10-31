package com.tikal.doorbell.android

import android.app.Application
import timber.log.Timber

class DoorbellApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}
