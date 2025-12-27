package JAVA_15_BEHAVIORAL_PATTERNS;

import java.util.Stack;

/**
 * MEMENTO PATTERN – TEXT EDITOR EXAMPLE
 */
public class Java_10_Memento {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();

        editor.write("Version1");
        editor.save();
        editor.write("Version2");
        editor.save();
        editor.write("Version3");

        System.out.println("Current content: " + editor.getContent());

        editor.undo();
        System.out.println("After undo: " + editor.getContent());

        editor.undo();
        System.out.println("After second undo: " + editor.getContent());
    }
}

// ==================== MEMENTO ====================
class EditorMemento {
    private final String state;
    public EditorMemento(String state) { this.state = state; }
    public String getState() { return state; }
}

// ==================== ORIGINATOR ====================
class TextEditor {
    private String content = "";
    private final Stack<EditorMemento> history = new Stack<>();

    public void write(String text) { content = text; }
    public void save() { history.push(new EditorMemento(content)); }
    public void undo() {
        if(!history.isEmpty()) {
            EditorMemento memento = history.pop();
            content = memento.getState();
        }
    }
    public String getContent() { return content; }
}

/*
================ MEMENTO PATTERN – FEATURES & DESIGN =================
WHAT:
- Captures and restores object state without violating encapsulation.
- Example: Undo in text editor.
WHY IT EXISTS:
- Preserve object states for rollback.
REAL SYSTEM USAGE:
- Undo-redo functionality, transactional systems, versioning
*/
