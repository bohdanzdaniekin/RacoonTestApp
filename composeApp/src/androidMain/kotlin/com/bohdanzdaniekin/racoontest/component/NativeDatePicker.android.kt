package com.bohdanzdaniekin.racoontest.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
actual fun NativeDatePicker(
    initialDate: Long?,
    onDateSelected: (Long?) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier
) {
    val state = rememberDatePickerState(
        initialSelectedDateMillis = initialDate
    )

    AnimatedVisibility(true) {
        DatePickerDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(onClick = {
                    onDateSelected(state.selectedDateMillis)
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text("Cancel")
                }
            }
        ) {
            DatePicker(state = state)
        }
    }
}

@Preview
@Composable
fun NativeDatePickerPreview() {
    MaterialTheme {
        NativeDatePicker(
            initialDate = null,
            onDateSelected = {},
            onDismiss = {},
            modifier = Modifier
        )
    }
}