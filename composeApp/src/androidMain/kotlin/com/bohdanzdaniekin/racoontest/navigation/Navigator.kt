package com.bohdanzdaniekin.racoontest.navigation

import androidx.fragment.app.FragmentActivity
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

            activity.supportFragmentManager.setFragmentResultListener(
                requestKey,
                activity
            ) { _, bundle ->
                val result = bundle.getString(TextInputFragment.KEY_TEXT).orEmpty()
                continuation.resume(result)
            }

            activity.supportFragmentManager.commit {
                replace(android.R.id.content, fragment)
                addToBackStack(null)
            }
        }
    }
}