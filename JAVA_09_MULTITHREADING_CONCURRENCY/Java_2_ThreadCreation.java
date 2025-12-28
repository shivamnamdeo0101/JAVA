package JAVA_09_MULTITHREADING_CONCURRENCY;

import java.util.concurrent.*;

/**
 * THE ULTIMATE THREAD CREATION & ASYNC PROCESSING CHEATSHEET
 * Logic: NEW -> RUNNABLE -> (START) -> RUNNING -> (JOIN/GET) -> TERMINATED
 */
public class Java_2_ThreadCreation {

    public static void main(String[] args) throws Exception {

        // ---------------------------------------------------------
        // 1. EXTENDING THREAD (Legacy / Simple Tasks)
        // ---------------------------------------------------------
        // Why: Direct access to Thread API.
        // Limitation: "Single Inheritance Trap" (cannot extend another class).
        // Ex - 2 people doing two work
        var t1 = new LegacyThread();
        t1.start(); 
        t1.join(); // Blocks Main until t1 is TERMINATED

        // ---------------------------------------------------------
        // 2. IMPLEMENTING RUNNABLE (Decoupled Task logic)
        // ---------------------------------------------------------
        // Why: Separates the 'Task' from the 'Worker'. Preferred over #1.
        // Limitation: Cannot return results or throw Checked Exceptions.
        Runnable task = () -> System.out.println("[Runnable] Executing decoupled task logic.");
        Thread t2 = new Thread(task);
        
        t2.start();
        t2.join();

        // ---------------------------------------------------------
        // 3. CALLABLE & EXECUTORS (Enterprise Standard)
        // ---------------------------------------------------------
        // Why: Supports return values, Checked Exceptions, and Thread Reuse.
        // Use Case: Fetching data from DB/APIs.
        
        // try-with-resources (Java 19+) ensures executor.shutdown() is called automatically
        try (ExecutorService executor = Executors.newFixedThreadPool(2)) {
            
            Callable<String> fetchTask = () -> {
                Thread.sleep(100); // Simulate I/O
                return "Data from Microservice A";
            };

            System.out.println("[Main] Submitting Callable...");
            Future<String> future = executor.submit(fetchTask);

            // .get() is a BLOCKING call. It puts the Main thread in WAITING state.
            String result = future.get(); 
            System.out.println("[Main] Received result: " + result);
        }

        // ---------------------------------------------------------
        // 4. COMPLETABLE FUTURE (Modern Non-Blocking Async)
        // ---------------------------------------------------------
        // Why: Avoids Callback Hell and Blocking .get() calls.
        CompletableFuture.supplyAsync(() -> "Processed Event")
            .thenApply(s -> s + " @ " + System.currentTimeMillis())
            .thenAccept(finalResult -> System.out.println("[Async] Pipeline finished: " + finalResult));
            
        System.out.println("[Main] Moving on without waiting for Async Pipeline!");
    }

    static class LegacyThread extends Thread {
        @Override public void run() { System.out.println("[Thread] Running Legacy Thread."); }
    }
}

/*
================================================================================
ENTERPRISE DEEP DIVE: THREADING ARCHITECTURE
================================================================================

1️⃣ MEMORY & PERFORMANCE (The "Why")
-----------------------------------
• Thread Object: Each 'new Thread()' allocates ~1MB for its Private Stack (OOM Risk).
• Thread Pool: Reuses existing threads. Tasks go into a BlockingQueue if workers are busy.
• Context Switching: Expensive CPU operation where OS saves/loads thread registers.

2️⃣ INTERNAL STATE TRANSITIONS
------------------------------

• NEW: Object created.
• RUNNABLE: After start(), waiting for OS CPU slice.
• BLOCKED: Waiting for a monitor lock (synchronized).
• WAITING: Caused by join() or wait(). Thread is idle.
• TIMED_WAITING: Caused by sleep(ms) or join(ms).
• TERMINATED: run() finished or exception thrown.

3️⃣ COMPARISON TABLE: TASK TYPES
-------------------------------
| Approach | Interface | Method | Returns | Checked Exception |
|----------|-----------|--------|---------|-------------------|
| Thread   | Class     | run()  | void    | No                |
| Runnable | Interface | run()  | void    | No                |
| Callable | Interface | call() | V       | YES               |
|

4️⃣ THE "OOM" (OUT OF MEMORY) PITFALL
-------------------------------------
• Issue: Creating 10,000 raw threads manually.
• Result: JVM throws "java.lang.OutOfMemoryError: unable to create new native thread".
• Solution: Use ExecutorService with a FixedPoolSize or Virtual Threads (Java 21+).

5️⃣ REAL-WORLD SYSTEM USAGE
---------------------------
✔ Batch Processing: Using FixedThreadPool to process 1M records in chunks.
✔ Web Servers: Tomcat uses a 'Connector Thread Pool' to handle HTTP requests.
✔ Parallel Computing: ForkJoinPool used by Java Streams to split tasks.

INTERVIEW ONE-LINER:
-------------------
"Prefer 'Runnable' for simple async tasks to keep classes extensible, but always use 
'Callable' with 'ExecutorService' for production-grade async pipelines that require 
error handling and thread management."
================================================================================

Approach,Analogy,Key Takeaway
================================================================================
JAVA CONCURRENCY – APPROACH COMPARISON TABLE (CHEAT SHEET)
================================================================================

| Approach            | Analogy                     | Key Takeaway |
|---------------------|-----------------------------|--------------|
| Extend Thread       | 2 People, 2 Rooms           | Each thread is isolated. Hard to share data. Not scalable. |
| Runnable            | 2 People, 1 Workstation     | Best for sharing data and reusing task logic. |
| Callable            | Worker + Receipt            | Use when you need a return value or checked exception. |
| ExecutorService     | Managed Factory             | Prevents creating too many threads (avoids OOM). |
| CompletableFuture   | Assembly Line               | Non-blocking async pipeline, best for chained tasks. |
| Virtual Threads     | Unlimited Gig-Workers       | Massive I/O concurrency with minimal memory cost. |

================================================================================
ONE-LINE INTERVIEW MEMORY HOOK
================================================================================

• Thread → isolated worker  
• Runnable → shared task  
• Callable → result + exception  
• Executor → controlled workers  
• CompletableFuture → async flow  
• Virtual Thread → millions of lightweight workers  

================================================================================



*/