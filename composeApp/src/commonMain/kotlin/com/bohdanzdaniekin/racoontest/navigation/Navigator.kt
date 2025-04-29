package com.bohdanzdaniekin.racoontest.navigation

import kotlinx.datetime.LocalDate

expect class Navigator {

    suspend fun pickText(initialText: String? = null): String?
}