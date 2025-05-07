import ComposeApp
import SwiftUI

class DatePickerViewFactory: NativeDatePickerViewFactory {

    static var shared = DatePickerViewFactory()

    func createDatePickerView(
        initialDate: KotlinLong?,
        onDateSelected: @escaping (KotlinLong?) -> Void,
        onDismiss: @escaping () -> Void
    ) -> UIViewController {
        let date =
            if let initialDate {
                Date(timeIntervalSince1970: initialDate.doubleValue / 1000.0)
            } else {
                Date()
            }

        let view = NativeDatePicker(
            initialDate: date,
            onSubmit: {
                if let date = $0 {
                    onDateSelected(
                        KotlinLong(
                            value: Int64(date.timeIntervalSince1970 * 1000.0)
                        )
                    )
                } else {
                    onDateSelected(nil)
                }
            },
            onDismiss: onDismiss
        )

        return UIHostingController(rootView: view)
    }
}
