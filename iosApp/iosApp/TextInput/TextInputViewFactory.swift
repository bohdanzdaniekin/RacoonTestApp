import ComposeApp
import SwiftUI

class TextInputViewFactory: NativeTextInputFactory {

    static let shared = TextInputViewFactory()

    func createTextInput(
        initialText: String?,
        onSubmit: @escaping (String?) -> Void
    ) -> UIViewController {
        let view = TextInputView(initialText: initialText ?? "", onSubmit: onSubmit)
        return UIHostingController(rootView: view)
    }
}
