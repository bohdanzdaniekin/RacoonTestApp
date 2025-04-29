package com.bohdanzdaniekin.racoontest.di

import com.bohdanzdaniekin.racoontest.screen.MainScreenViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::MainScreenViewModel)
}

val appModule = listOf(platformModule, viewModelModule)