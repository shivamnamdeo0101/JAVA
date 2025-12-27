package JAVA_9_MULTITHREADING_CONCURRENCY;

public class Java_1_ThreadLifecycle {
    public static void main(String[] args) {

        Thread t = new Thread(() -> {
            System.out.println("Thread is running...");
        });

        System.out.println("State before start: " + t.getState()); // NEW
        t.start();
        System.out.println("State after start: " + t.getState());  // RUNNABLE

        try {
            t.join(); // wait for completion
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("State after completion: " + t.getState()); // TERMINATED
    }
}

/*
================================================================================
THREAD LIFECYCLE – DEEP DIVE
================================================================================

WHAT:
-----
Thread lifecycle defines the **states a thread goes through** from creation to termination.
States: NEW → RUNNABLE → RUNNING → BLOCKED / WAITING → TIMED_WAITING → TERMINATED

WHY IT EXISTS:
--------------
• Threads allow concurrent execution.
• Lifecycle helps JVM & developer manage scheduling, resources, and synchronization.
• Provides hooks (start, sleep, join, wait) for controlling execution flow.

INTERNAL WORKING:
-----------------
• JVM Thread object wraps OS-level native thread.
• Thread states tracked internally via 'Thread.State' enum.
• modCount & stack frame per thread.
• Thread scheduler decides RUNNABLE → RUNNING.
• OS-level context switching moves thread in CPU.

TIME COMPLEXITY / PERFORMANCE:
-----------------------------
• State transitions are constant-time operations.
• Context switch overhead is OS-dependent.
• Frequent blocking can reduce CPU efficiency.

CORE FEATURES:
--------------
• Lifecycle management
• Thread-safe execution control
• Hooks: start(), sleep(), join(), interrupt()

ENTERPRISE PITFALLS:
-------------------
❌ Ignoring TERMINATED threads (memory leak)
❌ Modifying shared state without synchronization
❌ Misusing join() → deadlocks

REAL SYSTEM USAGE:
-----------------
✔ Job scheduling
✔ Background tasks
✔ Thread pools (Executor)
✔ Async processing in microservices

INTERVIEW QUESTIONS + ANSWERS:
------------------------------
Q1: What are thread states in Java?
A: NEW, RUNNABLE, RUNNING, BLOCKED, WAITING, TIMED_WAITING, TERMINATED.

Q2: Can a terminated thread be restarted?
A: No, start() throws IllegalThreadStateException.

Q3: Difference between RUNNABLE and RUNNING?
A: RUNNABLE is ready to run; RUNNING is actively on CPU.

INTERVIEW ONE-LINER:
-------------------
"Thread lifecycle allows precise control over thread execution and resource management in concurrent systems."
================================================================================
*/
