package JAVA_11_ADVANDED_JAVA;

/**
 * Topic: Cloneable & clone()
 */
public class Java_9_Cloneable implements Cloneable {

    private String name;

    public Java_9_Cloneable(String name) {
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone(); // shallow copy
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Java_9_Cloneable original = new Java_9_Cloneable("Shivam");
        Java_9_Cloneable copy = (Java_9_Cloneable) original.clone();

        System.out.println("Original: " + original.name);
        System.out.println("Copy: " + copy.name);
    }
}

/*
================================================================================
CLONEABLE & CLONE() – DEEP DIVE
================================================================================

WHAT:
-----
• Cloneable interface → marker interface
• Object.clone() → creates shallow copy of object

WHY IT EXISTS:
--------------
• Avoid manual copy constructor
• Useful in caching, prototyping
• Provides object duplication

INTERNAL WORKING:
-----------------
• Object.clone() creates field-by-field copy
• Shallow copy → references copied, not objects
• Deep copy requires manual cloning of nested objects
• Throws CloneNotSupportedException if class does not implement Cloneable

CORE FEATURES:
--------------
✔ Shallow copy by default
✔ Marker interface
✔ Supports arrays and custom objects

ENTERPRISE PITFALLS:
-------------------
❌ Forgetting to implement Cloneable → exception
❌ Assuming deep copy → nested objects still shared
❌ Mutable fields can break copies

REAL SYSTEM USAGE:
-----------------
✔ Prototyping objects
✔ Caching copies
✔ Undo / redo systems

INTERVIEW QUESTIONS + ANSWERS:
------------------------------
Q1: Difference between shallow and deep copy?
A: Shallow → references shared; Deep → full new object copy

Q2: Why Cloneable is a marker interface?
A: No methods; signals JVM that clone() is supported

INTERVIEW ONE-LINER:
-------------------
"Cloneable allows object duplication via shallow copy; deep copy requires custom implementation for nested references."
================================================================================
*/
