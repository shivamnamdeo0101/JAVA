package JAVA_9_MULTITHREADING_CONCURRENCY;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Java_10_CompletableFuture {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        // =========================
        // Async computation
        // =========================
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> "Hello")
                .thenApply(s -> s + " World")
                .thenApply(String::toUpperCase);

        System.out.println(future.get());

        // =========================
        // Combining futures
        // =========================
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> "A");
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> "B");

        CompletableFuture<String> combined = f1.thenCombine(f2, (s1, s2) -> s1 + s2);
        System.out.println("Combined: " + combined.get());
    }
}

/*
================================================================================
COMPLETABLEFUTURE – DEEP DIVE
================================================================================

WHAT:
-----
• High-level API for **asynchronous, non-blocking tasks**
• Can compose, combine, and chain tasks

WHY IT EXISTS:
--------------
• Traditional Future → blocking, no chaining
• CompletableFuture → async composition, error handling, chaining

INTERNAL WORKING:
-----------------
• Uses ForkJoinPool.commonPool() by default
• Completion stage → linked chain of dependent actions
• Each stage triggers next when complete
• Supports async callbacks and exception handling

DEFAULT VALUES:
---------------
• Common pool = #cores parallelism
• Thread pool can be overridden
• Non-blocking chaining

TIME COMPLEXITY:
----------------
• O(1) submission, O(n) chaining overhead
• Execution depends on underlying pool

CORE FEATURES:
--------------
• Async execution
• Chaining & composition
• Combining multiple futures
• Exception handling

ENTERPRISE PITFALLS:
-------------------
❌ Blocking get() negates async benefits
❌ Unhandled exceptions propagate silently
❌ CommonPool exhaustion

REAL SYSTEM USAGE:
-----------------
✔ Microservice async calls
✔ Event-driven pipelines
✔ Reactive computations
✔ Parallel data processing

INTERVIEW QUESTIONS + ANSWERS:
------------------------------
Q1: Difference Future vs CompletableFuture?
A: CompletableFuture → async chaining + combining, Future → single result blocking

Q2: thenApply vs thenAccept?
A: thenApply returns transformed value, thenAccept returns void

INTERVIEW ONE-LINER:
-------------------
"CompletableFuture enables fully asynchronous, composable, and non-blocking task pipelines."
================================================================================
*/
