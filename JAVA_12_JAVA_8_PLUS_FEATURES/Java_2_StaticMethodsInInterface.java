package JAVA_12_JAVA_8_PLUS_FEATURES;

/**
 * Topic: Static Methods in Interface
 */
interface StaticInterface {
    static void staticMethod() {
        System.out.println("Static method in interface");
    }
}

public class Java_2_StaticMethodsInInterface {
    public static void main(String[] args) {
        StaticInterface.staticMethod();
    }
}

/*
================================================================================
STATIC METHODS IN INTERFACE – DEEP DIVE
================================================================================

WHAT:
-----
• Methods with body, callable on interface itself
• Cannot be overridden by implementing classes
• Provide utility methods tied to interface

WHY IT EXISTS:
--------------
• Common utility functions related to interface
• Similar to static methods in abstract classes
• No dependency on implementing class

INTERNAL WORKING:
-----------------
• Compiled into interface bytecode as static method
• JVM calls via INVOKESTATIC
• No reference to instance needed

CORE FEATURES:
--------------
✔ Static, utility-like
✔ No inheritance
✔ Type-safe

ENTERPRISE PITFALLS:
-------------------
❌ Cannot override static method
❌ Misusing for instance logic

REAL SYSTEM USAGE:
-----------------
✔ Collections interface utility methods (e.g., List.of)
✔ Factory methods in interfaces

INTERVIEW QUESTIONS + ANSWERS:
------------------------------
Q1: Can implementing class override static method?
A: No, static methods belong to interface only

INTERVIEW ONE-LINER:
-------------------
"Static methods in interfaces provide interface-level utility methods safely and type-safely."
================================================================================
*/
