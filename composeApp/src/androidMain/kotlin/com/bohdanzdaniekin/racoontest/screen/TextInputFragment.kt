package com.bohdanzdaniekin.racoontest.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.text.input.TextFieldValue
import androidx.core.bundle.bundleOf
import androidx.fragment.app.Fragment
import kotlinx.coroutines.launch

class TextInputFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
        setContent {
            MaterialTheme {
                val (text, updateText) = remember {
                    mutableStateOf(
                        TextFieldValue(initialText().orEmpty())
                    )
                }
                val scope = rememberCoroutineScope()
                TextInputScreen(
                    text = text,
                    onTextChange = updateText,
                    onSubmit = {
                        scope.launch {
                            parentFragmentManager.setFragmentResult(
                                KEY_REQUEST,
                                bundleOf(KEY_TEXT to text.text)
                            )
                            parentFragmentManager.popBackStack()
                        }
                    },
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }

    private fun initialText(): String? = arguments?.getString(INITIAL_TEXT)

    companion object {

        private const val INITIAL_TEXT = "initial_text"

        const val KEY_TEXT = "text"
        const val KEY_REQUEST = "RESULT_FROM_FRAGMENT"

        fun newInstance(
            initialText: String? = null
        ): TextInputFragment {
            return TextInputFragment().apply {
                arguments = Bundle().apply {
                    putString(INITIAL_TEXT, initialText)
                }
            }
        }
    }
}