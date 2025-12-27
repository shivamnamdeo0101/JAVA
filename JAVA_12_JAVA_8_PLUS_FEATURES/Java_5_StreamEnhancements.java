package JAVA_12_JAVA_8_PLUS_FEATURES;

import java.util.*;
import java.util.stream.*;

/**
 * Topic: Stream API Enhancements
 */
public class Java_5_StreamEnhancements {
    public static void main(String[] args) {

        List<String> names = List.of("Alice", "Bob", "Charlie", "David");

        // =========================
        // Filter + Map + Collect
        // =========================
        List<String> filtered = names.stream()
                .filter(s -> s.startsWith("A") || s.startsWith("B"))
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(filtered);

        // =========================
        // Sorting
        // =========================
        List<String> sorted = names.stream().sorted().collect(Collectors.toList());
        System.out.println(sorted);

        // =========================
        // Reduce
        // =========================
        String concatenated = names.stream().reduce("", (a, b) -> a + "," + b);
        System.out.println(concatenated);

        // =========================
        // Parallel Stream
        // =========================
        names.parallelStream().forEach(System.out::println);
    }
}

/*
================================================================================
STREAM API ENHANCEMENTS – DEEP DIVE
================================================================================

WHAT:
-----
• Streams = sequence of elements supporting aggregate operations
• Functional, declarative processing
• Not a data structure; does not store elements

WHY IT EXISTS:
--------------
• Avoid explicit loops
• Enable parallel processing
• Fluent operations on collections
• Reduces boilerplate & errors

INTERNAL WORKING:
-----------------
• Lazy evaluation for intermediate ops
• Terminal ops trigger evaluation
• Spliterator handles element partitioning
• ParallelStream uses ForkJoinPool.commonPool()

CORE FEATURES:
--------------
✔ Lazy, declarative
✔ Parallel processing
✔ Functional-style ops (map, filter, reduce)
✔ Non-mutating source

ENTERPRISE PITFALLS:
-------------------
❌ Forget terminal operation → no processing
❌ Side-effects in streams
❌ Over-parallelization → thread overhead

REAL SYSTEM USAGE:
-----------------
✔ Data processing pipelines
✔ Aggregation and reports
✔ Real-time filtering
✔ Parallel computations

INTERVIEW QUESTIONS + ANSWERS:
------------------------------
Q1: Difference between intermediate and terminal ops?
A: Intermediate = lazy, returns stream; Terminal = triggers evaluation

Q2: Are streams thread-safe?
A: Stream itself is not shared; source must be thread-safe

INTERVIEW ONE-LINER:
-------------------
"Streams enable fluent, functional, and optionally parallel data processing over collections."
================================================================================
*/
