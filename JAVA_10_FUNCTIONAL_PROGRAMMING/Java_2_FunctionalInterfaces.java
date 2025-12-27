package JAVA_10_FUNCTIONAL_PROGRAMMING;

import java.util.function.*;

/**
 * Topic: Functional Interfaces
 */
public class Java_2_FunctionalInterfaces {

    // =========================
    // Custom Functional Interface
    // =========================
    @FunctionalInterface
    interface MathOperation {
        int operate(int a, int b);
    }

    public static void main(String[] args) {

        // =========================
        // Lambda implementing Functional Interface
        // =========================
        MathOperation add = (a, b) -> a + b;
        MathOperation multiply = (a, b) -> a * b;

        System.out.println("Add: " + add.operate(5, 3));
        System.out.println("Multiply: " + multiply.operate(5, 3));
    }
}

/*
================================================================================
FUNCTIONAL INTERFACES – DEEP DIVE
================================================================================

WHAT:
-----
• Interface with exactly one abstract method
• Can be implemented with lambda or method reference
• Serves as a contract for behavior

WHY IT EXISTS:
--------------
• Enables lambda usage
• Ensures compiler checks single abstract method
• Predefined functional interfaces reduce boilerplate

INTERNAL WORKING:
-----------------
• @FunctionalInterface annotation → compile-time check
• Lambda implements the abstract method dynamically
• Default/static methods allowed, do not count as abstract

CORE FEATURES:
--------------
• Single abstract method (SAM)
• Optional default methods
• Can extend other interfaces (still SAM)
• Can be used in Stream, CompletableFuture

ENTERPRISE PITFALLS:
-------------------
❌ Annotating non-SAM interface
❌ Confusing multiple inheritance of SAM interfaces
❌ Overusing lambdas for complex logic

REAL SYSTEM USAGE:
-----------------
✔ Strategy pattern via lambda
✔ Sorting & filtering logic
✔ Event listener implementations

INTERVIEW QUESTIONS + ANSWERS:
------------------------------
Q1: Difference between Functional Interface and Interface?
A: Functional Interface → exactly one abstract method; normal Interface → multiple methods allowed.

Q2: Can Functional Interface have default methods?
A: Yes, defaults do not count as abstract.

INTERVIEW ONE-LINER:
-------------------
"Functional interfaces define a single contract that a lambda expression can implement concisely."
================================================================================
*/
