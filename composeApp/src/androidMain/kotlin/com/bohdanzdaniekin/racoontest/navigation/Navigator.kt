package com.bohdanzdaniekin.racoontest.navigation

import androidx.activity.addCallback
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.commit
import com.bohdanzdaniekin.racoontest.screen.TextInputFragment
import com.bohdanzdaniekin.racoontest.utils.ActivityProvider
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

actual class Navigator(
    private val activityProvider: ActivityProvider
) {

    private val currentActivity: FragmentActivity?
        get() = activityProvider.currentActivity

    actual suspend fun pickText(initialText: String?): String? {
        return suspendCancellableCoroutine { continuation ->
            val activity = currentActivity
            if (activity == null) {
                continuation.resume(null)
                return@suspendCancellableCoroutine
            }

            val requestKey = TextInputFragment.KEY_REQUEST
            val fragment = TextInputFragment.newInstance(initialText)

            val listener = FragmentResultListener { _, bundle ->
                val result = bundle.getString(TextInputFragment.KEY_TEXT).orEmpty()
                continuation.resume(result)
            }
            activity
                .supportFragmentManager
                .setFragmentResultListener(requestKey, activity, listener)

            val onBackPressedCallback = activity
                .onBackPressedDispatcher
                .addCallback(fragment) {
                    activity
                        .supportFragmentManager
                        .popBackStack()
                    continuation.resume(null)
                }

            activity.supportFragmentManager.commit {
                replace(android.R.id.content, fragment)
                addToBackStack(null)
            }

            continuation.invokeOnCancellation {
                activity
                    .supportFragmentManager
                    .clearFragmentResultListener(TextInputFragment.KEY_REQUEST)

                onBackPressedCallback.remove()
            }
        }
    }
}