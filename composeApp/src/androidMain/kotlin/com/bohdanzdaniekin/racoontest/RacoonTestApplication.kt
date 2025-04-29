package com.bohdanzdaniekin.racoontest

import android.app.Application
import com.bohdanzdaniekin.racoontest.di.initKoin
import com.bohdanzdaniekin.racoontest.utils.ActivityProvider
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class RacoonTestApplication : Application() {

    val activityProvider = ActivityProvider(this)

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(this@RacoonTestApplication)
        }
    }
}