import SwiftUI

struct NativeDatePicker: View {
    var initialDate: Date
    var onSubmit: (Date?) -> Void
    var onDismiss: () -> Void

    @State private var selectedDate: Date = Date()

    var body: some View {
        ZStack {
            Color.black.opacity(0.25)
                .ignoresSafeArea()
                .contentShape(Rectangle())
                .onTapGesture {
                    onDismiss()
                }
            VStack(spacing: 16) {
                Text("Select Date")
                    .font(.headline)
                    .padding(.top)
                DatePicker(
                    "",
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
                    Button("Submit") {
                        onSubmit(selectedDate)
                    }
                    .padding(.leading, 16)
                }.padding(.bottom, 12)
            }.background(
                RoundedRectangle(cornerRadius: 16)
                    .fill(Color.white)
                    .shadow(color: .gray, radius: 2, x: 0, y: 2)
            )
            .onAppear {
                selectedDate = initialDate
            }
        }
    }
}

#Preview {
    NativeDatePicker(
        initialDate: .now,
        onSubmit: { _ in },
        onDismiss: {}
    )
}
