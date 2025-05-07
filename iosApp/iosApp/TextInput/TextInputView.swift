import SwiftUI

struct TextInputView: View {
    var onSubmit: (String) -> Void = { _ in }
    var initialText: String = ""

    @State private var text: String = ""

    init(
        initialText: String = "",
        onSubmit: @escaping (String) -> Void = { _ in }
    ) {
        self.initialText = initialText
        self.onSubmit = onSubmit
        _text = State(initialValue: initialText)
    }

    var body: some View {
        VStack(spacing: 20) {
            TextField("Enter text", text: $text)
                .textFieldStyle(RoundedBorderTextFieldStyle())
                .padding()
            
            Button(action: {
                onSubmit(text)
            }) {
                Text("Submit")
            }
        }
        .padding()
    }
}

#Preview {
    TextInputView()
}
