package com.bohdanzdaniekin.racoontest.screen

sealed interface MainScreenEffect {

    data class ShowTextInput(val initialInput: String?) : MainScreenEffect
}
