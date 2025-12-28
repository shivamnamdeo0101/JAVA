package JAVA_09_MULTITHREADING_CONCURRENCY;

/*
===============================================================================
DEADLOCK – THREAD STATE LINE-BY-LINE (UPDATED)
===============================================================================

Goal:
-----
• Show REAL deadlock
• Print thread states at EACH IMPORTANT STEP
• Clearly show BLOCKED vs TIMED_WAITING vs RUNNABLE

Interview Focus:
----------------
• Deadlock → BLOCKED
• sleep() → TIMED_WAITING
• synchronized entry wait ≠ WAITING
===============================================================================
*/

public class Java_5_DeadlockDemo {

    private static final Object LOCK_A = new Object();
    private static final Object LOCK_B = new Object();

    public static void main(String[] args) throws Exception {

        Thread t1 = new Thread(() -> {
            System.out.println("T1: Trying to acquire LOCK_A");
            synchronized (LOCK_A) {
                System.out.println("T1: Acquired LOCK_A");
                sleep(200); // TIMED_WAITING

                System.out.println("T1: Trying to acquire LOCK_B");
                synchronized (LOCK_B) { // never acquired
                    System.out.println("T1: Acquired LOCK_B");
                }
            }
        }, "Thread-1");

        Thread t2 = new Thread(() -> {
            System.out.println("T2: Trying to acquire LOCK_B");
            synchronized (LOCK_B) {
                System.out.println("T2: Acquired LOCK_B");
                sleep(200); // TIMED_WAITING

                System.out.println("T2: Trying to acquire LOCK_A");
                synchronized (LOCK_A) { // never acquired
                    System.out.println("T2: Acquired LOCK_A");
                }
            }
        }, "Thread-2");

        System.out.println("MAIN: Starting threads");
        t1.start();
        t2.start();

        // --- Observe states over time ---
        Thread.sleep(50);
        printStates("After start", t1, t2);

        Thread.sleep(250);
        printStates("After sleep()", t1, t2);

        Thread.sleep(500);
        printStates("Deadlock confirmed", t1, t2);
    }

    private static void printStates(String phase, Thread t1, Thread t2) {
        System.out.println("\n=== " + phase + " ===");
        System.out.println("T1 state: " + t1.getState());
        System.out.println("T2 state: " + t2.getState());
    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/*
===============================================================================
EXPECTED STATE TRANSITIONS (VERY IMPORTANT)
===============================================================================

1️⃣ After start
----------------
T1 → RUNNABLE
T2 → RUNNABLE

2️⃣ During sleep()
------------------
T1 → TIMED_WAITING (sleep)
T2 → TIMED_WAITING (sleep)

3️⃣ Deadlock phase
------------------
T1 → BLOCKED (waiting for LOCK_B)
T2 → BLOCKED (waiting for LOCK_A)

🚫 NO THREAD IS IN WAITING STATE

===============================================================================
WHY BLOCKED (INTERVIEW GOLD)
===============================================================================

• synchronized uses intrinsic MONITOR lock
• Thread is trying to ENTER synchronized block
• Lock is already held by another thread
• JVM puts thread in BLOCKED state

WAITING happens ONLY when:
-------------------------
• Object.wait()
• Thread.join()
• LockSupport.park()

Deadlock NEVER uses wait().

===============================================================================
ONE-LINE INTERVIEW ANSWER
===============================================================================
"In deadlock, threads are BLOCKED because they are competing for monitor locks,
not voluntarily waiting using wait() or join()."
===============================================================================
*/
