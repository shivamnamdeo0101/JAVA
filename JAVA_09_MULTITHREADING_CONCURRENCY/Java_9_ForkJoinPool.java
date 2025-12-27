package JAVA_09_MULTITHREADING_CONCURRENCY;

import java.util.concurrent.RecursiveTask;
import java.util.concurrent.ForkJoinPool;

/**
 * ================================================================================
 * FORKJOINPOOL – THE "DIVIDE & CONQUER" CHEATSHEET
 * ================================================================================
 * * CONCEPT:
 * - Fork: Breaking a huge task into smaller, manageable sub-tasks (Recursion).
 * - Join: Waiting for sub-tasks to finish and combining their results.
 * - Work-Stealing: If Thread-A is done, it "steals" work from Thread-B's queue
 * to ensure no CPU core sits idle.
 * * 
 */
public class Java_9_ForkJoinPool {

    // RecursiveTask<Integer> means this task will RETURN an Integer result.
    static class LibraryDigitalizationTask extends RecursiveTask<Integer> {
        private final int booksToScan;
        private static final int THRESHOLD = 100; // Max books one person can handle

        public LibraryDigitalizationTask(int booksToScan) {
            this.booksToScan = booksToScan;
        }

        @Override
        protected Integer compute() {
            // 1. BASE CASE: If the task is small enough, do it sequentially
            if (booksToScan <= THRESHOLD) {
                System.out.println(Thread.currentThread().getName() + " scanned " + booksToScan + " books.");
                return booksToScan;
            }

            // 2. RECURSIVE STEP: Task is too big, split it in half
            int mid = booksToScan / 2;
            LibraryDigitalizationTask worker1 = new LibraryDigitalizationTask(mid);
            LibraryDigitalizationTask worker2 = new LibraryDigitalizationTask(booksToScan - mid);

            // fork() : Arranges to execute this sub-task asynchronously (asks for help)
            worker1.fork();

            // compute() : Current thread works on the second half immediately
            int result2 = worker2.compute();

            // join() : Wait for the forked helper to finish and get their result
            int result1 = worker1.join();

            return result1 + result2;
        }
    }

    public static void main(String[] args) {
        int totalBooksInLibrary = 1000;

        // Create the pool (Project Manager)
        ForkJoinPool pool = new ForkJoinPool();

        try {
            LibraryDigitalizationTask mainTask = new LibraryDigitalizationTask(totalBooksInLibrary);
            
            // invoke() is the starting point that returns the final combined result
            int finalCount = pool.invoke(mainTask);

            System.out.println("\n--- SUCCESS ---");
            System.out.println("Total books scanned and merged: " + finalCount);
        } finally {
            pool.shutdown();
        }
    }
}

/*
================================================================================
FORKJOINPOOL – DEEP DIVE (TECHNICAL NOTES)
================================================================================

                               TotalBooks = 1000
                                       |
                                       |
                    ┌──────────────────┴──────────────────┐
                    |                                     |
            Worker 1 (500)                         Worker 2 (500)
            compute()                              fork()
                    |                                     |
                    |                                     |
        ┌───────────┴───────────┐             ┌───────────┴───────────┐
        |                       |             |                       |
 Worker 3 (250)           Worker 4 (250)  Worker 5 (250)         Worker 6 (250)
 compute()                fork()           compute()              fork()
        |                       |             |                       |
        |                       |             |                       |
   ┌────┴────┐             ┌────┴────┐    ┌────┴────┐             ┌────┴────┐
   |         |             |         |    |         |             |         |
A (100)   B (150)      C (100)   D (150) E (100)   F (150)     G (100)   H (150)
compute   compute      compute    compute compute   compute     compute   compute
   |         |             |         |        |         |           |         |
   └────┬────┘             └────┬────┘        └────┬────┘           └────┬────┘
        |                         |                   |                     |
        └───────────────┐         └───────────────┐   └───────────────┐     |
                        |                         |                   |     |
                        └────────────── JOIN ─────┴─────────────── JOIN ─────┘
                                         |
                                         |
                               Final Combined Result
                                         |
                                 computedResult = 1000



WHAT:
-----
• Specialized executor for **divide-and-conquer** (recursive) tasks.
• Splits tasks until they reach a "Base Case" (Threshold).



WHY IT EXISTS:
--------------
• **Work-Stealing Algorithm**: Every worker thread has its own Deque (Double-Ended Queue). 
  Idle threads steal tasks from the BOTTOM of busy threads' queues.
• Maximizes CPU usage for heavy calculations (CPU-bound tasks).

INTERNAL WORKING:
-----------------
• **RecursiveTask<V>**: Returns a value (like our book count).
• **RecursiveAction**: Does not return a result (like sorting an array in-place).
• **fork()**: Pushes task to the thread's work queue.
• **join()**: Blocks until result is ready, but allows thread to do other work while waiting.

DEFAULT VALUES:
---------------
• **Parallelism**: Defaults to `Runtime.getRuntime().availableProcessors() - 1`.
• **Common Pool**: Accessible via `ForkJoinPool.commonPool()`, used by Parallel Streams.

ENTERPRISE PITFALLS:
-------------------
❌ **Threshold too low**: Creating millions of tasks causes more overhead than work.
❌ **Blocking I/O**: Don't use ForkJoin for DB calls or API calls; it starves the CPU.
❌ **Shared state**: Sub-tasks should be independent to avoid synchronization overhead.

REAL SYSTEM USAGE:
-----------------
✔ **Parallel Streams**: `list.parallelStream().filter(...).collect(...)` uses this internally.
✔ **Image Processing**: Splitting an image into 4 quadrants to apply a filter.
✔ **Big Data**: Summing up values in a massive array or complex tree structure.

INTERVIEW ONE-LINER:
-------------------
"ForkJoinPool solves the idle-thread problem via Work-Stealing, making it the most efficient 
way to handle recursive, CPU-intensive parallel processing in Java."
================================================================================
*/