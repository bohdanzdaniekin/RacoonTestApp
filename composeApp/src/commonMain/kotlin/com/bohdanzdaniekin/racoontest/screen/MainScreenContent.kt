package com.bohdanzdaniekin.racoontest.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bohdanzdaniekin.racoontest.component.NativeDatePicker
import com.bohdanzdaniekin.racoontest.ext.fromEpochMillis
import com.bohdanzdaniekin.racoontest.ext.toEpochMillis
import kotlinx.datetime.LocalDate
import kotlinx.datetime.format
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char

private fun formatDate(date: LocalDate?): String? {
    val format = LocalDate.Format {
        dayOfMonth()
        char('-')
        monthName(MonthNames.ENGLISH_FULL)
        char('-')
        year()
    }
    return date?.format(format)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreenContent(
    modifier: Modifier = Modifier,
    showDatePicker: Boolean = false,
    currentDate: LocalDate? = null,
    onPickDateClick: () -> Unit = {},
    onDatePicked: (LocalDate?) -> Unit = {},
    onDatePickerDismissed: () -> Unit = {},
    onShowTextInputClick: () -> Unit = {},
    text: String? = null
) {
    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                windowInsets = TopAppBarDefaults.windowInsets,
                title = {
                    Text(
                        "My Test App",
                        style = MaterialTheme.typography.headlineMedium
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = onPickDateClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 36.dp),
                shape = RoundedCornerShape(6.dp)
            ) {
                Text("Date")
            }
            Spacer(modifier = Modifier.height(8.dp))
            AnimatedVisibility(currentDate != null) {
                Text(
                    text = formatDate(currentDate).orEmpty(),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
            Spacer(modifier = Modifier.height(42.dp))
            Button(
                onClick = onShowTextInputClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 36.dp),
                shape = RoundedCornerShape(6.dp)
            ) {
                Text("Text")
            }
            Spacer(modifier = Modifier.height(8.dp))
            AnimatedVisibility(!text.isNullOrBlank()) {
                Text(
                    text = text.orEmpty(),
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        }
    }
    AnimatedVisibility(showDatePicker,) {
        NativeDatePicker(
            initialDate = currentDate?.toEpochMillis(),
            onDismiss = onDatePickerDismissed,
            onDateSelected = { date ->
                onDatePicked(date?.let(LocalDate.Companion::fromEpochMillis))
            },
        )
    }
}