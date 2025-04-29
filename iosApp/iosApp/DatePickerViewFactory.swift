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

struct NativeDatePicker: View {
    var initialDate: Date
    var onSubmit: (Date?) -> Void
    var onDismiss: () -> Void

    @State private var selectedDate: Date = Date()

    var body: some View {

        VStack(spacing: 16) {
            Text("Select Date")
                .font(.headline)
                .padding(.top)
            DatePicker(
                "Select date",
                selection: $selectedDate,
                displayedComponents: .date
            )
            .datePickerStyle(.wheel)
            .labelsHidden()

            HStack(alignment: .center) {
                Button("Cancel") {
                    onDismiss()
                }
                .padding(.trailing, 16)
                .padding(.bottom, 8)
                Button("Submit") {
                    onSubmit(selectedDate)
                }
                .padding(.leading, 16)
                .padding(.bottom, 8)
            }
        }
        .onAppear {
            selectedDate = initialDate
        }
        .padding()
        .background(Color.white)
        .padding(.horizontal, 8)
    }
}

#Preview {
    NativeDatePicker(initialDate: Date(), onSubmit: { _ in }, onDismiss: {})
}
