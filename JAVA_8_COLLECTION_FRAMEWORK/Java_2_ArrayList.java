package JAVA_8_COLLECTION_FRAMEWORK;
import java.util.*;

public class Java_2_ArrayList {
    public static void main(String[] args) {

        List<String> list = new ArrayList<>();

        // ================= CORE OPERATIONS =================
        list.add("A");
        list.add("B");
        list.add(1, "C");     // insert at index

        list.get(0);          // O(1)
        list.set(1, "D");     // replace
        list.remove("A");     // remove by value
        list.remove(0);       // remove by index

        list.contains("D");  // search
        list.indexOf("D");   // position
        list.size();
        list.isEmpty();

        // ================= BULK OPERATIONS =================
        list.addAll(List.of("X", "Y"));
        list.removeAll(List.of("X"));
        list.retainAll(List.of("Y"));

        // ================= ITERATION =================
        for (String s : list) {}

        Iterator<String> it = list.iterator();
        while (it.hasNext()) it.next();

        // ================= UTILITY =================
        list.clear();
    }
}

/*
========================= ARRAYLIST – FEATURES, DESIGN & INTERNAL WORKING =========================

WHAT:
-----
ArrayList is a **resizable array implementation** of the List interface.
Internally, it behaves like a dynamic array that grows automatically
when elements exceed its capacity.

WHY ARRAYLIST EXISTS:
---------------------
Java arrays are:
❌ Fixed in size
❌ No dynamic insertion/removal
❌ No rich utility APIs

ArrayList solves:
✔ Dynamic resizing
✔ Random access
✔ Rich collection operations
✔ Better abstraction over arrays

-----------------------------------------------------------------------------------------------
INTERNAL WORKING (VERY IMPORTANT – JVM LEVEL)
-----------------------------------------------------------------------------------------------

1️⃣ INTERNAL DATA STRUCTURE:
----------------------------
ArrayList is backed by:
    transient Object[] elementData;

This array stores all elements contiguously in memory.

2️⃣ DEFAULT CAPACITY:
---------------------
• Initial capacity = 10 (when first element is added)
• Empty constructor does NOT allocate immediately
• Memory allocated lazily (on first add)

3️⃣ ADD OPERATION (add(E e)):
-----------------------------
Step-by-step:
• Check if elementData has space
• If yes → insert element
• If no → trigger grow()

Time Complexity:
• O(1) amortized
• O(n) when resizing occurs

4️⃣ RESIZING LOGIC (grow()):
----------------------------
newCapacity = oldCapacity + (oldCapacity >> 1)
             = oldCapacity * 1.5

Example:
10 → 15 → 22 → 33 → …

Why 1.5x?
---------
• Faster than small growth
• Less memory waste than doubling
• Balanced memory vs performance

5️⃣ INSERT AT INDEX (add(index, element)):
------------------------------------------
• Validate index
• Shift elements right using System.arraycopy()
• Insert element

Time Complexity:
• O(n) (shifting required)

6️⃣ REMOVE OPERATION:
---------------------
remove(index):
• Validate index
• Shift elements left
• Nullify last element (GC friendly)

remove(Object):
• Linear search using equals()
• Calls remove(index) internally

7️⃣ GET OPERATION (get(index)):
-------------------------------
• Direct array access
• No traversal
• Extremely fast

Time Complexity:
• O(1)

8️⃣ ITERATION INTERNALS:
-----------------------
Enhanced for-loop uses:
• Iterator internally
• Iterator maintains expectedModCount
• Structural modification triggers
  ConcurrentModificationException (Fail-Fast)

-----------------------------------------------------------------------------------------------
CORE FEATURES:
--------------
✔ Ordered (insertion order preserved)
✔ Allows duplicates
✔ Allows multiple nulls
✔ Random access (index-based)
❌ Not thread-safe

-----------------------------------------------------------------------------------------------
WHY THESE METHODS EXIST (DESIGN INTENT):
---------------------------------------
add(index)       → precise UI insertion (pagination, drag-drop lists)
set(index)       → update value without shifting
contains()       → quick membership check for small datasets
addAll()         → batch DB / API responses
retainAll()      → permission filtering, feature toggles

WHY NO public removeRange():
----------------------------
• Method exists but is protected
• Used internally by subList()
• Prevents accidental mass deletion bugs

-----------------------------------------------------------------------------------------------
ENTERPRISE PITFALLS:
-------------------
❌ Using ArrayList for frequent middle deletes
❌ Using it as Queue / Stack
❌ Sharing same instance across threads
❌ Assuming capacity == size
❌ Storing mutable objects without proper equals()

-----------------------------------------------------------------------------------------------
REAL SYSTEM USAGE:
-----------------
✔ REST API responses
✔ UI tables & dropdowns
✔ Search results
✔ Read-heavy cache layers
✔ In-memory aggregation

-----------------------------------------------------------------------------------------------
FOLLOW-UP INTERVIEW QUESTIONS:
------------------------------
Q1: How to optimize ArrayList growth?
A : new ArrayList<>(expectedSize)

Q2: Why ArrayList is faster than LinkedList?
A : Contiguous memory + index-based access

Q3: Is ArrayList thread-safe?
A : No → use Collections.synchronizedList or CopyOnWriteArrayList

Q4: Why remove is slower?
A : Shifting elements + array copy

-----------------------------------------------------------------------------------------------
INTERVIEW ONE-LINER:
-------------------
"ArrayList trades cheap reads for expensive writes due to its
contiguous memory structure."

===============================================================================================
*/
