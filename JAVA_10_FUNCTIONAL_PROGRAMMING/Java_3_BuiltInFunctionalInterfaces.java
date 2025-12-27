package JAVA_10_FUNCTIONAL_PROGRAMMING;

import java.util.function.*;
import java.util.*;

/**
 * Topic: Built-in Functional Interfaces
 */
public class Java_3_BuiltInFunctionalInterfaces {

    public static void main(String[] args) {

        // =========================
        // Predicate<T> – returns boolean
        // =========================
        Predicate<String> isEmpty = s -> s.isEmpty();
        System.out.println("Is empty? " + isEmpty.test(""));

        // =========================
        // Consumer<T> – consumes data, returns void
        // =========================
        Consumer<String> printer = s -> System.out.println("Printing: " + s);
        printer.accept("Hello");

        // =========================
        // Function<T,R> – transforms T -> R
        // =========================
        Function<String, Integer> lengthFunc = s -> s.length();
        System.out.println("Length: " + lengthFunc.apply("Shivam"));

        // =========================
        // Supplier<T> – provides data
        // =========================
        Supplier<String> supplier = () -> "Generated String";
        System.out.println(supplier.get());

        // =========================
        // UnaryOperator<T> / BinaryOperator<T>
        // =========================
        UnaryOperator<Integer> square = x -> x * x;
        BinaryOperator<Integer> sum = (a, b) -> a + b;
        System.out.println("Square: " + square.apply(5));
        System.out.println("Sum: " + sum.apply(4, 6));
    }
}

/*
================================================================================
BUILT-IN FUNCTIONAL INTERFACES – DEEP DIVE
================================================================================

WHAT:
-----
• Predefined interfaces for common patterns: Predicate, Function, Supplier, Consumer, Operators
• Avoids custom SAM interface creation
• Represents behavior contracts

WHY IT EXISTS:
--------------
• Reduce boilerplate
• Standardize functional programming
• Simplifies lambda and Stream integration

INTERNAL WORKING:
-----------------
• All are SAM interfaces
• Lambda generates a synthetic method implementing abstract method
• Can be composed: andThen(), compose(), and(), or()

DEFAULT BEHAVIOR:
-----------------
• Stateless unless capturing external variable
• Thread-safety depends on captured state

CORE FEATURES:
--------------
• Predicate<T>: boolean tests
• Consumer<T>: void consuming
• Function<T,R>: T → R mapping
• Supplier<T>: no input, provides T
• Unary/BinaryOperator<T>: specialized function for T

ENTERPRISE PITFALLS:
-------------------
❌ Overusing lambdas for heavy computation
❌ Capturing mutable state incorrectly
❌ Misunderstanding andThen vs compose

REAL SYSTEM USAGE:
-----------------
✔ API input validation
✔ Data transformation pipelines
✔ Lazy computation (Supplier)
✔ Batch processing (Consumer)

INTERVIEW QUESTIONS + ANSWERS:
------------------------------
Q1: Difference Predicate vs Function?
A: Predicate → boolean, Function → transforms T → R

Q2: Can Supplier throw exception?
A: Only unchecked; checked must be handled externally.

Q3: UnaryOperator vs Function?
A: UnaryOperator is Function<T,T> specialized for same type input/output

INTERVIEW ONE-LINER:
-------------------
"Built-in functional interfaces provide reusable contracts for lambda expressions and Stream pipelines."
================================================================================
*/
