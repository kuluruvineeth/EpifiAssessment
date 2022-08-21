package com.kuluruvineeth.epifiassessment

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PanValidatorApp : Application() {

    override fun onCreate() {
        super.onCreate()
        application = this
    }

    companion object{
        lateinit var application: PanValidatorApp
    }
}