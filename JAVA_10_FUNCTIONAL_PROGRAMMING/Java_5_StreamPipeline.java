package JAVA_10_FUNCTIONAL_PROGRAMMING;

import java.util.*;
import java.util.stream.*;

/**
 * Topic: Stream Pipeline, Intermediate & Terminal Operations
 */
public class Java_5_StreamPipeline {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        // =========================
        // Intermediate Operations
        // =========================
        List<Integer> processed = numbers.stream()
            .filter(n -> n % 2 == 0) // filter
            .map(n -> n * n)         // map
            .sorted(Comparator.reverseOrder()) // sorted
            .collect(Collectors.toList());

        System.out.println("Processed: " + processed);

        // =========================
        // Terminal Operations
        // =========================
        int sum = numbers.stream().reduce(0, Integer::sum);
        int max = numbers.stream().max(Integer::compareTo).orElse(0);

        System.out.println("Sum: " + sum + ", Max: " + max);
    }
}

/*
================================================================================
STREAM PIPELINE – DEEP DIVE
================================================================================

WHAT:
-----
• Stream pipeline = Source + zero/more intermediate ops + terminal op
• Provides functional, composable processing

WHY IT EXISTS:
--------------
• Clean alternative to nested loops
• Enables chaining transformations
• Lazy evaluation until terminal operation

INTERNAL WORKING:
-----------------
• Intermediate ops return new pipeline, no computation
• Terminal triggers traversal
• filter/map create internal spliterator chain
• reduce/collect trigger iterative consumption
• Sorting may copy or buffer elements

CORE FEATURES:
--------------
• Intermediate: filter, map, sorted, distinct, limit, skip
• Terminal: collect, reduce, count, forEach
• Lazy evaluation
• Non-mutating until terminal

ENTERPRISE PITFALLS:
-------------------
❌ Performing side-effects inside intermediate operations
❌ Relying on order in parallel streams
❌ Excessive intermediate operations for large collections

REAL SYSTEM USAGE:
-----------------
✔ Transforming API payloads
✔ Aggregating DB query results
✔ Reporting pipelines
✔ Parallelizable ETL tasks

INTERVIEW QUESTIONS + ANSWERS:
------------------------------
Q1: Difference intermediate vs terminal operation?
A: Intermediate → lazy, returns stream; Terminal → triggers computation, returns result.

Q2: Can intermediate ops mutate source?
A: No, streams are non-mutating by design.

INTERVIEW ONE-LINER:
-------------------
"A Stream pipeline allows lazy, functional transformations, deferring execution until a terminal operation."
================================================================================
*/
