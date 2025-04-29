package com.bohdanzdaniekin.racoontest.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.onClick
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.UIKitInteropInteractionMode
import androidx.compose.ui.viewinterop.UIKitInteropProperties
import androidx.compose.ui.viewinterop.UIKitViewController
import com.bohdanzdaniekin.racoontest.LocalNativeViewFactory

@OptIn(ExperimentalComposeUiApi::class, ExperimentalFoundationApi::class)
@Composable
actual fun NativeDatePicker(
    initialDate: Long?,
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier
) {
    val factory = LocalNativeViewFactory.current
    Box(
        modifier = Modifier
            .onClick { onDismiss() }
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.05f)),
        contentAlignment = Alignment.Center
    ) {
        UIKitViewController(
            modifier = modifier
                .size(350.dp)
                .clip(RoundedCornerShape(16.dp))
                .shadow(elevation = 4.dp),
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
                interactionMode = UIKitInteropInteractionMode.NonCooperative,
            )
        )
    }
}