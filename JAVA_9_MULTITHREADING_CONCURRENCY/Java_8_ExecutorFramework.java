package JAVA_9_MULTITHREADING_CONCURRENCY;

import java.util.concurrent.*;

public class Java_8_ExecutorFramework {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        // =========================
        // Fixed Thread Pool
        // =========================
        ExecutorService executor = Executors.newFixedThreadPool(3);

        Runnable task1 = () -> System.out.println("Task 1 executed by " + Thread.currentThread().getName());
        Callable<String> task2 = () -> {
            Thread.sleep(500);
            return "Task 2 result by " + Thread.currentThread().getName();
        };

        // Submit tasks
        executor.submit(task1);
        Future<String> future = executor.submit(task2);

        // Get result
        System.out.println(future.get());

        executor.shutdown();
    }
}

/*
================================================================================
EXECUTOR FRAMEWORK – DEEP DIVE
================================================================================

WHAT:
-----
• High-level API to manage threads
• Decouples task submission from execution
• Supports Runnable, Callable, Future

WHY IT EXISTS:
--------------
• Before: manual Thread creation → hard to manage
• Executors provide thread pools → reuse threads
• Controls concurrency & resource utilization
• Handles task queueing, scheduling

INTERNAL WORKING:
-----------------
• ThreadPoolExecutor:
  - corePoolSize, maximumPoolSize
  - workQueue: task queue (LinkedBlockingQueue)
  - threads created lazily
  - rejects excess tasks based on policy
• FutureTask wraps Callable/Runnable
• Worker threads pick tasks from queue

DEFAULT VALUES:
---------------
• FixedThreadPool → corePoolSize = nThreads
• CachedThreadPool → corePoolSize = 0, max = Integer.MAX
• SynchronousQueue / LinkedBlockingQueue used
• Threads non-daemon by default

TIME COMPLEXITY:
----------------
• Task submission O(1)
• Execution O(1) amortized
• Scaling dependent on pool size

CORE FEATURES:
--------------
• Thread pooling
• Task queueing
• Scheduled execution
• Futures for result retrieval

ENTERPRISE PITFALLS:
-------------------
❌ Not shutting down → thread leak
❌ Blocking tasks → pool exhaustion
❌ Misconfigured pool → starvation or idle threads

REAL SYSTEM USAGE:
-----------------
✔ Web request handling
✔ Async service calls
✔ Batch jobs
✔ Background task processing

INTERVIEW QUESTIONS + ANSWERS:
------------------------------
Q1: Difference between submit() and execute()?
A: submit → returns Future; execute → void

Q2: Fixed vs Cached ThreadPool?
A: Fixed → predictable threads; Cached → flexible

Q3: Why not create Thread per task?
A: High overhead, poor resource utilization

INTERVIEW ONE-LINER:
-------------------
"ExecutorFramework abstracts thread management, enabling efficient, reusable, and controlled concurrency."
================================================================================
*/
