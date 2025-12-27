package JAVA_11_ADVANDED_JAVA;

/**
 * Topic: Static Nested Classes
 */
public class Java_5_StaticNestedClasses {

    static class Nested {
        void greet() {
            System.out.println("Hello from Static Nested Class");
        }
    }

    public static void main(String[] args) {
        Nested nested = new Nested();
        nested.greet();
    }
}

/*
================================================================================
STATIC NESTED CLASSES – DEEP DIVE
================================================================================

WHAT:
-----
• Nested class declared static
• Does NOT hold reference to outer class
• Used for logical grouping

WHY IT EXISTS:
--------------
• Avoid memory leak from non-static inner class
• Group related classes
• Improves readability and maintainability

INTERNAL WORKING:
-----------------
• Compiles as Outer$Nested.class
• No outer class reference
• Can access static members of outer class

CORE FEATURES:
--------------
✔ No outer instance required
✔ Encapsulated within outer class
✔ Supports access to static members

ENTERPRISE PITFALLS:
-------------------
❌ Trying to access non-static outer members → compilation error
❌ Overusing → code verbosity

REAL SYSTEM USAGE:
-----------------
✔ Helper classes in APIs
✔ Builder patterns
✔ Encapsulated utilities

INTERVIEW QUESTIONS + ANSWERS:
------------------------------
Q1: Difference from non-static inner class?
A: Non-static → holds reference to outer, static → independent

INTERVIEW ONE-LINER:
-------------------
"Static nested classes group related classes without requiring outer instance, saving memory and improving structure."
================================================================================
*/
