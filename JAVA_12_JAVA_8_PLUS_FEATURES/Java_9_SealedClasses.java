package JAVA_12_JAVA_8_PLUS_FEATURES;

/**
 * Topic: Sealed Classes
 */
public abstract sealed class Java_9_SealedClasses permits Circle, Rectangle {
    abstract double area();
}

final class Circle extends Java_9_SealedClasses {
    private final double radius;
    Circle(double r) { this.radius = r; }
    double area() { return Math.PI * radius * radius; }
}

final class Rectangle extends Java_9_SealedClasses {
    private final double width, height;
    Rectangle(double w, double h) { this.width = w; this.height = h; }
    double area() { return width * height; }
}

class TestSealed {
    public static void main(String[] args) {
        Java_9_SealedClasses c = new Circle(5);
        Java_9_SealedClasses r = new Rectangle(4, 6);
        System.out.println("Circle Area: " + c.area());
        System.out.println("Rectangle Area: " + r.area());
    }
}

/*
================================================================================
SEALED CLASSES – DEEP DIVE
================================================================================

WHAT:
-----
• Restrict which classes can extend a superclass
• Explicit inheritance boundaries
• Compile-time safety

WHY IT EXISTS:
--------------
• Prevent uncontrolled inheritance
• Improve code maintainability & security
• Enhances pattern matching

INTERNAL WORKING:
-----------------
• Compiler enforces permitted subclasses
• JVM marks sealed superclass & permits list
• Final, non-sealed modifiers control further inheritance

CORE FEATURES:
--------------
✔ Restricted inheritance
✔ Explicit permits clause
✔ Supports pattern matching

ENTERPRISE PITFALLS:
-------------------
❌ Over-permitting
❌ Using sealed for trivial classes
❌ Not understanding non-sealed overrides

REAL SYSTEM USAGE:
-----------------
✔ Domain modeling
✔ Security-critical hierarchy
✔ Pattern matching optimizations

INTERVIEW QUESTIONS + ANSWERS:
------------------------------
Q1: Difference between sealed and abstract?
A: Sealed = controls subclassing; Abstract = can be extended freely

INTERVIEW ONE-LINER:
-------------------
"Sealed classes enforce controlled inheritance, providing explicit boundaries and compile-time safety."
================================================================================
*/
