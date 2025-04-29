package com.bohdanzdaniekin.racoontest.screen

import kotlinx.datetime.LocalDate

data class MainScreenState(
    val date: LocalDate? = null,
    val isDatePickerVisible: Boolean = false,
    val text: String? = null
)
