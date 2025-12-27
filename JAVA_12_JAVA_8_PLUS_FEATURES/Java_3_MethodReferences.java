package JAVA_12_JAVA_8_PLUS_FEATURES;

import java.util.*;
import java.util.function.Function;

/**
 * Topic: Method References
 */
public class Java_3_MethodReferences {
    public static void main(String[] args) {
        List<String> names = List.of("Alice", "Bob", "Charlie");

        // =========================
        // Using lambda
        // =========================
        names.forEach(name -> System.out.println(name));

        // =========================
        // Using method reference
        // =========================
        names.forEach(System.out::println);

        // Reference to static method
        Function<String, Integer> lengthFunc = String::length;
        System.out.println(lengthFunc.apply("Hello"));
    }
}

/*
================================================================================
METHOD REFERENCES – DEEP DIVE
================================================================================

WHAT:
-----
• Concise way to refer to methods using ::
• Replaces lambda expressions when method already exists

WHY IT EXISTS:
--------------
• Improves readability
• Reduces boilerplate
• Supports functional programming style

INTERNAL WORKING:
-----------------
• Compiles to invokedynamic call
• JVM resolves method reference at runtime
• Captures functional interface target type

CORE FEATURES:
--------------
✔ Can refer to:
   • Static methods
   • Instance methods
   • Constructor references
✔ Used in Stream API and functional interfaces

ENTERPRISE PITFALLS:
-------------------
❌ Confusing syntax for beginners
❌ Cannot pass extra arguments

REAL SYSTEM USAGE:
-----------------
✔ Stream.map(String::length)
✔ Event handlers
✔ Factory method references

INTERVIEW QUESTIONS + ANSWERS:
------------------------------
Q1: Difference between lambda and method reference?
A: Lambda → custom code, Method ref → existing method call

INTERVIEW ONE-LINER:
-------------------
"Method references simplify lambdas by pointing directly to existing methods or constructors."
================================================================================
*/
