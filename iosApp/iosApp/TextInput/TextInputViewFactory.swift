import ComposeApp
import SwiftUI

class TextInputViewFactory: NativeTextInputFactory {

    static let shared = TextInputViewFactory()

    func createTextInput(
        initialText: String?,
        onSubmit: @escaping (String?) -> Void
    ) -> UIViewController {
        return TextInputViewController.TextInputViewController(
            initialText: initialText ?? "",
            onSubmit: onSubmit
        )
    }
}
