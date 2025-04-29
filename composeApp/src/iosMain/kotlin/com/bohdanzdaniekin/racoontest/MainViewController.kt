package com.bohdanzdaniekin.racoontest

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.window.ComposeUIViewController
import com.bohdanzdaniekin.racoontest.di.initKoin
import com.bohdanzdaniekin.racoontest.di.platformModule
import com.bohdanzdaniekin.racoontest.di.viewModelModule
import org.koin.dsl.module

fun MainViewController(
    datePickerViewFactory: NativeDatePickerViewFactory,
    textInputFactory: NativeTextInputFactory
) = ComposeUIViewController(
    configure = {
        initKoin {
            modules(
                module {
                    single<NativeTextInputFactory> { textInputFactory }
                },
                platformModule,
                viewModelModule
            )
        }
    }
) {
    CompositionLocalProvider(
        LocalNativeViewFactory provides datePickerViewFactory
    ) {
        App()
    }
}

val LocalNativeViewFactory = staticCompositionLocalOf<NativeDatePickerViewFactory> {
    error("No date picker view factory provided.")
}