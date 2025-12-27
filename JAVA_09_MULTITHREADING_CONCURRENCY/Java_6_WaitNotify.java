package JAVA_09_MULTITHREADING_CONCURRENCY;

public class Java_6_WaitNotify {

    private final Object lock = new Object();
    private boolean messageAvailable = false;

    public static void main(String[] args) {
        Java_6_WaitNotify obj = new Java_6_WaitNotify();
        Thread producer = new Thread(() -> obj.produce());
        Thread consumer = new Thread(() -> obj.consume());

        producer.start();
        consumer.start();
    }

    // =========================
    // Producer
    // =========================
    public void produce() {
        synchronized (lock) {
            System.out.println("Producing message...");
            messageAvailable = true;
            lock.notify(); // notify waiting consumer
        }
    }

    // =========================
    // Consumer
    // =========================
    public void consume() {
        synchronized (lock) {
            while (!messageAvailable) {
                try {
                    lock.wait(); // wait until producer notifies
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("Consuming message...");
            messageAvailable = false;
        }
    }
}

/*
================================================================================
WAIT / NOTIFY / NOTIFYALL – DEEP DIVE
================================================================================

WHAT:
-----
• Mechanism for **inter-thread communication**.
• Thread can wait until notified by another thread.

WHY IT EXISTS:
--------------
• Avoid busy-waiting
• Efficient producer-consumer scenarios
• Synchronization beyond mutual exclusion

INTERNAL WORKING:
-----------------
• Each object has a monitor (intrinsic lock) and wait set.
• wait(): releases lock and enters object's wait set
• notify(): picks one waiting thread. - “There is no defined algorithm; it is JVM-dependent and unspecified.”
• notifyAll(): wakes all waiting threads - “Yes, it wakes all waiting threads, but they still compete for the lock, so only one executes at a time.”
• JVM manages monitor queue and thread scheduling

DEFAULT VALUES:
---------------
• wait()/notify() must be inside synchronized block
• No default timeout → indefinite wait
• Threads return to runnable state when notified

TIME COMPLEXITY:
----------------
• O(1) to add/remove from wait set
• Scheduling overhead depends on JVM

CORE FEATURES:
--------------
• Blocking without consuming CPU
• Coordinated thread execution
• Supports multiple producers/consumers

ENTERPRISE PITFALLS:
-------------------
❌ Calling wait() outside synchronized → IllegalMonitorStateException
❌ Missed notify → thread hangs
❌ Spurious wakeups → always check condition in while loop

REAL SYSTEM USAGE:
-----------------
✔ Message queues
✔ Resource pools
✔ Event-driven systems
✔ Multi-threaded pipelines

INTERVIEW QUESTIONS + ANSWERS:
------------------------------
Q1: Why wait() in while loop?
A: To handle spurious wakeups.

Q2: Difference between notify() and notifyAll()?
A: notify() → one thread; notifyAll() → all waiting threads.

Q3: Can wait() be used on Thread object?
A: No, must be object whose monitor is held.

INTERVIEW ONE-LINER:
-------------------
"wait() and notify() enable threads to communicate safely and efficiently, avoiding busy-waiting in concurrent systems."
================================================================================
*/
