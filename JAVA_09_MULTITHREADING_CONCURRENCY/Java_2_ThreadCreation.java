package JAVA_09_MULTITHREADING_CONCURRENCY;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * THREAD CREATION – DIFFERENT APPROACHES AND ENTERPRISE USAGE
 * Demonstrates:
 * 1️⃣ Extending Thread
 * 2️⃣ Implementing Runnable
 * 3️⃣ Using Callable with ExecutorService
 * 
 * Shows benefits, trade-offs, and real-world use cases.
 */
public class Java_2_ThreadCreation {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        // =========================
        // 1️⃣ EXTENDING THREAD
        // =========================
        Thread t1 = new ThreadDemo();
        t1.start();  // Starts a new OS-level thread, JVM schedules it
        t1.join();   // Main thread waits until t1 completes

        // =========================
        // 2️⃣ IMPLEMENTING RUNNABLE
        // =========================
        Thread t2 = new Thread(new RunnableDemo());
        t2.start();  // Thread executes run()
        t2.join();   // Wait for completion

        // =========================
        // 3️⃣ USING CALLABLE + EXECUTORS
        // =========================
        ExecutorService executor = Executors.newFixedThreadPool(2); // Thread pool manages threads efficiently
        Future<Integer> future = executor.submit(new CallableDemo());
        System.out.println("Callable result: " + future.get()); // Blocks until result available
        executor.shutdown(); // Gracefully shuts down executor
    }

    // =========================
    // THREAD EXTENSION
    // =========================
    static class ThreadDemo extends Thread {
        @Override
        public void run() {
            System.out.println("[Thread] Running: Single-use, simple thread.");
        }
    }

    // =========================
    // RUNNABLE IMPLEMENTATION
    // =========================
    static class RunnableDemo implements Runnable {
        @Override
        public void run() {
            System.out.println("[Runnable] Running: Decoupled logic, reusable task.");
        }
    }

    // =========================
    // CALLABLE + FUTURE
    // =========================
    static class CallableDemo implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("[Callable] Running: Supports return value & exceptions.");
            return 100; // Example computation result
        }
    }
}

/*
================================================================================
THREAD CREATION – DEEP DIVE & ENTERPRISE USAGE
================================================================================

WHAT:
-----
• Three main ways to create threads in Java:
  1️⃣ Extend Thread class
  2️⃣ Implement Runnable interface
  3️⃣ Implement Callable with ExecutorService

• Each approach solves a specific problem:
  - Simple single-thread tasks
  - Reusable decoupled task logic
  - Asynchronous computation with return values and exception handling

WHY IT EXISTS:
--------------
• Extending Thread: Quick way to run code asynchronously, but limited by single inheritance.
• Runnable: Decouples task from thread control; allows reusability.
• Callable + ExecutorService: Supports return values, checked exceptions, thread pooling, and scalable concurrency management.

INTERNAL WORKING:
-----------------
• Thread:
  - Wraps OS-level thread
  - run() contains the task
  - start() schedules it with JVM thread scheduler
  - join() blocks calling thread until completion  blocking main thread / calling thread untill the thread is completed
  - Ex: 10 people working 10 seprate work rooms

• Runnable:
  - Task object passed to Thread constructor - new RunnableDemo()
  - run() invoked by thread - Override Runnable implementation
  - Allows multiple threads to share same Runnable object
  - Ex - 10 people working the same workstation - same Runnable object

• Callable + ExecutorService:
  - Callable wrapped in FutureTask internally
  - Thread pool (ExecutorService) manages threads
  - submit() schedules task, returns Future
  - Future.get() blocks until completion
  - Thread pool reduces thread creation overhead
  - Ex:   Fixed number of workers - 10 people working the same workstation

DEFAULT VALUES:
---------------
• Thread: default priority = 5, no default name
• Runnable: needs Thread wrapper - new Thread(new Runnable()) - new Thread(new RunnableDemo())
• Callable: returns Future, can throw exceptions - new Callable<Integer>() {
    @Override
        public Integer call() throws Exception {
            System.out.println("[Callable] Running: Supports return value & exceptions.");
            return 100; // Example computation result
        }
    }
• ExecutorService: configurable pool size, core/max threads

TIME COMPLEXITY / PERFORMANCE:
-----------------------------
• Thread start(): O(1), but OS context switch is expensive
• Runnable: same as Thread
• Callable + ExecutorService: thread reuse reduces overhead, scales better

CORE FEATURES:
--------------
• Runnable/Callable separates task logic from execution
• Supports concurrency
• ExecutorService handles pooling, scheduling, and lifecycle

ENTERPRISE PITFALLS:
-------------------
❌ Creating too many threads → OOM ? OOM Full Form is Out Of Memory Error
❌ Blocking main thread unnecessarily - join() blocks calling thread until completion - blocking main thread / calling thread untill the thread is completed
❌ Not shutting down executor → thread leaks - executor.shutdown(); // Gracefully shuts down executor 
❌ Mixing raw threads and executors inconsistently 

REAL SYSTEM USAGE:
-----------------
✔ Async API requests
✔ Background jobs & monitoring
✔ Batch processing
✔ CPU-bound vs IO-bound task segregation
✔ Microservices with scalable async handling

INTERVIEW QUESTIONS + ANSWERS:
------------------------------
Q1: Difference Runnable vs Callable?
A: Runnable returns void, no exceptions; Callable returns value + checked exceptions.

Q2: Why use ExecutorService instead of raw Thread?
A: Reuses threads, manages lifecycle, prevents thread explosion.

Q3: Can you extend multiple threads?
A: No, single inheritance restricts it; use Runnable for multiple inheritance scenarios.

Q4: When to prefer Callable?
A: When you need result, exception handling, or plan to use thread pools.

INTERVIEW ONE-LINER:
-------------------
"Use Runnable for reusable tasks, Thread for quick single-use threads, and Callable+ExecutorService for scalable, result-returning async operations."
================================================================================
*/
