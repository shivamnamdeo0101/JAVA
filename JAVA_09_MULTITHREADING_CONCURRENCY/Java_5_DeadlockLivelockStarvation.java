package JAVA_09_MULTITHREADING_CONCURRENCY;

public class Java_5_DeadlockLivelockStarvation {

    static class Resource {
        final String name;
        Resource(String name) { this.name = name; }
    }

    private static final Resource RES1 = new Resource("RES1");
    private static final Resource RES2 = new Resource("RES2");

    public static void main(String[] args) throws InterruptedException {

        // =========================
        // Deadlock example
        // =========================
        Thread t1 = new Thread(() -> {
            synchronized (RES1) {
                sleep(100);
                synchronized (RES2) {}
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (RES2) {
                sleep(100);
                synchronized (RES1) {}
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();
    }

    private static void sleep(int ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) { e.printStackTrace(); }
    }
}

/*
================================================================================
DEADLOCK, Livelock, Starvation – DEEP DIVE
================================================================================

WHAT:
-----
Deadlock: threads wait indefinitely for each other’s resources.
Livelock: threads active but cannot make progress.
Starvation: thread never gets CPU due to scheduling.

WHY IT EXISTS:
--------------
• Shared resources and locks without ordering → deadlock.
• Aggressive lock yielding → livelock.
• Low-priority threads → starvation.
• Critical to understand for multi-threaded systems to avoid blocking production workloads.

INTERNAL WORKING:
-----------------
• JVM scheduler allocates CPU slices.
• Locks held at object/explicit lock level.
• Deadlock occurs when circular wait exists (resource graph).
• Livelock occurs when threads repeatedly retry, yielding locks.
• Starvation occurs under unfair scheduling.

DEFAULT VALUES:
---------------
• Locks by default are non-fair → starvation possible
• Thread priorities influence scheduler

CORE FEATURES:
--------------
• Concurrency hazards
• Needs ordering and monitoring
• Can be detected and avoided programmatically

ENTERPRISE PITFALLS:
-------------------
❌ Unordered locking → deadlocks
❌ Misusing yield() → livelock
❌ Ignoring thread priorities → starvation
❌ Blocking critical threads → system freeze

REAL SYSTEM USAGE:
-----------------
✔ Database transaction locks
✔ Multi-threaded resource access
✔ Distributed locks

INTERVIEW QUESTIONS + ANSWERS:
------------------------------
Q1: How to detect deadlock?
A: jstack, thread dumps, monitoring tools.

Q2: Difference between deadlock and livelock?
A: Deadlock: blocked; Livelock: active but not progressing.

Q3: How to prevent starvation?
A: Use fair locks or adjust thread priorities.

INTERVIEW ONE-LINER:
-------------------
"Deadlocks, livelocks, and starvation are concurrency hazards that must be proactively managed in multi-threaded enterprise systems."
================================================================================
*/
