package com.bohdanzdaniekin.racoontest.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bohdanzdaniekin.racoontest.ext.collectAsEffectWithLifecycle
import com.bohdanzdaniekin.racoontest.navigation.Navigator
import kotlinx.datetime.LocalDate
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainScreen(modifier: Modifier = Modifier) {
    val viewModel = koinViewModel<MainScreenViewModel>()

    val state by viewModel.state.collectAsStateWithLifecycle()
    val navigator = koinInject<Navigator>()

    viewModel.effect.collectAsEffectWithLifecycle { effect ->
        when (effect) {
            is MainScreenEffect.ShowTextInput -> {
                val text = navigator.pickText(initialText = effect.initialInput)
                viewModel.onEvent(MainScreenEvent.OnTextSubmitted(text))
            }
        }
    }

    MainScreen(
        modifier = modifier,
        state = state,
        onPickDateClick = { viewModel.onEvent(MainScreenEvent.OnPickDateClicked) },
        onShowTextInputClick = { viewModel.onEvent(MainScreenEvent.OnShowTextInputClicked) },
        onDatePicked = { date -> viewModel.onEvent(MainScreenEvent.OnDatePicked(date)) },
        onDatePickerDismissed = { viewModel.onEvent(MainScreenEvent.OnPickerDismissed) }
    )
}

@Composable
private fun MainScreen(
    modifier: Modifier = Modifier,
    state: MainScreenState,
    onPickDateClick: () -> Unit,
    onDatePicked: (LocalDate?) -> Unit,
    onDatePickerDismissed: () -> Unit = {},
    onShowTextInputClick: () -> Unit,
) {
    MainScreenContent(
        modifier = modifier,
        showDatePicker = state.isDatePickerVisible,
        onPickDateClick = onPickDateClick,
        onDatePicked = onDatePicked,
        onDatePickerDismissed = onDatePickerDismissed,
        currentDate = state.date,
        onShowTextInputClick = onShowTextInputClick,
        text = state.text
    )
}