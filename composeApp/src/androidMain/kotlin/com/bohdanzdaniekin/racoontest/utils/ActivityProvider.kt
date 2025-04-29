package com.bohdanzdaniekin.racoontest.utils

import android.app.Activity
import android.app.Application
import android.util.Log
import androidx.fragment.app.FragmentActivity

class ActivityProvider(application: Application) {

    var currentActivity: FragmentActivity? = null
        private set

    init {
        application.registerActivityLifecycleCallbacks(
            object : DefaultActivityLifecycleCallbacks {
                override fun onActivityResumed(activity: Activity) {
                    Log.d("ActivityProvider", "onActivityResumed: $activity")
                    currentActivity = activity as? FragmentActivity
                }

                override fun onActivityPaused(activity: Activity) {
                    Log.d("ActivityProvider", "onActivityResumed: $activity")
                    currentActivity = null
                }
            }
        )
    }
}