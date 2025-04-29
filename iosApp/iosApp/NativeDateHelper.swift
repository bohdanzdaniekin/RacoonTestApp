import ObjectiveC
import Foundation

@objc public class NativeDateHelper: NSObject {
    
    @objc public func getTodayAsString() -> String {
        let formatter = DateFormatter()
        formatter.dateStyle = .medium
        return formatter.string(from: Date())
    }
}
