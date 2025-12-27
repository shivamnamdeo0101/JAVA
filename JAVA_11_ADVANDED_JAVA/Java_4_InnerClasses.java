package JAVA_11_ADVANDED_JAVA;

/**
 * Topic: Inner Classes
 */
public class Java_4_InnerClasses {

    class Inner {
        void greet() {
            System.out.println("Hello from Inner Class");
        }
    }

    public static void main(String[] args) {
        Java_4_InnerClasses outer = new Java_4_InnerClasses();
        Inner inner = outer.new Inner();
        inner.greet();
    }
}

/*
================================================================================
INNER CLASSES – DEEP DIVE
================================================================================

WHAT:
-----
• Class defined within another class
• Can access outer class members including private
• Represents strong coupling and encapsulation

WHY IT EXISTS:
--------------
• Encapsulate helper classes
• Event listeners in UI
• Reduce top-level class pollution
• Improve readability and maintainability

INTERNAL WORKING:
-----------------
• Compiles into Outer$Inner.class
• Stores reference to outer class instance
• Synthetic constructor with Outer instance parameter
• Outer.this used to access outer members

CORE FEATURES:
--------------
✔ Access to outer class private members
✔ Encapsulated behavior
✔ Supports multiple types (static, non-static, anonymous)

ENTERPRISE PITFALLS:
-------------------
❌ Memory leaks due to inner class holding outer reference
❌ Overuse → less readable
❌ Non-static inner classes in long-lived instances

REAL SYSTEM USAGE:
-----------------
✔ GUI event listeners
✔ Callbacks
✔ Encapsulated helper classes

INTERVIEW QUESTIONS + ANSWERS:
------------------------------
Q1: Difference between static and non-static inner class?
A: Non-static → instance tied, static → no outer reference

Q2: Can inner class be private?
A: Yes, limits visibility outside outer class

INTERVIEW ONE-LINER:
-------------------
"Inner classes encapsulate helper logic while allowing direct access to outer class members."
================================================================================
*/
