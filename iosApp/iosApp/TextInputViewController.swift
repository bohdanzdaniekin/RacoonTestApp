import UIKit
import SwiftUI

@objc public class TextInputViewController: NSObject {
    
    @objc static public func TextInputViewController(
        initialText: String,
        onSubmit: @escaping (String) -> Void
    ) -> UIViewController {
        let view = TextInputView(initialText: initialText, onSubmit: onSubmit)
        return UIHostingController(rootView: view)
    }
}
