package JAVA_08_COLLECTION_FRAMEWORK;

import java.util.Stack;
import java.util.Iterator;
import java.util.List;

public class Java_4_Stack {
    public static void main(String[] args) {

        Stack<Integer> stack = new Stack<>();

        // ================= CORE OPERATIONS =================
        stack.push(10);          // add element (top)
        stack.push(20);
        stack.push(30);

        stack.peek();            // view top without removing
        stack.pop();             // remove top element
        stack.search(10);        // 1-based position from top

        stack.get(0);            // inherited from Vector
        stack.set(0, 99);        // replace element

        stack.contains(20);
        stack.size();
        stack.isEmpty();

        // ================= BULK OPERATIONS =================
        stack.addAll(List.of(100, 200, 300));
        stack.removeAll(List.of(200));
        stack.retainAll(List.of(99, 100));

        // ================= ITERATION =================
        for (Integer i : stack) {}

        Iterator<Integer> it = stack.iterator();
        while (it.hasNext()) it.next();

        // ================= UTILITY =================
        stack.clear();
    }
}

/*
========================= STACK – FEATURES, DESIGN & INTERNAL WORKING =========================

WHAT:
-----
Stack is a **LIFO (Last-In-First-Out)** data structure.
In Java, Stack is a **legacy class** that extends Vector and represents
a stack abstraction using an underlying synchronized dynamic array.

It represents the abstraction:
"Last operation should be undone first."

WHY STACK EXISTS:
-----------------
Before Java Collections matured:
• Developers implemented stack logic manually using arrays
• No standardized LIFO abstraction existed

Java designers introduced Stack to:
✔ Provide a ready-to-use LIFO structure
✔ Support classic algorithms (DFS, recursion simulation)
✔ Offer thread-safe stack operations (initially important)

In modern systems, Stack is largely **discouraged**.

-----------------------------------------------------------------------------------------------
INTERNAL WORKING (JVM LEVEL – VERY IMPORTANT)
-----------------------------------------------------------------------------------------------

1️⃣ INTERNAL DATA STRUCTURE:
----------------------------
Stack extends Vector
Vector internally uses:
    protected Object[] elementData;

So Stack is backed by:
✔ Contiguous Object[] array
✔ All stack operations are built on Vector methods

Key inherited fields:
---------------------
• elementData → stores elements
• elementCount → current size
• capacityIncrement → growth strategy
• modCount → structural modification tracking

2️⃣ PUSH OPERATION (push(E item)):
---------------------------------
Implementation:
• Internally calls addElement(item)
• addElement is synchronized
• Checks capacity
• Grows array if needed
• Inserts at end of array (top of stack)

Time Complexity:
• O(1) amortized
• O(n) if resize occurs

3️⃣ POP OPERATION (pop()):
-------------------------
Steps:
• Check if stack is empty
• Retrieve last element (top)
• Remove it (elementCount--)
• Set reference to null (GC friendly)
• Return removed value

Time Complexity:
• O(1)

4️⃣ PEEK OPERATION (peek()):
---------------------------
• Returns last element
• No modification
• No resizing

Time Complexity:
• O(1)

5️⃣ SEARCH OPERATION (search(Object o)):
---------------------------------------
• Linear search from top to bottom
• Uses equals()
• Returns 1-based index from top

Time Complexity:
• O(n)

6️⃣ GET / SET OPERATIONS:
------------------------
Inherited from Vector:
• get(index) → direct array access
• set(index, value) → replace element

⚠ These break pure stack abstraction
⚠ Provided only because Stack extends Vector

7️⃣ ITERATION INTERNALS:
-----------------------
Iterator behavior:
• Fail-Fast
• Uses modCount
• Concurrent modification throws
  ConcurrentModificationException

⚠ Even though Stack is synchronized,
Iterator is NOT thread-safe.

-----------------------------------------------------------------------------------------------
DEFAULT VALUES:
---------------
• Default capacity = 10
• Capacity growth = double size (if no capacityIncrement)
• Load factor = ❌ Not applicable
• Thread safety = ✔ Method-level synchronized

-----------------------------------------------------------------------------------------------
TIME COMPLEXITY:
----------------
push    → O(1) amortized
pop     → O(1)
peek    → O(1)
search  → O(n)
get     → O(1)
remove  → O(n)

Worst-case resize → O(n)

-----------------------------------------------------------------------------------------------
CORE FEATURES:
--------------
✔ LIFO ordering
✔ Allows duplicates
✔ Allows multiple nulls
✔ Thread-safe (synchronized methods)
❌ Poor scalability
❌ Legacy design

-----------------------------------------------------------------------------------------------
WHY EACH IMPORTANT METHOD EXISTS (DESIGN INTENT):
-----------------------------------------------
push()      → add task, call frame, undo action
pop()       → rollback, undo, backtracking
peek()      → validation before removal
search()    → debugging, inspection
addAll()    → bulk load (rare, breaks stack purity)
contains()  → safety checks

NOTE:
-----
Many List methods exist only because Stack extends Vector,
not because they belong to stack abstraction.

-----------------------------------------------------------------------------------------------
WHY CERTAIN METHODS ARE DESIGN-FLAWS IN STACK:
----------------------------------------------
• get(index) breaks LIFO
• set(index) breaks immutability of stack order
• add(index) breaks stack discipline

This is why Stack is considered **poorly designed**.

-----------------------------------------------------------------------------------------------
ENTERPRISE PITFALLS:
-------------------
❌ Using Stack in new code
❌ Assuming iterators are thread-safe
❌ Using Stack for high-concurrency systems
❌ Treating Stack as pure LIFO while using List methods

-----------------------------------------------------------------------------------------------
REAL SYSTEM USAGE:
-----------------
LEGACY / EDUCATIONAL:
✔ Expression evaluation
✔ DFS algorithms
✔ Parenthesis checking

MODERN SYSTEMS:
❌ Rarely used
✔ Replaced by ArrayDeque

-----------------------------------------------------------------------------------------------
MODERN REPLACEMENT:
-------------------
Deque<Integer> stack = new ArrayDeque<>();

WHY:
----
✔ Faster
✔ No synchronization overhead
✔ Better memory locality
✔ Cleaner API

-----------------------------------------------------------------------------------------------
INTERVIEW QUESTIONS (WITH ANSWERS):
----------------------------------
Q1: Why Stack is considered legacy?
A : It extends Vector and inherits unnecessary synchronized List behavior.

Q2: Is Stack thread-safe?
A : Methods are synchronized, but iterators are fail-fast and not thread-safe.

Q3: Why ArrayDeque is preferred over Stack?
A : Better performance, no locking, cleaner stack semantics.

Q4: Can Stack be used in concurrent systems?
A : Technically yes, but scalability is very poor.

Q5: Why does Stack allow get(index)?
A : Because it extends Vector, not because it should.

-----------------------------------------------------------------------------------------------
INTERVIEW ONE-LINER:
-------------------
"Stack is a legacy, synchronized LIFO structure built on Vector,
and ArrayDeque is the correct modern replacement."

===============================================================================================

*/