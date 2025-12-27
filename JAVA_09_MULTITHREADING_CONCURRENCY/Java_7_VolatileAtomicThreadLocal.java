package JAVA_09_MULTITHREADING_CONCURRENCY;

import java.util.concurrent.atomic.AtomicInteger;

public class Java_7_VolatileAtomicThreadLocal {

    private volatile int counter = 0;
    private AtomicInteger atomicCounter = new AtomicInteger(0);
    private static ThreadLocal<String> threadLocal = ThreadLocal.withInitial(() -> "Default");

    public static void main(String[] args) {
        Java_7_VolatileAtomicThreadLocal obj = new Java_7_VolatileAtomicThreadLocal();

        // =========================
        // Volatile demo
        // =========================
        Thread t1 = new Thread(() -> obj.incrementVolatile());
        Thread t2 = new Thread(() -> obj.incrementVolatile());
        t1.start();
        t2.start();

        // =========================
        // Atomic demo
        // =========================
        obj.atomicCounter.incrementAndGet();

        // =========================
        // ThreadLocal demo
        // =========================
        System.out.println("Main thread: " + threadLocal.get());
        threadLocal.set("Updated in main");
        System.out.println("Main thread after set: " + threadLocal.get());
    }

    public void incrementVolatile() {
        counter++;
    }
}

/*
================================================================================
VOLATILE / ATOMIC / THREADLOCAL – DEEP DIVE
================================================================================

WHAT:
-----
• Volatile → ensures visibility of shared variable across threads.
• Atomic → provides atomic operations for primitives / objects.
• ThreadLocal → thread-confined storage.

WHY IT EXISTS:
--------------
• Volatile: solves caching visibility problem.
• Atomic: solves non-atomic read-modify-write race.
• ThreadLocal: per-thread state without synchronization.

INTERNAL WORKING:
-----------------
Volatile:
• Directly reads/writes main memory
• CPU cache flush
AtomicInteger:
• Uses CAS (compare-and-swap) at JVM level
ThreadLocal:
• Each thread has ThreadLocalMap internally
• Key = ThreadLocal instance, Value = thread-specific value

DEFAULT VALUES:
---------------
• Volatile: no default, just keyword
• AtomicInteger: initial value = 0
• ThreadLocal: initialValue can be defined

TIME COMPLEXITY:
----------------
• Volatile read/write: O(1)
• Atomic CAS: O(1) amortized, contention affects retries
• ThreadLocal get/set: O(1) per thread

CORE FEATURES:
--------------
• Volatile: visibility
• Atomic: atomicity
• ThreadLocal: thread confinement
• Lightweight alternatives to locks

ENTERPRISE PITFALLS:
-------------------
❌ Volatile ≠ atomic → race in ++
❌ Excessive ThreadLocal → memory leaks
❌ Misusing Atomic references for compound operations

REAL SYSTEM USAGE:
-----------------
✔ Counters & metrics
✔ Thread-specific context
✔ Lock-free data structures
✔ Logging context per thread

INTERVIEW QUESTIONS + ANSWERS:
------------------------------
Q1: Can volatile replace synchronization?
A: Only for visibility, not atomic operations.

Q2: Difference between Atomic and volatile?
A: Atomic provides atomic read-modify-write; volatile only visibility.

Q3: ThreadLocal use case?
A: DB connections, user context per thread in web apps.

INTERVIEW ONE-LINER:
-------------------
"Volatile, Atomic, and ThreadLocal provide visibility, atomicity, and thread confinement without heavy locks."
================================================================================
*/
