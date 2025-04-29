package com.bohdanzdaniekin.racoontest

import platform.UIKit.UIViewController

interface NativeDatePickerViewFactory {

    fun createDatePickerView(
        initialDate: Long?,
        onDateSelected: (Long?) -> Unit,
        onDismiss: () -> Unit
    ): UIViewController
}