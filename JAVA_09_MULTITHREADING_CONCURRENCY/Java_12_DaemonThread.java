package JAVA_09_MULTITHREADING_CONCURRENCY;

/**
 * Daemon Thread Demo
 */
public class Java_12_DaemonThread {

    public static void main(String[] args) throws InterruptedException {

        Thread userThread = new Thread(() -> {
            try {
                System.out.println("User thread started");
                Thread.sleep(3000);
                System.out.println("User thread finished");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        Thread daemonThread = new Thread(() -> {
            while (true) {
                try {
                    System.out.println("Daemon thread running in background...");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    break;
                }
            }
        });

        // Mark daemon BEFORE start()
        daemonThread.setDaemon(true);

        userThread.start();
        daemonThread.start();

        // main thread exits after userThread completes
        userThread.join();
        System.out.println("Main thread exiting");
    }
}

/*
================================================================================
DAEMON THREAD – DEEP DIVE
================================================================================

WHAT:
-----
• Daemon thread is a **background service thread**.
• JVM does NOT wait for daemon threads to finish.
• When all user (non-daemon) threads end → JVM exits.

WHY IT EXISTS:
--------------
• Background tasks that should not block JVM shutdown.
• System-level services:
  - Garbage Collector
  - JIT Compiler
  - Background cleanup
• Avoid hanging applications due to infinite background loops.

INTERNAL WORKING:
-----------------
• Each thread has a daemon flag (true/false).
• Default: threads inherit daemon status from parent.
• JVM shutdown condition:
    → No NON-daemon threads alive
• Daemon threads are abruptly terminated by JVM.

IMPORTANT RULES:
----------------
✔ setDaemon(true) must be called BEFORE start()
❌ Calling after start() → IllegalThreadStateException
❌ Daemon threads should not manage critical resources

THREAD LIFECYCLE BEHAVIOR:
-------------------------
User Thread Alive  | Daemon Thread | JVM
------------------|---------------|-----
YES               | YES           | RUNNING
NO                | YES           | ❌ JVM EXITS
NO                | NO            | ❌ JVM EXITS

REAL SYSTEM USAGE:
-----------------
✔ Garbage Collection
✔ Cache cleanup
✔ Monitoring & logging
✔ Background metrics collection
✔ Idle connection cleanup

ENTERPRISE PITFALLS:
-------------------
❌ Using daemon for DB writes or file I/O
❌ Expecting finally block to run (not guaranteed)
❌ Forgetting daemon threads die abruptly

COMMON INTERVIEW QUESTIONS:
---------------------------
Q1: What happens if only daemon threads remain?
A: JVM exits immediately.

Q2: Can daemon thread prevent JVM shutdown?
A: No.

Q3: Is main thread daemon?
A: No, main is always a user thread.

Q4: Can daemon thread create user thread?
A: Yes, but child inherits daemon status unless changed.

INTERVIEW ONE-LINER:
-------------------
"Daemon threads are background service threads that do not prevent JVM shutdown once all user threads finish."
================================================================================
*/
