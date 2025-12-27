package JAVA_10_FUNCTIONAL_PROGRAMMING;

import java.util.*;
import java.util.function.*;

/**
 * Topic: Lambda Expressions
 */
public class Java_1_LambdaExpressions {

    public static void main(String[] args) {

        // =========================
        // Basic Runnable with Lambda
        // =========================
        Runnable r = () -> System.out.println("Hello from Lambda Thread");
        new Thread(r).start();

        // =========================
        // Comparator using Lambda
        // =========================
        List<String> names = Arrays.asList("Shivam", "Amit", "Ravi");
        names.sort((a, b) -> a.compareTo(b));
        System.out.println(names);

        // =========================
        // Predicate using Lambda
        // =========================
        Predicate<String> startsWithA = s -> s.startsWith("A");
        System.out.println("Starts with A? " + startsWithA.test("Amit"));

        // =========================
        // Consumer using Lambda
        // =========================
        Consumer<String> printer = s -> System.out.println("Printing: " + s);
        names.forEach(printer);
    }
}

/*
================================================================================
LAMBDA EXPRESSIONS – DEEP DIVE
================================================================================

WHAT:
-----
• Anonymous function, no name, can be treated as first-class object
• Solves verbosity of anonymous inner classes
• Represents behavior or action (Functional Interface)

WHY IT EXISTS:
--------------
• Pre-Java 8: verbose anonymous inner classes
• Simplifies APIs like Runnable, Comparator, Collection methods
• Encourages functional programming style in Java

INTERNAL WORKING:
-----------------
• Compiled as invokedynamic bytecode
• Uses synthetic methods in generated class
• Captures effectively final variables
• No new class files for each lambda
• Memory-efficient and faster than inner classes

DEFAULT BEHAVIOR:
-----------------
• Thread safety depends on captured variables
• Lambda instances are immutable

TIME COMPLEXITY:
----------------
• Same as underlying implementation (Runnable, Comparator)
• No additional overhead

CORE FEATURES:
--------------
• Concise syntax
• Type inference
• Can capture final/effectively final variables
• Can be passed as arguments

WHY EACH METHOD EXISTS:
----------------------
• Runnable → thread tasks
• Comparator → sorting inline
• Predicate → filtering collections
• Consumer → processing elements inline

ENTERPRISE PITFALLS:
-------------------
❌ Capturing non-final local variables
❌ Using lambdas in hot loops with large objects (boxing/unboxing)
❌ Confusing lambda vs method reference

REAL SYSTEM USAGE:
-----------------
✔ Sorting REST API responses
✔ Filtering collections
✔ Event handlers in UI
✔ Async task submission

INTERVIEW QUESTIONS + ANSWERS:
------------------------------
Q1: Difference lambda vs anonymous inner class?
A: Lambda is concise, no new class file, captures effectively final variables.

Q2: Can lambda throw checked exception?
A: Only if functional interface allows (e.g., Callable throws Exception).

INTERVIEW ONE-LINER:
-------------------
"Lambda expressions represent a behavior as an object, reducing verbosity and enabling functional style in Java."
================================================================================
*/
