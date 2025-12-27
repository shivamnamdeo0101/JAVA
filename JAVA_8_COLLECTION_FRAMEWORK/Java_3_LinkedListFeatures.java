package JAVA_8_COLLECTION_FRAMEWORK;

import java.util.*;

public class Java_3_LinkedListFeatures {
    public static void main(String[] args) {

        LinkedList<Integer> list = new LinkedList<>();

        // ================= LIST BEHAVIOR =================
        list.add(10);
        list.addFirst(5);
        list.addLast(20);

        list.getFirst();
        list.getLast();

        list.removeFirst();
        list.removeLast();

        // ================= QUEUE BEHAVIOR =================
        list.offer(30);
        list.poll();

        // ================= DEQUE / STACK BEHAVIOR =================
        list.push(40); // stack-style
        list.pop();

        list.peek();
    }
}

/*
=========================== LINKEDLIST – FEATURES, DESIGN & INTERNAL WORKING ===========================

WHAT:
-----
LinkedList is a **doubly-linked list implementation** of:
• List
• Queue
• Deque

It is a **node-based** data structure, not array-based.

WHY LINKEDLIST EXISTS:
----------------------
ArrayList problems:
❌ Costly middle insert/delete (shifting)
❌ Resizing overhead

LinkedList solves:
✔ O(1) insert/delete at ends
✔ No resizing
✔ Natural support for Queue & Deque

This is WHY it implements **multiple interfaces**.

------------------------------------------------------------------------------------------------------
INTERNAL WORKING (CRITICAL FOR INTERVIEWS)
------------------------------------------------------------------------------------------------------

1️⃣ INTERNAL DATA STRUCTURE:
----------------------------
LinkedList maintains:
• Node<E> first
• Node<E> last
• int size

Each Node contains:
-------------------
class Node<E> {
    E item;
    Node<E> next;
    Node<E> prev;
}

This makes it a **doubly linked list**.

2️⃣ MEMORY LAYOUT (IMPORTANT):
-------------------------------
• Nodes are scattered across heap
• Not contiguous memory
• Leads to cache misses (CPU-level cost)

3️⃣ ADD OPERATION:
------------------
add(E e):
• If empty → first = last = new Node
• Else → new node linked after last
• Update last reference

Time Complexity:
• O(1)

addFirst():
• New node before first
• Update first pointer

addLast():
• Same as add()

4️⃣ REMOVE OPERATION:
---------------------
removeFirst():
• Store first.next
• Set prev = null
• Update first reference

removeLast():
• Store last.prev
• Set next = null
• Update last reference

All are O(1).

5️⃣ GET OPERATION:
------------------
get(index):
• Decide traversal direction:
   if (index < size/2) → start from first
   else                → start from last
• Traverse node-by-node

Time Complexity:
• O(n)

⚠️ This is the biggest performance drawback.

6️⃣ QUEUE METHODS INTERNALS:
----------------------------
offer(E e):
• Calls addLast()
• Never throws exception

poll():
• Calls removeFirst()
• Returns null if empty

peek():
• Reads first element without removal

7️⃣ STACK METHODS (push/pop):
-----------------------------
push():
• addFirst()

pop():
• removeFirst()

This is why LinkedList can act as a Stack
(but ArrayDeque is still preferred).

8️⃣ ITERATION:
--------------
• Iterator walks node-by-node
• Fail-fast using modCount
• Structural modification throws ConcurrentModificationException

------------------------------------------------------------------------------------------------------
CORE FEATURES:
--------------
✔ Ordered
✔ Allows duplicates
✔ Allows nulls
✔ O(1) insert/delete at ends
❌ O(n) random access
❌ High memory overhead
❌ Not thread-safe

------------------------------------------------------------------------------------------------------
WHY IT IMPLEMENTS MULTIPLE INTERFACES:
-------------------------------------
• Node structure supports both ends naturally
• Avoids separate Queue / Stack implementations
• Promotes reuse via interface-based design

------------------------------------------------------------------------------------------------------
WHY LINKEDLIST IS RARE IN PRODUCTION:
------------------------------------
❌ Poor CPU cache locality
❌ Extra memory (2 references per node)
❌ Slower traversal than ArrayList
❌ GC pressure due to many small objects

In modern systems:
• CPU cache efficiency matters more than theoretical O(1)

------------------------------------------------------------------------------------------------------
WHEN LINKEDLIST *IS* A GOOD CHOICE:
----------------------------------
✔ Heavy Deque operations
✔ Command stacks (undo/redo)
✔ Workflow engines
✔ Task pipelines with head/tail operations

------------------------------------------------------------------------------------------------------
ENTERPRISE PITFALLS:
-------------------
❌ Using LinkedList assuming O(1) everywhere
❌ Using it for random access
❌ Using it instead of ArrayDeque for stack/queue
❌ Ignoring memory overhead

------------------------------------------------------------------------------------------------------
INTERVIEW TRAPS & ANSWERS:
-------------------------
Q1: Why not use LinkedList everywhere since insert is O(1)?
A : Because traversal is O(n) and memory/cache cost is high.

Q2: Why ArrayDeque preferred over LinkedList for Stack/Queue?
A : Better cache locality + lower memory overhead.

Q3: Does LinkedList allow null?
A : Yes (unlike some concurrent collections).

Q4: Why LinkedList implements Deque?
A : Natural support for both ends via node links.

------------------------------------------------------------------------------------------------------
INTERVIEW ONE-LINER:
-------------------
"LinkedList optimizes end insertions but sacrifices cache efficiency
and random access, making it a niche choice in modern systems."

====================================================================================================
*/
