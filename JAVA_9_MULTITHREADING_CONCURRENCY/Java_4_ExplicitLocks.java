package JAVA_9_MULTITHREADING_CONCURRENCY;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Java_4_ExplicitLocks {

    private int counter = 0;
    private ReentrantLock lock = new ReentrantLock();
    private ReadWriteLock rwLock = new ReentrantReadWriteLock();

    public static void main(String[] args) throws InterruptedException {
        Java_4_ExplicitLocks obj = new Java_4_ExplicitLocks();

        Thread t1 = new Thread(() -> obj.increment());
        Thread t2 = new Thread(() -> obj.increment());

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Counter after ReentrantLock: " + obj.counter);

        obj.readWriteDemo();
    }

    // =========================
    // ReentrantLock demo
    // =========================
    public void increment() {
        lock.lock();
        try {
            for (int i = 0; i < 1000; i++) counter++;
        } finally {
            lock.unlock();
        }
    }

    // =========================
    // ReadWriteLock demo
    // =========================
    public void readWriteDemo() {
        rwLock.writeLock().lock();
        try {
            counter += 100;
        } finally {
            rwLock.writeLock().unlock();
        }

        rwLock.readLock().lock();
        try {
            System.out.println("Read counter: " + counter);
        } finally {
            rwLock.readLock().unlock();
        }
    }
}

/*
================================================================================
EXPLICIT LOCKS – DEEP DIVE
================================================================================

WHAT:
-----
Explicit locks give **fine-grained control** over synchronization beyond `synchronized`.
Includes:
• ReentrantLock
• ReadWriteLock

WHY IT EXISTS:
--------------
• `synchronized` cannot tryLock(), lockInterruptibly(), or fairness control.
• Explicit locks improve scalability in high-concurrency environments.
• Supports multiple readers/single writer.

INTERNAL WORKING:
-----------------
• ReentrantLock uses AbstractQueuedSynchronizer (AQS)
• Maintains a FIFO queue for waiting threads
• tryLock() → non-blocking attempt
• lockInterruptibly() → responds to interrupts
• ReadWriteLock separates read lock and write lock state internally

DEFAULT VALUES:
---------------
• ReentrantLock: non-fair by default
• ReadWriteLock: non-fair by default
• Fairness can be configured in constructor

TIME COMPLEXITY / PERFORMANCE:
-----------------------------
• Lock acquisition: O(1) amortized
• High contention → threads enqueued
• Reader-heavy scenario → better throughput than synchronized

CORE FEATURES:
--------------
• Mutual exclusion
• Reentrant
• Multiple readers single writer
• Interruptible locking
• Optional fairness

ENTERPRISE PITFALLS:
-------------------
❌ Forgetting unlock → deadlocks
❌ Using write lock unnecessarily → reduces concurrency
❌ Mixing intrinsic and explicit locks → unpredictable behavior

REAL SYSTEM USAGE:
-----------------
✔ Shared caches
✔ Concurrent collections
✔ Multi-reader configurations (DB read/write, config updates)
✔ High-performance microservices

INTERVIEW QUESTIONS + ANSWERS:
------------------------------
Q1: Difference between synchronized and ReentrantLock?
A: Explicit locks offer tryLock(), fairness, interruptible locking; synchronized does not.

Q2: What is ReadWriteLock used for?
A: Multiple readers can access; writer has exclusive access.

Q3: Can ReentrantLock be re-entrant?
A: Yes, same thread can acquire multiple times.

INTERVIEW ONE-LINER:
-------------------
"Explicit locks provide flexible, fine-grained control over concurrent access, critical for scalable multi-threaded systems."
================================================================================
*/
