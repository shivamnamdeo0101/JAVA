package JAVA_08_COLLECTION_FRAMEWORK;
import java.util.*;

public class Java_5_Vector {
    public static void main(String[] args) {

        Vector<String> vector = new Vector<>();

        // ================= CORE OPERATIONS =================
        vector.add("A");
        vector.add("B");
        vector.add(1, "C");     // insert at index

        vector.get(0);          // O(1)
        vector.set(1, "D");     // replace
        vector.remove("A");     // remove by value
        vector.remove(0);       // remove by index

        vector.contains("D");
        vector.indexOf("D");
        vector.size();
        vector.isEmpty();

        // ================= BULK OPERATIONS =================
        vector.addAll(List.of("X", "Y"));
        vector.removeAll(List.of("X"));
        vector.retainAll(List.of("Y"));

        // ================= ITERATION =================
        for (String s : vector) {}

        Iterator<String> it = vector.iterator();
        while (it.hasNext()) it.next();

        // ================= UTILITY =================
        vector.clear();
    }
}

/*
========================= VECTOR – FEATURES, DESIGN & INTERNAL WORKING =========================

WHAT:
-----
Vector is a **thread-safe resizable array implementation** of the List interface.
It is the synchronized counterpart of ArrayList and was introduced in **Java 1.0**.

WHY VECTOR EXISTS:
------------------
At the time of early Java:
✔ Multithreading was core
✔ No concurrent collections existed

Vector solved:
✔ Thread safety
✔ Dynamic resizing
✔ Safe shared access

However, it is now considered **legacy**.

-----------------------------------------------------------------------------------------------
INTERNAL WORKING (JVM LEVEL)
-----------------------------------------------------------------------------------------------

1️⃣ INTERNAL DATA STRUCTURE:
----------------------------
Vector is backed by:
    protected Object[] elementData;

Same as ArrayList → contiguous memory array.

2️⃣ DEFAULT CAPACITY:
---------------------
• Default capacity = 10
• Can specify capacity increment manually

Constructors:
-------------
Vector()
Vector(int initialCapacity)
Vector(int initialCapacity, int capacityIncrement)

3️⃣ SYNCHRONIZATION MECHANISM:
------------------------------
Every public method is synchronized:

Example:
---------
public synchronized boolean add(E e)

This means:
• Entire method is locked
• Only ONE thread can access at a time
• Global object-level lock

4️⃣ ADD OPERATION:
------------------
• Acquires lock
• Checks capacity
• Grows if needed
• Inserts element
• Releases lock

Time Complexity:
----------------
• O(1) amortized
• O(n) during resize
• Additional overhead due to synchronization

5️⃣ RESIZING LOGIC:
-------------------
Two strategies:

A) capacityIncrement > 0:
-------------------------
newCapacity = oldCapacity + capacityIncrement

B) capacityIncrement == 0:
--------------------------
newCapacity = oldCapacity * 2

Example:
---------
10 → 20 → 40 → 80

WHY DOUBLE SIZE?
----------------
• Older JVM memory strategy
• Faster growth but more memory waste

6️⃣ INSERT AT INDEX:
--------------------
• Lock acquired
• Validate index
• Shift elements
• Insert

Time Complexity:
----------------
• O(n)

7️⃣ REMOVE OPERATION:
---------------------
• Linear search using equals()
• Shift elements
• Nullify last reference

All under synchronized block.

8️⃣ ITERATION INTERNALS:
-----------------------
Iterator is **Fail-Fast**:
• Uses modCount
• Concurrent modification throws
  ConcurrentModificationException

⚠ Even though Vector is thread-safe,
Iterator is NOT thread-safe.

-----------------------------------------------------------------------------------------------
CORE FEATURES:
--------------
✔ Ordered
✔ Allows duplicates
✔ Allows multiple nulls
✔ Random access
✔ Thread-safe (method-level)
❌ Poor scalability

-----------------------------------------------------------------------------------------------
WHY VECTOR IS SLOW:
-------------------
❌ Global synchronization lock
❌ Blocks even read operations
❌ No lock striping
❌ Not optimized for modern CPUs

-----------------------------------------------------------------------------------------------
VECTOR vs ARRAYLIST:
--------------------
ArrayList → Fast, non-thread-safe
Vector    → Thread-safe, slow

-----------------------------------------------------------------------------------------------
ENTERPRISE PITFALLS:
-------------------
❌ Using Vector in modern apps
❌ Assuming Iterator is thread-safe
❌ Excessive blocking under high concurrency
❌ Memory waste due to doubling strategy

-----------------------------------------------------------------------------------------------
MODERN REPLACEMENTS:
--------------------
✔ CopyOnWriteArrayList (read-heavy)
✔ Collections.synchronizedList(new ArrayList<>())

-----------------------------------------------------------------------------------------------
REAL SYSTEM USAGE (LEGACY):
--------------------------
✔ Old banking systems
✔ Legacy enterprise apps
✔ Java 1.4 era frameworks

-----------------------------------------------------------------------------------------------
FOLLOW-UP INTERVIEW QUESTIONS:
------------------------------
Q1: Is Vector fully thread-safe?
A : Methods are synchronized, but Iterators are fail-fast.

Q2: Why Vector is discouraged?
A : Poor performance due to coarse-grained locking.

Q3: Difference between Vector and CopyOnWriteArrayList?
A : Vector locks on every operation; COW creates copy on write.

Q4: Can Vector handle high concurrency?
A : No, it scales poorly.

-----------------------------------------------------------------------------------------------
INTERVIEW ONE-LINER:
-------------------
"Vector provides thread safety via synchronized methods,
but sacrifices scalability and performance."

===============================================================================================

*/