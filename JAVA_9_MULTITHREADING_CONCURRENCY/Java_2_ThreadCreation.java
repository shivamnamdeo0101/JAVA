package JAVA_9_MULTITHREADING_CONCURRENCY;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Java_2_ThreadCreation {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        // 1️⃣ Extending Thread
        Thread t1 = new ThreadDemo();
        t1.start();
        t1.join();

        // 2️⃣ Implementing Runnable
        Thread t2 = new Thread(new RunnableDemo());
        t2.start();
        t2.join();

        // 3️⃣ Using Callable + Future
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<Integer> future = executor.submit(new CallableDemo());
        System.out.println("Callable result: " + future.get());
        executor.shutdown();
    }

    static class ThreadDemo extends Thread {
        @Override
        public void run() {
            System.out.println("Thread subclass running");
        }
    }

    static class RunnableDemo implements Runnable {
        @Override
        public void run() {
            System.out.println("Runnable running");
        }
    }

    static class CallableDemo implements Callable<Integer> {
        @Override
        public Integer call() {
            System.out.println("Callable running");
            return 42;
        }
    }
}

/*
================================================================================
THREAD CREATION – DEEP DIVE
================================================================================

WHAT:
-----
Three main ways to create threads in Java:
1️⃣ Extend Thread class
2️⃣ Implement Runnable interface
3️⃣ Implement Callable with ExecutorService (returns result + exception handling)

WHY IT EXISTS:
--------------
• Thread class extension is simple but single inheritance limited.
• Runnable decouples task logic from thread control.
• Callable supports return values and checked exceptions.
• Executors improve scalability, thread reuse, and management.

INTERNAL WORKING:
-----------------
• Thread object wraps OS-level thread.
• Runnable/Callable task passed to Thread/Executor.
• ExecutorService maintains thread pool (corePoolSize, maxPoolSize, queue).
• Future.get() blocks until computation completes.
• Callable internally uses FutureTask wrapping.

DEFAULT VALUES:
---------------
• Thread: no default name, priority=5
• Runnable: needs Thread wrapper
• Callable: returns Future
• ExecutorService: configurable pool size

TIME COMPLEXITY / PERFORMANCE:
-----------------------------
• Thread start(): O(1), but OS-dependent context switch cost.
• Runnable: same as Thread
• Callable + Executor: thread reuse reduces thread creation overhead

CORE FEATURES:
--------------
• Supports concurrency
• Runnable/Callable separates task from execution
• Executors manage thread lifecycle

ENTERPRISE PITFALLS:
-------------------
❌ Creating too many threads → OutOfMemoryError
❌ Blocking main thread waiting for join() or get()
❌ Mixing Thread vs Executor inconsistently

REAL SYSTEM USAGE:
-----------------
✔ Async processing
✔ Background tasks
✔ Microservice request handling
✔ CPU-bound vs IO-bound task segregation

INTERVIEW QUESTIONS + ANSWERS:
------------------------------
Q1: Difference between Runnable and Callable?
A: Callable returns result + allows checked exceptions; Runnable does not.

Q2: Can we extend multiple threads?
A: No, Java single inheritance restricts Thread subclassing.

Q3: Why use ExecutorService instead of direct Thread?
A: Reuses threads, manages lifecycle, prevents excessive creation.

INTERVIEW ONE-LINER:
-------------------
"Use Callable + ExecutorService for scalable, result-returning, and manageable multithreaded tasks."
================================================================================
*/
