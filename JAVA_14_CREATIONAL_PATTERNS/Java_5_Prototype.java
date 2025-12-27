package JAVA_14_CREATIONAL_PATTERNS;

/**
 * PROTOTYPE PATTERN – DOCUMENT CLONE EXAMPLE
 */
public class Java_5_Prototype {
    public static void main(String[] args) throws CloneNotSupportedException {

        Document original = new Document("Original Report", "This is the content");
        Document clone = original.clone();

        System.out.println(original);
        System.out.println(clone);
    }
}

// ==================== PRODUCT ====================
class Document implements Cloneable {
    private String title;
    private String content;

    public Document(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @Override
    protected Document clone() throws CloneNotSupportedException {
        return (Document) super.clone();
    }

    @Override
    public String toString() {
        return "Document [title=" + title + ", content=" + content + "]";
    }
}

/*
================ PROTOTYPE – FEATURES & DESIGN =================
WHAT:
- Creates a copy of existing object without using new keyword.
- Useful when object creation is costly.
WHY IT EXISTS:
- Avoids expensive object initialization.
- Enables runtime cloning of complex objects.
INTERNAL WORKING:
- Implements Cloneable interface
- Overrides clone() method
TIME COMPLEXITY: O(1) shallow clone
CORE FEATURES:
- Fast duplication
- Supports deep copy if implemented
REAL SYSTEM USAGE:
- Document editors, graphics objects, configuration templates
*/
