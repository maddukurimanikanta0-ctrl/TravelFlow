package Booking;

import java.util.Stack;

public class UndoStack {

    private Stack<String> actions = new Stack<>();

    public void pushAction(String action) {
        actions.push(action);
    }

    public void undo() {
        if (actions.isEmpty()) {
            System.out.println("[UNDO] Nothing to undo.");
        } else {
            System.out.println("[UNDO] Reverted: " + actions.pop());
        }
    }
}
