package com.bohdanzdaniekin.racoontest.screen

import kotlinx.datetime.LocalDate

sealed interface MainScreenEffect {

    data class ShowDatePicker(val initialDate: LocalDate?) : MainScreenEffect

    data class ShowTextInput(val initialInput: String?) : MainScreenEffect
}
