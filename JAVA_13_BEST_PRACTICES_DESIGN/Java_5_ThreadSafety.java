package JAVA_13_BEST_PRACTICES_DESIGN;

import java.util.concurrent.atomic.AtomicInteger;

public class Java_5_ThreadSafety {
    public static void main(String[] args) {
        AtomicInteger counter = new AtomicInteger(0);

        counter.incrementAndGet(); // Thread-safe increment
        System.out.println("Counter: " + counter.get());
    }
}

/*
================================================================================
THREAD SAFETY – DEEP DIVE
================================================================================
WHAT:
- Guarantees correct behavior when multiple threads access shared data concurrently.

WHY IT EXISTS:
- Race conditions and inconsistent state in multi-threaded apps.

INTERNAL WORKING:
- AtomicInteger uses CAS (Compare-And-Swap) instructions at JVM level.
- Ensures read-modify-write is atomic.
- No explicit locking needed → lightweight.

ENTERPRISE PITFALLS:
- Non-thread-safe counters → wrong results in parallel processing.
- Synchronized collections → may cause contention.

REAL USE CASE:
- Multi-threaded counters
- Shared caches
- Logging frameworks
- Microservices metrics

INTERVIEW Q&A:
Q1: How to make counters thread-safe without synchronized? → AtomicInteger/AtomicLong.
Q2: Difference between synchronized and AtomicInteger? → Atomic is lock-free.
Q3: Can immutability improve thread-safety? → Yes, immutable objects are inherently safe.
================================================================================
*/
