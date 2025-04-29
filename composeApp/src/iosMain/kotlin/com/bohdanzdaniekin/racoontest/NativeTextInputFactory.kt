package com.bohdanzdaniekin.racoontest

import platform.UIKit.UIViewController

interface NativeTextInputFactory {
    fun createTextInput(
        initialText: String?,
        onSubmit: (String?) -> Unit
    ): UIViewController
}