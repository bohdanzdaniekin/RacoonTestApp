package com.bohdanzdaniekin.racoontest.screen

import kotlinx.datetime.LocalDate

sealed interface MainScreenEvent {

    data object OnPickDateClicked : MainScreenEvent

    data object OnShowTextInputClicked : MainScreenEvent

    data object OnPickerDismissed : MainScreenEvent

    data class OnDatePicked(val date: LocalDate?) : MainScreenEvent

    data class OnTextSubmitted(val text: String?) : MainScreenEvent
}
