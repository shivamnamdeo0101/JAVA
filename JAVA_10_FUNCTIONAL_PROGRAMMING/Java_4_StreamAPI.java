package JAVA_10_FUNCTIONAL_PROGRAMMING;

import java.util.*;
import java.util.stream.*;

/**
 * Topic: Stream API
 */
public class Java_4_StreamAPI {

    public static void main(String[] args) {

        List<String> names = Arrays.asList("Shivam", "Amit", "Ravi", "Amit");

        // =========================
        // Create Stream
        // =========================
        Stream<String> nameStream = names.stream();
        Stream<String> parallelStream = names.parallelStream();

        // =========================
        // Intermediate operations
        // =========================
        List<String> filtered = names.stream()
            .filter(s -> s.startsWith("A"))
            .map(String::toUpperCase)
            .distinct()
            .collect(Collectors.toList());

        System.out.println("Filtered: " + filtered);

        // =========================
        // Terminal operations
        // =========================
        long count = names.stream().filter(s -> s.startsWith("A")).count();
        System.out.println("Count starting with A: " + count);

        // =========================
        // Reduce
        // =========================
        String concatenated = names.stream().reduce("", (a, b) -> a + "-" + b);
        System.out.println("Concatenated: " + concatenated);
    }
}

/*
================================================================================
STREAM API – DEEP DIVE
================================================================================

WHAT:
-----
• Stream is a sequence of elements supporting functional-style operations
• Does NOT store data; abstraction over data source
• Enables pipeline processing

WHY IT EXISTS:
--------------
• Reduce imperative for-loops
• Enables parallel processing
• Lazy evaluation

INTERNAL WORKING:
-----------------
• Source → Stream pipeline → Terminal operation triggers execution
• Intermediate operations are lazy: filter, map, sorted
• Terminal operations: collect, reduce, forEach
• Stream ops create internal Spliterator
• Parallel streams split tasks, fork/join used internally

DEFAULT BEHAVIOR:
-----------------
• Streams are single-use
• Can be sequential or parallel

TIME COMPLEXITY:
----------------
• Depends on source + ops
• filter/map: O(n)
• reduce/collect: O(n)

CORE FEATURES:
--------------
• Lazy evaluation
• Functional style
• Composable
• Parallelizable

ENTERPRISE PITFALLS:
-------------------
❌ Reusing same stream
❌ Performing side-effects in intermediate ops
❌ Excessive boxing/unboxing

REAL SYSTEM USAGE:
-----------------
✔ Filtering API results
✔ Transforming collections
✔ Parallel batch processing
✔ Aggregation & reporting

INTERVIEW QUESTIONS + ANSWERS:
------------------------------
Q1: Difference sequential vs parallel stream?
A: Parallel uses ForkJoinPool internally; sequential processes in single thread.

Q2: Can stream be reused?
A: No, once terminal operation is called, stream is consumed.

INTERVIEW ONE-LINER:
-------------------
"Streams allow functional, parallel-ready pipelines over data sources without storing them."
================================================================================
*/
