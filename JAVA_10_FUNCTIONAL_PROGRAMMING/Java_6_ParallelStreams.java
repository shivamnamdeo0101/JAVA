package JAVA_10_FUNCTIONAL_PROGRAMMING;

import java.util.*;
import java.util.stream.*;

/**
 * Topic: Parallel Streams
 */
public class Java_6_ParallelStreams {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

        // Sequential sum
        int seqSum = numbers.stream().reduce(0, Integer::sum);

        // Parallel sum
        int parSum = numbers.parallelStream().reduce(0, Integer::sum);

        System.out.println("Sequential sum: " + seqSum);
        System.out.println("Parallel sum: " + parSum);

        // Parallel processing with forEachOrdered
        numbers.parallelStream()
                .map(n -> n * 2)
                .forEachOrdered(System.out::println);
    }
}

/*
================================================================================
PARALLEL STREAMS – DEEP DIVE
================================================================================

WHAT:
-----
• Streams that split tasks for parallel execution
• Uses ForkJoinPool internally
• Allows multi-core utilization for large datasets

WHY IT EXISTS:
--------------
• Improve performance for CPU-bound tasks
• Avoid manual threading
• Simplify parallel data processing

INTERNAL WORKING:
-----------------
• Source splits into segments using Spliterator
• Each segment processed by worker threads (ForkJoinPool)
• Intermediate ops executed per segment
• Terminal op merges results
• forEachOrdered maintains encounter order

CORE FEATURES:
--------------
• Multi-core parallelism
• Lazy intermediate operations
• ForkJoinPool-managed threads

ENTERPRISE PITFALLS:
-------------------
❌ Using parallel streams for small collections
❌ Stateful operations
❌ Side-effects causing race conditions

REAL SYSTEM USAGE:
-----------------
✔ Parallel aggregation
✔ Large dataset transformations
✔ Batch reporting
✔ ETL pipelines

INTERVIEW QUESTIONS + ANSWERS:
------------------------------
Q1: Difference parallel vs sequential stream?
A: Parallel → multi-threaded, may process out-of-order; Sequential → single-threaded.

Q2: Can all operations safely be parallelized?
A: Only stateless, associative, non-interfering operations.

INTERVIEW ONE-LINER:
-------------------
"Parallel streams leverage ForkJoinPool for parallel, functional processing without explicit thread management."
================================================================================
*/
