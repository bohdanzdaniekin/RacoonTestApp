package com.bohdanzdaniekin.racoontest.navigation

import platform.UIKit.UIAdaptivePresentationControllerDelegateProtocol
import platform.UIKit.UIPresentationController
import platform.darwin.NSObject

class DismissDelegate(
    private val onDismiss: () -> Unit
) : NSObject(), UIAdaptivePresentationControllerDelegateProtocol {

    override fun presentationControllerDidDismiss(presentationController: UIPresentationController) {
        onDismiss()
    }
}