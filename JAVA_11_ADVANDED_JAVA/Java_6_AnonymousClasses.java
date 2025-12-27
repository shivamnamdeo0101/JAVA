package JAVA_11_ADVANDED_JAVA;

/**
 * Topic: Anonymous Classes
 */
public class Java_6_AnonymousClasses {

    interface Greet {
        void sayHello();
    }

    public static void main(String[] args) {
        Greet greet = new Greet() {
            @Override
            public void sayHello() {
                System.out.println("Hello from Anonymous Class");
            }
        };

        greet.sayHello();
    }
}

/*
================================================================================
ANONYMOUS CLASSES – DEEP DIVE
================================================================================

WHAT:
-----
• Local class without a name
• Defined and instantiated in a single expression
• Implements interface or extends a class

WHY IT EXISTS:
--------------
• Short-lived, one-off implementations
• Reduce boilerplate
• Common in GUI and functional callbacks

INTERNAL WORKING:
-----------------
• Compiles as Outer$1.class, Outer$2.class, etc.
• Holds synthetic reference to enclosing scope if used
• Can access final or effectively final variables

CORE FEATURES:
--------------
✔ Implements interface or extends class
✔ One-time use
✔ Encapsulates behavior inline

ENTERPRISE PITFALLS:
-------------------
❌ Overusing → readability suffers
❌ Capturing mutable outer variables → errors

REAL SYSTEM USAGE:
-----------------
✔ GUI callbacks
✔ Event listeners
✔ Runnable instances

INTERVIEW QUESTIONS + ANSWERS:
------------------------------
Q1: Difference from lambda?
A: Anonymous class → full class, lambda → functional interface implementation

INTERVIEW ONE-LINER:
-------------------
"Anonymous classes provide inline, one-off implementations, widely used for callbacks and event handling."
================================================================================
*/
