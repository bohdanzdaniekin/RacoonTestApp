package com.bohdanzdaniekin.racoontest.di

import com.bohdanzdaniekin.racoontest.navigation.Navigator
import com.bohdanzdaniekin.racoontest.RacoonTestApplication
import com.bohdanzdaniekin.racoontest.utils.ActivityProvider
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

actual val platformModule
    get() = module {
        single<ActivityProvider> { (androidApplication() as RacoonTestApplication).activityProvider }
        factory {
            Navigator(activityProvider = get())
        }
    }