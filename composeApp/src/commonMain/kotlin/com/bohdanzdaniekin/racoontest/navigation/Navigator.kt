package com.bohdanzdaniekin.racoontest.navigation

expect class Navigator {

    suspend fun pickText(initialText: String? = null): String?
}