package com.bohdanzdaniekin.racoontest.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitInteropInteractionMode
import androidx.compose.ui.viewinterop.UIKitInteropProperties
import androidx.compose.ui.viewinterop.UIKitViewController
import com.bohdanzdaniekin.racoontest.LocalNativeViewFactory

@OptIn(ExperimentalComposeUiApi::class)
@Composable
actual fun NativeDatePicker(
    initialDate: Long?,
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier
) {
    val factory = LocalNativeViewFactory.current
    UIKitViewController(
        modifier = modifier
            .fillMaxSize(),
        factory = {
            factory.createDatePickerView(
                initialDate = initialDate,
                onDateSelected = { date ->
                    onDateSelected(date)
                },
                onDismiss = onDismiss
            )
        },
        properties = UIKitInteropProperties(
            interactionMode = UIKitInteropInteractionMode.NonCooperative
        )
    )
}