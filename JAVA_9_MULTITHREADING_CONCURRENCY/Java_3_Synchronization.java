package JAVA_9_MULTITHREADING_CONCURRENCY;

public class Java_3_Synchronization {

    private int counter = 0;

    public static void main(String[] args) throws InterruptedException {

        Java_3_Synchronization obj = new Java_3_Synchronization();

        Thread t1 = new Thread(() -> obj.increment());
        Thread t2 = new Thread(() -> obj.increment());

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Counter value: " + obj.counter);
    }

    // =========================
    // SYNCHRONIZED METHOD
    // =========================
    public synchronized void increment() {
        for (int i = 0; i < 1000; i++) {
            counter++;
        }
    }
}

/*
================================================================================
SYNCHRONIZATION – DEEP DIVE
================================================================================

WHAT:
-----
Mechanism to control **access to shared resources** in multithreading to avoid race conditions.

WHY IT EXISTS:
--------------
• Multiple threads modifying shared data can corrupt state.
• Synchronization ensures atomicity and visibility.
• Provides **mutual exclusion** and consistency.

INTERNAL WORKING:
-----------------
• Uses intrinsic monitor lock per object.
• synchronized methods / blocks acquire object lock.
• JVM maintains monitor tables per object.
• Only one thread can hold lock; others block.
• Reentrant: thread holding lock can reacquire.

DEFAULT VALUES:
---------------
• Lock is non-fair by default
• Each object has a single intrinsic lock
• Thread contention handled by JVM scheduler

TIME COMPLEXITY / PERFORMANCE:
-----------------------------
• Lock acquire/release is O(1) but contention may block threads.
• Excessive locking → throughput reduction.

CORE FEATURES:
--------------
• Mutual exclusion
• Visibility guarantee
• Reentrant
• Can lock instance or class (static synchronized)

ENTERPRISE PITFALLS:
-------------------
❌ Locking large blocks → reduces concurrency
❌ Using multiple locks → deadlocks
❌ Ignoring visibility → stale reads

REAL SYSTEM USAGE:
-----------------
✔ Shared counters
✔ Banking transactions
✔ Thread-safe caches
✔ Resource pools

INTERVIEW QUESTIONS + ANSWERS:
------------------------------
Q1: Difference between synchronized method and block?
A: Block allows finer control, reduces contention.

Q2: Can a synchronized method be static?
A: Yes, locks on Class object.

Q3: What happens if exception occurs in synchronized method?
A: Lock is automatically released.

INTERVIEW ONE-LINER:
-------------------
"Synchronization ensures safe shared resource access while balancing performance and thread contention."
================================================================================
*/
