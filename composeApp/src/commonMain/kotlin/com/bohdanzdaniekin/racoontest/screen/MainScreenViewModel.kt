package com.bohdanzdaniekin.racoontest.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch

class MainScreenViewModel : ViewModel() {

    private val _state = MutableStateFlow(MainScreenState())
    val state = _state.asStateFlow()

    private val _effect = Channel<MainScreenEffect>(Channel.BUFFERED)
    val effect = _effect.receiveAsFlow()

    fun onEvent(event: MainScreenEvent) {
        when (event) {
            is MainScreenEvent.OnPickDateClicked -> {
//                emitEffect(
//                    MainScreenEffect.ShowDatePicker(initialDate = state.value.date)
//                )
                _state.updateAndGet { state ->
                    state.copy(isDatePickerVisible = true)
                }
            }
            is MainScreenEvent.OnShowTextInputClicked -> {
                emitEffect(
                    MainScreenEffect.ShowTextInput(initialInput = state.value.text)
                )
            }
            is MainScreenEvent.OnDatePicked -> {
                _state.updateAndGet { state ->
                    state.copy(date = event.date, isDatePickerVisible = false)
                }
            }
            MainScreenEvent.OnPickerDismissed -> {
                _state.updateAndGet { state ->
                    state.copy(isDatePickerVisible = false)
                }
            }
            is MainScreenEvent.OnTextSubmitted -> {
                _state.updateAndGet { state ->
                    state.copy(text = event.text)
                }
            }
        }
    }

    private fun emitEffect(effect: MainScreenEffect) {
        viewModelScope.launch {
            _effect.send(effect)
        }
    }
}