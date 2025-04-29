package com.bohdanzdaniekin.racoontest.navigation

import com.bohdanzdaniekin.racoontest.NativeTextInputFactory
import kotlinx.coroutines.suspendCancellableCoroutine
import platform.UIKit.UIApplication
import kotlin.coroutines.resume

actual class Navigator(
    private val textInputFactory: NativeTextInputFactory
) {

    actual suspend fun pickText(initialText: String?): String? {
        return suspendCancellableCoroutine { continuation ->
            val controller = UIApplication.sharedApplication.keyWindow?.rootViewController
                ?: return@suspendCancellableCoroutine

            val hosting = textInputFactory.createTextInput(
                initialText = initialText,
                onSubmit = { result: String? ->
                    controller.dismissViewControllerAnimated(true, null)
                    continuation.resume(result)
                }
            )
//            val hosting = TextInputViewController.TextInputViewController(
//                initialText = initialText,
//                onSubmit = { result: String? ->
//                    controller.dismissViewControllerAnimated(true, null)
//                    continuation.resume(result)
//                }
//            )
//            TextViewControllerKt
//            val hosting = UIHostingController(rootView = TextInputView { result ->
//                controller?.dismissViewControllerAnimated(true, null)
//                continuation.resume(result)
//            })
//
            controller.presentViewController(hosting, true, null)
        }
    }
}
