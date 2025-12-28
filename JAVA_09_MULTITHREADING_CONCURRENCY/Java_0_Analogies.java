package JAVA_09_MULTITHREADING_CONCURRENCY;

/**
 * DETAILED JAVA CONCURRENCY FIX-IT GUIDE
 * -----------------------------------------------------------------------------
 */

/*
 * 1. DEADLOCK FIX: "Global Lock Hierarchy"
 * WHY: It happens when threads grab locks in different orders (A->B and B->A).
 * THE FIX: Enforce a strict rule where every thread must acquire Lock A before Lock B.
 * JAVA TIP: If ordering is impossible, use 'lock.tryLock(timeout)' which prevents 
 * a permanent stall by giving up if the lock isn't available within a set time.
 * 
 * 🧠 Real-World Analogy

🚪 2 Doors + 2 People

Person A: Door 1 pakad ke Door 2 ka wait

Person B: Door 2 pakad ke Door 1 ka wait

👉 Dono bahar khade hain (BLOCKED)
 */
 

/*
 * 2. STARVATION FIX: "FIFO (First-In-First-Out) Scheduling"
 * WHY: JVM thread scheduling is usually "greedy"—the most aggressive thread wins, 
 * leaving low-priority threads to wait forever.
 * THE FIX: Use 'new ReentrantLock(true)'. The 'true' flag tells Java to use a 
 * "Fairness Policy," placing threads in a queue so the longest waiter goes next.
 */

/*
 * 3. LIVELOCK FIX: "Exponential Backoff & Jitter"
 * WHY: Two threads are too "polite"; they both detect a conflict and retry at 
 * the exact same time, repeating the collision forever.
 * THE FIX: Introduce a 'Thread.sleep()' with a random duration (Jitter) before 
 * retrying. This desynchronizes the threads so one finishes while the other sleeps.
 */

/*
 * 4. RACE CONDITION FIX: "Mutual Exclusion & CAS"
 * WHY: Multiple threads perform a "Read-Modify-Write" cycle at the same time, 
 * causing lost updates (e.g., two +1s only resulting in a total increase of 1).
 * THE FIX: Use 'synchronized' to lock the block, or 'AtomicInteger' which uses 
 * "Compare-And-Swap" (CAS)—a hardware-level trick that fails and retries 
 * automatically if the value changed behind its back.
 */
 



// 🔒 synchronized vs tryLock() — IMPORTANT COMPARISON TABLE
// | 🔍 Aspect                           | `synchronized`                      | `tryLock()` (`ReentrantLock`)         |
// | ----------------------------------- | ----------------------------------- | ------------------------------------- |
// | **Basic nature**                    | Intrinsic lock (JVM level)          | Explicit lock (java.util.concurrent)  |
// | **Lock acquisition**                | **Always waits** until lock is free | **Conditional** (may or may not wait) |
// | **Blocking behavior**               | ✅ **Blocking**                      | ❌ **Non-blocking**                    |
// | **One-line meaning**                | *“Wait karna hi padega”*            | *“Mila to kaam, warna aage badho”*    |
// | **Timeout support**                 | ❌ No                                | ✅ Yes (`tryLock(time)`)               |
// | **If lock not available**           | Thread goes to `BLOCKED`            | Returns `false` immediately           |
// | **Thread state while waiting**      | `BLOCKED`                           | `RUNNABLE` / `TIMED_WAITING`          |
// | **Interrupt support while waiting** | ❌ No                                | ✅ Yes                                 |
// | **Fallback logic possible?**        | ❌ No                                | ✅ Yes                                 |
// | **Manual unlock needed?**           | ❌ No (auto)                         | ✅ Yes (`unlock()`)                    |
// | **Fairness support**                | ❌ No                                | ✅ Yes (fair lock option)              |
// | **Deadlock handling**               | ❌ Cannot avoid                      | ✅ Can reduce/avoid                    |
// | **Lock status check**               | ❌ Not possible                      | ✅ `isLocked()`                        |
// | **Exception safety**                | Auto release                        | Must use `finally`                    |
// | **Complexity**                      | Very simple                         | More control, more responsibility     |
// | **Performance (high contention)**   | Lower                               | Higher                                |
// | **Use in frameworks**               | Core Java                           | Enterprise / Concurrent systems       |
// | **Real-world analogy**              | Elevator – wait no matter what      | Elevator – else stairs                |
// | **Best suited for**                 | Simple synchronization              | High-performance, timeout-based logic |


// synchronized is blocking and unconditional, while tryLock() is non-blocking, conditional, and timeout-based.
// Which one should we use?”

// | Situation               | Use                |
// | ----------------------- | ------------------ |
// | Simple mutual exclusion | `synchronized`     |
// | Avoid blocking threads  | `tryLock()`        |
// | Timeout required        | `tryLock(timeout)` |
// | High concurrency system | `tryLock()`        |
// | Legacy / small code     | `synchronized`     |
