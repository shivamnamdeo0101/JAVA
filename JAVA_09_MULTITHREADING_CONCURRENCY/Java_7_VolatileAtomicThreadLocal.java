package JAVA_09_MULTITHREADING_CONCURRENCY;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ================================================================================
 * VOLATILE / ATOMIC / THREADLOCAL – THE ULTIMATE CHEATSHEET
 * ================================================================================
 * * 1. VOLATILE: 
 * - Problem: CPU Caching (Visibility). Threads might keep local copies of variables.
 * - Solution: Forces read/write to Main Memory.
 * - Pitfall: NOT atomic. 'counter++' is still dangerous.
 * * 2. ATOMIC (CAS):
 * - Problem: Race conditions during Read-Modify-Write.
 * - Solution: Uses "Compare-And-Swap" CPU instructions.
 * - Benefit: Lock-free thread safety (no thread suspension).
 * * 3. THREADLOCAL:
 * - Problem: Shared state complexity.
 * - Solution: Thread Confinement. Each thread gets its own private sandbox.
 * - Pitfall: Memory leaks in Thread Pools (must call .remove()).
 * ================================================================================
 */
public class Java_7_VolatileAtomicThreadLocal {

    // Visibility guaranteed, but 'volatileCounter++' is NOT thread-safe!
    private volatile int volatileCounter = 0;

    // Atomicity guaranteed via CAS (Compare-And-Swap)
    private final AtomicInteger atomicCounter = new AtomicInteger(0);

    // Isolation guaranteed: ThreadLocalMap stores values unique to the current thread
    private static final ThreadLocal<String> threadContext = ThreadLocal.withInitial(() -> "InitialValue");

    public static void main(String[] args) throws InterruptedException {
        Java_7_VolatileAtomicThreadLocal demo = new Java_7_VolatileAtomicThreadLocal();

        // --- DEMONSTRATION: VOLATILE vs ATOMIC ---
        Runnable incrementTask = () -> {
            for (int i = 0; i < 1000; i++) {
                demo.volatileCounter++;        // WRONG: Non-atomic (Read -> Add -> Write)
                demo.atomicCounter.getAndIncrement(); // RIGHT: Atomic CAS operation
            }
        };

        Thread t1 = new Thread(incrementTask);
        Thread t2 = new Thread(incrementTask);

        t1.start(); t2.start();
        t1.join();  t2.join();

        System.out.println("Volatile Counter (likely < 2000): " + demo.volatileCounter);
        System.out.println("Atomic Counter (exactly 2000): " + demo.atomicCounter.get());

        // --- DEMONSTRATION: THREADLOCAL ---
        Thread t3 = new Thread(() -> {
            threadContext.set("Thread-3-Data");
            System.out.println("T3 Context: " + threadContext.get());
            threadContext.remove(); // CRITICAL: Prevent memory leaks in pools
        });

        t3.start();
        t3.join();
        System.out.println("Main Thread Context (remains unchanged): " + threadContext.get());
    }
}

/*
================================================================================
INTERNAL MECHANICS (INTERVIEW DEEP DIVE)
================================================================================



1. MEMORY BARRIERS (Volatile):
   - When you write to volatile, the JVM inserts a "Store-Store" and "Store-Load" barrier.
   - This ensures all preceding writes are flushed to main memory.

2. CAS - COMPARE AND SWAP (Atomic):
   - Theoretical Complexity: O(1) amortized.
   - Mechanism: Expects value A, wants to set to value B. 
   - If current value == A, set to B. Else, retry in a loop (Spin-lock behavior).

3. THREADLOCAL MAP:
   - Every 'Thread' object has a 'ThreadLocalMap' field.
   - Key: The ThreadLocal instance.
   - Value: Your object.
   - Entry uses WeakReferences for Keys, but Values stay until thread dies or remove() is called.

================================================================================
ENTERPRISE BEST PRACTICES & PITFALLS
================================================================================
- ❌ DON'T use Volatile for counters or business logic flags involving logic (if x then y).
- ❌ DON'T forget to call .remove() on ThreadLocal if using App Servers (Tomcat/Jetty).
- ✅ DO use AtomicInteger/Long for high-performance metrics and global counters.
- ✅ DO use ThreadLocal for UserSessions, TransactionIDs, and SimpleDataFormats (which are not thread-safe).

Q: Can Volatile be used with null? 
A: Yes, it is often used in Double-Checked Locking for Singletons to prevent 
   half-initialized objects from being visible to other threads.

Q: Atomic vs Synchronized?
A: Atomic is non-blocking (optimistic). Synchronized is blocking (pessimistic).
   Atomics perform better under low-to-moderate contention.
================================================================================
*/