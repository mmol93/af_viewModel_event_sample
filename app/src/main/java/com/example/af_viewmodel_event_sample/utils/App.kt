package com.example.af_viewmodel_event_sample.utils

import android.app.Application
import timber.log.Timber

class App:Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(MyTimber())
    }
}