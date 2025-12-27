package JAVA_12_JAVA_8_PLUS_FEATURES;

/**
 * Topic: Default Methods in Interface
 */
interface MyInterface {
    void abstractMethod();

    default void defaultMethod() {
        System.out.println("Default method in interface");
    }
}

public class Java_1_DefaultMethods implements MyInterface {
    @Override
    public void abstractMethod() {
        System.out.println("Implemented abstract method");
    }

    public static void main(String[] args) {
        Java_1_DefaultMethods obj = new Java_1_DefaultMethods();
        obj.abstractMethod();
        obj.defaultMethod();
    }
}

/*
================================================================================
DEFAULT METHODS – DEEP DIVE
================================================================================

WHAT:
-----
• Methods with body in interfaces
• Allows interface evolution without breaking implementing classes
• Bridges gap between abstract classes and interfaces

WHY IT EXISTS:
--------------
• Before Java 8 → adding method to interface breaks all implementations
• Default methods enable adding new functionality safely
• Supports backward compatibility

INTERNAL WORKING:
-----------------
• Compiles as static method in interface + synthetic bridge if overridden
• JVM resolves default method during interface dispatch at runtime
• Method table updated for implementing class

CORE FEATURES:
--------------
✔ Method with implementation in interface
✔ Can be overridden
✔ Supports multiple inheritance of behavior

ENTERPRISE PITFALLS:
-------------------
❌ Diamond problem → conflict resolution needed
❌ Overusing → misuse over abstract class

REAL SYSTEM USAGE:
-----------------
✔ API evolution (e.g., Java Collections add new methods)
✔ Interface extension without breaking existing code

INTERVIEW QUESTIONS + ANSWERS:
------------------------------
Q1: Can default methods access instance fields?
A: No, interface cannot have instance fields

Q2: Can a class override default method?
A: Yes, with normal override

INTERVIEW ONE-LINER:
-------------------
"Default methods allow interfaces to evolve without breaking existing implementations."
================================================================================
*/
