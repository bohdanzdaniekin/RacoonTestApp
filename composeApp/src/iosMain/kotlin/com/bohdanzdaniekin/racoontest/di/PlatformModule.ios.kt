package com.bohdanzdaniekin.racoontest.di

import com.bohdanzdaniekin.racoontest.navigation.Navigator
import org.koin.dsl.module

actual val platformModule = module {
    factory { Navigator(get()) }
}
